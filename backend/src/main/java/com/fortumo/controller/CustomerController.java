package com.fortumo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fortumo.model.Billing;
import com.fortumo.model.Customer;
import com.fortumo.model.Subscription;
import com.fortumo.service.BillingService;
import com.fortumo.service.CustomerService;
import com.fortumo.service.SubscriptionService;

import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class CustomerController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomerService customerService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    BillingService billingService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
    	
    	Customer customerNew;
    	if(customerService.findOneByEmail(customer.getEmail())==null && customerService.findOneByPhone(customer.getPhone())==null){
    		
    		 try {
                 customerNew = customerService.addCustomer(customer);
            } catch (DataIntegrityViolationException e) {
                if (log.isDebugEnabled())
                    log.debug(e.getMessage());
                return new ResponseEntity<>(HttpStatus.CONFLICT);


            }

            return new ResponseEntity<>(customerNew, HttpStatus.CREATED);
    		
    	}
    	else{
    		return new ResponseEntity<>(HttpStatus.CONFLICT);
    	}
       
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/login{email:.+}")
    public ResponseEntity<Customer> loginCustomer(@RequestParam String email, @RequestParam String password) {

    	Customer customer = customerService.findOneByEmail(email);
    	System.out.println(email);
    	System.out.println(password);
 
    	if(customer == null){
    		
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	else if( customer.getPassword().equals(password)){
    		
    		 return new ResponseEntity<>(customer, HttpStatus.OK);
    	}
    	else{
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
       
    }
    
    

    @RequestMapping(method = RequestMethod.POST, value = "/subscribe")
    public ResponseEntity<Billing> subscribe(@RequestParam Long id, @RequestParam String subscriptionName) {
        Customer customer = customerService.getCustomer(id);
        Subscription subscription = subscriptionService.findByName(subscriptionName);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
            customer.setSubscription(subscription);
            Billing billing = new Billing(new Date(), subscription.getPrice());
            customer.getBillings().add(billing);
            customer.setLastBillingDate(billing.getPaidDate());
            customerService.updateCustomer(customer);
            return new ResponseEntity<Billing>(billing, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/unsubscribe")
    public ResponseEntity<Object> unSubscribe(@RequestParam Long id, @RequestParam String subscriptionName) {
        Customer customer = customerService.getCustomer(id);
        Subscription subscription = subscriptionService.findByName(subscriptionName);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        customer.setSubscription(null);
        customerService.updateCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

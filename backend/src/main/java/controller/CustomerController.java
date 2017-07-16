package controller;

import model.Billing;
import model.Customer;
import model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.BillingService;
import service.CustomerService;
import service.SubscriptionService;

import java.util.Date;

@RestController
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
    public ResponseEntity<Customer> registerCustomer(Customer customer) {
    	Customer customerNew;
        try {
             customerNew = customerService.addCustomer(customer);
        } catch (DataIntegrityViolationException e) {
            if (log.isDebugEnabled())
                log.debug(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);


        }

        return new ResponseEntity<>(customerNew, HttpStatus.CREATED);
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

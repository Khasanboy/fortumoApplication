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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

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
		if (customerService.findOneByEmail(customer.getEmail()) == null
				&& customerService.findOneByPhone(customer.getPhone()) == null) {

			try {
				customerNew = customerService.addCustomer(customer);
			} catch (DataIntegrityViolationException e) {
				if (log.isDebugEnabled())
					log.debug(e.getMessage());
				return new ResponseEntity<>(HttpStatus.CONFLICT);

			}

			return new ResponseEntity<>(customerNew, HttpStatus.CREATED);

		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<Customer> loginCustomer(@RequestBody Map<String, String> requestBody) {

		String phone = requestBody.get("phone");
		String password = requestBody.get("password");

		Customer customer = customerService.findOneByPhone(phone);

		if (customer == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else if (customer.getPassword().equals(password)) {

			return new ResponseEntity<>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/subscribe")
	public ResponseEntity<Customer> subscribe(@RequestBody Map<String, String> requestBody) {
		Long id = new Long(requestBody.get("id"));
		String subscriptionName = requestBody.get("subscriptionName");

		Customer customer = customerService.getCustomer(id);
		Subscription subscription = subscriptionService.findByName(subscriptionName);
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else if (subscription == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			customer.setSubscription(subscription);
			Calendar c = new GregorianCalendar();
			c.add(Calendar.DATE, 30);
			Date date = c.getTime();
			Billing billing = new Billing(new Date(), date, subscription.getPrice(), customer);
			customer.getBillings().add(billing);
			customer.setLastBillingDate(new Date());
			customerService.updateCustomer(customer);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/unsubscribe")
	public ResponseEntity<Customer> unSubscribe(@RequestBody Map<String, String> requestBody) {
		Long id = new Long(requestBody.get("id"));
		String subscriptionName = requestBody.get("subscriptionName");

		Customer customer = customerService.getCustomer(id);
		Subscription subscription = subscriptionService.findByName(subscriptionName);
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else if (subscription == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else{
			customer.setSubscription(null);
			customerService.updateCustomer(customer);
			return new ResponseEntity<>(customer,HttpStatus.OK);
		}
		
	}

}

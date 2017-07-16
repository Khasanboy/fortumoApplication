package com.fortumo.common;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.fortumo.controller.CustomerController;
import com.fortumo.model.Billing;
import com.fortumo.model.Customer;
import com.fortumo.service.CustomerService;

public class QuartzJob implements Job {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerController customerController;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		List<Customer> customers = new ArrayList<>();

		customers = customerService.getAllCustomers();
		if (!customers.isEmpty() && customers.size() > 0) {
			for (Customer customer : customers) {
				if (customer.getSubscription() != null) {
					Duration duration = Duration.between(new Date().toInstant(),
							customer.getLastBillingDate().toInstant());
					if (!duration.minusDays(30).isNegative()) {
						Billing billing = new Billing(new Date(), customer.getSubscription().getPrice());
						customer.getBillings().add(billing);
						customer.setLastBillingDate(billing.getPaidDate());
						customerService.updateCustomer(customer);
						///here should send data to send SMS
					}
				}
			}
		}

	}

}

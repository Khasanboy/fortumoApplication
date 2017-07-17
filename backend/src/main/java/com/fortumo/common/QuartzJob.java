package com.fortumo.common;

import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
		
		if(customerService.getAllCustomers() != null){
			List<Customer> customers =  customerService.getAllCustomers();
			for (Customer customer : customers) {
				if (customer.getSubscription() != null) {
					Duration duration = Duration.between(new Date().toInstant(),
							customer.getLastBillingDate().toInstant());
					if (!duration.minusDays(30).isNegative()) {
						Calendar c = new GregorianCalendar();
						c.add(Calendar.DATE, 30);
						Date date = c.getTime();
						Billing billing = new Billing(new Date(), date, customer.getSubscription().getPrice(), customer);
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

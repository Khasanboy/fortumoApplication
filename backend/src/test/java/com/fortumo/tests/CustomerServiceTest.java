package com.fortumo.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fortumo.AbstractTest;
import com.fortumo.model.Customer;
import com.fortumo.service.CustomerService;

public class CustomerServiceTest extends AbstractTest {
	
	@Autowired
	CustomerService customerService;
	
	
	//This one depends on the running cycle of the tests; sometimes it may be 1
	@Test
	public void testGetAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		
		Assert.assertNotNull(customers);
		Assert.assertEquals(2, customers.size());
	}
	
	@Test
	public void testGetCustomer(){
		Customer customer = customerService.getCustomer(1L);
		Assert.assertNotNull(customer);
		Assert.assertEquals(1, customer.getId().intValue());
	}
	
	@Test
	public void testAddCustomer(){
		Customer customer = new Customer("Masaki", "Ihara", "ihara@gmail.com", "1234", "1234" );
		Customer createdCustomer = customerService.addCustomer(customer);
		
		Assert.assertNotNull(createdCustomer);
		Assert.assertEquals(createdCustomer.getFirstName(), customerService.findOneByEmail("ihara@gmail.com").getFirstName());
		
	}
	
	@Test
	public void testFindOneByEmail(){
		Customer cus = customerService.findOneByEmail("doe@gmail.com");
		
		Assert.assertNotNull(cus);
		Assert.assertEquals("doe@gmail.com", cus.getEmail());
	}
	
	@Test
	public void testFindOneByPhone(){
		
		Customer cus = customerService.findOneByPhone("123");
		
		Assert.assertNotNull(cus);
		Assert.assertEquals("123", cus.getPhone());
	}
	
	@Test
	public void testUpdateCustomer(){
		
		Customer cus = customerService.findOneByPhone("123");
		cus.setLastName("Jon");
		
		Assert.assertEquals("Jon", cus.getLastName());
	}
	
	
	
	
	

}

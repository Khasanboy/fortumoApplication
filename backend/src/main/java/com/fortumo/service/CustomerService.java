package com.fortumo.service;

import java.util.List;

import com.fortumo.model.Customer;

public interface CustomerService {
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomer(Long id);
	
	public Customer addCustomer(Customer admin);
	
	public void deleteCustomer(Long id);
	
	public void updateCustomer(Customer admin);
	
	public Customer findOneByEmail(String email);
	
	public Customer findOneByPhone(String phone);
	

}

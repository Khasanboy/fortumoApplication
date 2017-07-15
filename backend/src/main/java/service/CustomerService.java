package service;

import java.util.List;

import model.Customer;

public interface CustomerService {
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomer(Long id);
	
	public void addCustomer(Customer admin);
	
	public void deleteCustomer(Long id);
	
	public void updateCustomer(Customer admin);
	
	public Customer findOneByUsername(String username);

}

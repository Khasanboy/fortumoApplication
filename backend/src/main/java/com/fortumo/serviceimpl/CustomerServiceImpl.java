package com.fortumo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortumo.model.Customer;
import com.fortumo.repository.CustomerRepository;
import com.fortumo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomer(Long id) {
		return customerRepository.findOne(id);
	}

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);

	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.delete(id);

	}

	@Override
	public void updateCustomer(Customer customer) {
		customerRepository.save(customer);

	}

	@Override
	public Customer findOneByEmail(String email) {
		return customerRepository.findOneByEmail(email);
	}

	@Override
	public Customer findOneByPhone(String phone) {
		return customerRepository.findOneByPhone(phone);
	}
	
	

}

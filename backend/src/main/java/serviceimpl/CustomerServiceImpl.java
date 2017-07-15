package serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.Customer;
import repository.CustomerRepository;
import service.CustomerService;

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
	public Customer findOneByUsername(String username) {
		return customerRepository.findOneByUsername(username);
	}

}

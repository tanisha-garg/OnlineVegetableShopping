package com.cg.vegetable.mgmt.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.OnlineVegetableShoppingApplication;
import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.ICartRepository;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	private static final Logger Log = LoggerFactory.getLogger(OnlineVegetableShoppingApplication.class);

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private ICartRepository cartRepository;
	
	/*
	 * 
	 * Saves Customer in the database after validation
	 * 
	 * @param customer is Customer
	 * @return saved CustomerDetails
	 * 
	 * */

	@Override
	public Customer addCustomer(Customer customer) {
		Log.info("Inside addCustomer method with customer id:"+customer.getCustomerId());
		validateCustomer(customer);
		Cart cart = new Cart();
		customer.setCart(cart);
		customerRepository.save(customer);
		cart.setCustId(customer.getCustomerId());
		cartRepository.save(cart);
		return customer;
	}

	/*
	 * 
	 * Updates Customer in database after validation
	 * 
	 *  @param customer is Customer
	 *  @return updated CustomerDetails
	 *  
	 * */
	@Override
	public Customer updateCustomer(Customer customer) {
		Log.info("Inside updateCustomer method with customer id:"+customer.getCustomerId());
		validateCustomer(customer);
		boolean exist = customerRepository.existsById(customer.getCustomerId());
		if (!exist) {
			throw new CustomerNotUpdatedException("Customer doesn't exist for id =" + customer.getCustomerId());
		}
		Customer updateCustomer = customerRepository.save(customer);
		return updateCustomer;
	}
	

	/*
	 * 
	 * Removed Customer from the database
	 * 
	 * @param customer is Customer 
	 * @return saved CustomerDetails
	 * 
	 * */

	@Override
	public Customer removeCustomer(Customer customer) {
		Log.info("Inside removeCustomer method");
		Integer customerId = customer.getCustomerId();
		boolean exists = customerRepository.existsById(customerId);
		if (!exists) {
			throw new CustomerNotRemovedException("No Customer passed");
		}
		customerRepository.deleteById(customerId);
		return customer;
	}

	/*
	 * Find a customer from the database based on customerId
	 * 
	 * @param customerId is customerId
	 * @return fetched CustomerDetails
	 * 
	 * */
	@Override
	public Customer viewCustomer(Integer customerId) {
		Log.info("Inside viewCustomer method with customer id:"+customerId);
		Optional<Customer> viewCustomer = customerRepository.findById(customerId);
		if (!viewCustomer.isPresent()) {
			throw new CustomerNotFoundException("Customer doesn't exist for id =" + customerId);
		}
		validateCustomer(viewCustomer.get());
		return viewCustomer.get();

	}

	/*
	 * Find a list of customers from the database based on city
	 * 
	 * @param city is city entered by customers
	 * @return fetched list of CustomerDetails
	 * 
	 * */
	@Override
	public List<Customer> viewCustomerList(String city) {
		Log.info("Inside viewCustomerList method with city:"+city);
		List<Customer> list = customerRepository.findByCity(city);
		if (list.isEmpty()) {
			throw new CustomerNotFoundException("customers not found");
		}
		return list;
	}

	// validating customer details
	public void validateCustomer(Customer customer) {
		if (customer == null) {
			throw new CustomerNotFoundException("No customer exists");
		}

		if (customer.getName() == null || customer.getName().isEmpty()) {
			throw new InvalidCustomerNameException("Name cannot be null or empty");
		}

		if (customer.getEmailId() == "") {
			throw new InvalidEmailIdException("Email cannot be empty");
		}

		if (customer.getCustomerId() < 0) {
			throw new CustomerIdNotFoundException("Invalid Customer ID passed");
		}
	}

	// validating customer mobile number
	void validateMobileNumber(String mobileNumber) {
		if (mobileNumber.length() != 10) {
			throw new InvalidMobileNumberException("number is not valid");
		}

	}
}
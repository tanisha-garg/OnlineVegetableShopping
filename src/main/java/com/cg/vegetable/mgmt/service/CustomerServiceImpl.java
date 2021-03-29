package com.cg.vegetable.mgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Transactional
	@Override
	public Customer addCustomer(Customer customer) {
		customerRepository.save(customer);
		return customer;
	}

	@Transactional
	@Override
	public Customer updateCustomer(Customer customer) {
		validateCustomer(customer);
		boolean exist = customerRepository.existsById(customer.getCustomerId());
		if (!exist) {
			throw new CustomerNotUpdatedException("Customer doesn't exist for id =" + customer.getCustomerId());
		}
		Customer updateCustomer = customerRepository.save(customer);
		return updateCustomer;
	}

	@Transactional
	@Override
	public Customer removeCustomer(Customer customer) {

//		Optional<Customer>optional=customerRepository.findById(customer.getCustomerId());
//		if(!optional.isPresent()) {
//			 throw new CustomerIdNotFoundException("Customer to be removed does not exist");
//		 }
//		 Customer removed = optional.get();
//		 customerRepository.deleteById(customer.getCustomerId());
//		 return removed;

		Integer customerId = customer.getCustomerId();
		boolean exists = customerRepository.existsById(customerId);
		if (!exists) {
			throw new CustomerNotRemovedException("No Customer passed");
		}
		customerRepository.deleteById(customerId);
		return null;

	}

	@Transactional
	@Override
	public Customer viewCustomer(Customer customer) {
		validateCustomer(customer);
		Integer customerId = customer.getCustomerId();
		Optional<Customer> viewCustomer = customerRepository.findById(customerId);
		if (!viewCustomer.isPresent()) {
			throw new CustomerNotFoundException("Customer doesn't exist for id =" + customerId);
		}
		return viewCustomer.get();

	}

	@Transactional
	@Override
	public List<Customer> viewCustomerList(String location) {
		List<Customer> customerList = customerRepository.findAll();
		List<Customer> desiredList = new ArrayList<>();
		if (customerList.isEmpty()) {
			throw new CustomerNotFoundException("No customer is present at the particular given location");
		}

		else {
			for (Customer customer : customerList) {
				if (customer.getAddress().getCity() == location) {
					desiredList.add(customer);
				}
			}
		}
		return desiredList;
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

	void validateMobileNumber(String mobileNumber) {
		Pattern pattern = Pattern.compile("^(\\+\\d{7,9}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
		Matcher matcher = pattern.matcher("+777 (202) 555-0125");

		boolean result = mobileNumber.equals(matcher.matches());
		if (result == false) {
			throw new InvalidMobileNumberException(
					"Mobile number entered is not valid. Please enter a valid mobole number");
		}
	}

	void validateEmailId(String emailId) {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		// Matching the given phone number with regular expression
		boolean result = emailId.matches(regex);
		if (result == false) {
			throw new InvalidEmailIdException("EmailId entered is not valid. Please enter a valid emailId.");
		}
	}

	void validatePassword(String password) {
		// Regex to check valid password.
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(password);
		if (password == null) {
			throw new InvalidPasswordException("Enter a valid password");
		}
	}
}

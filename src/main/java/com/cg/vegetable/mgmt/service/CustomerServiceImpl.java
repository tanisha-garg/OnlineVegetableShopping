package com.cg.vegetable.mgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.exceptions.*;
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Override
	public Customer addCustomer(Customer customer) {
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return null;
	}

	@Override
	public Customer removeCustomer(Customer customer) {
		return null;
	}

	@Override
	public Customer viewCustomer(Customer customer) {
		return null;
	}

	@Override
	public List<Customer> viewCustomerList(String location) {
		return null;
	}

//	@Override
//	public List<Customer> viewCustomerList(){
//		List<Customer> customerList=repository.viewCustomerList();
//		if(customerList.isEmpty()) {
//		throw new CustomerNotFoundException("No such customer exists");
//	}
	
	public void validateName(String name) {
		if(name == null || name.isEmpty()|| name.trim()==null) {
			throw new InvalidCustomerNameException("Name cannot be null or empty");
		}
	}
	
	void validateMobileNumber(String mobileNumber)
	   {
			if(mobileNumber.length()<10 || mobileNumber.length()>10)
			{
				throw new InvalidMobileNumberException("Mobile Number entered is wrong");
			}
		}
	
	void validateEmailId(String emailId)
	   {
			if(!(emailId.contains("@")&& emailId.endsWith(".com")))
			{
				throw new InvalidEmailIdException("EmailId entered is wrong");
			}
		}
	
	void validatePassword(String password)
	   {
			if(!(password.length() >= 8 && 
			          password.length() <= 15 && password.contains("@,#,$,%,^,&,*,1,2,3,4,5,6,7,8,9,0")))
			{
				throw new InvalidPasswordException("Password does'nt match the criteria");
			}
		}
	
//	void validateConfirmPassword(String confirmPassword)
//	   {
//			if(password!=confirmPassword)
//			{
//				throw new InvalidPasswordException("Password does'nt match the criteria");
//			}
//		}
	
	}
	



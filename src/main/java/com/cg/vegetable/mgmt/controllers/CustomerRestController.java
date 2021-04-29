package com.cg.vegetable.mgmt.controllers;

import com.cg.vegetable.mgmt.dto.*;
import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.service.ICustomerService;
import com.cg.vegetable.mgmt.util.CustomerUtil;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/customers")
@RestController
public class CustomerRestController {
	@Autowired
	private ICustomerService customerService;

	@Autowired
	private CustomerUtil customerUtil;
	

	/*
     * 
     * Rest Controller for removing customer
     * Path: /customers/remove/id
     * @param: id is customerId
     * @return: string stating "Customer removed successfully"
     * 
     * */	
	@DeleteMapping("/remove/{id}")
	public String removeCustomer(@PathVariable @Min(1) Integer id) {
		Customer customer = customerService.viewCustomer(id);
		customerService.removeCustomer(customer);
		return "Customer removed successfully";

	}
	/*
     * 
     * Rest Controller for getting customer using customer id
     * Path: /customers/view/id
     * @param: id is customerId
     * @return: customer details if added successfully
     * 
     * */	

	@GetMapping("/view/{id}")
	public CustomerDetails fetchCustomerDetails(@PathVariable @Min(1) int id) {
		Customer customer = customerService.viewCustomer(id);
		CustomerDetails details = customerUtil.toDetail(customer);
		return details;

	}
	/*
     * 
     * Rest Controller for adding customer
     * Path: /customers/add
     * @return: customer details if added successfully
     * 
     * */	

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public CustomerDetails addCustomer(@RequestBody @Valid AddCustomerRequest requestData) {
		Address address = new Address();
		address.setArea(requestData.getArea());
		address.setBuildingName(requestData.getBuildingName());
		address.setArea(requestData.getArea());
		address.setCity(requestData.getCity());
		address.setFlatNo(requestData.getFlatNo());
		address.setPincode(requestData.getPincode());
		address.setState(requestData.getState());
		Customer customer = new Customer(requestData.getName(), requestData.getMobileNumber(), requestData.getEmailId(),
				address);
		Customer addedCustomer = customerService.addCustomer(customer);
		return customerUtil.toDetail(addedCustomer);
	}

	/*
     * 
     * Rest Controller for updating customer using customer id
     * Path: /customers/update/id
     * @param: id is customerId
     * @return: customer details if updated successfully
     * 
     * */	
	@PutMapping("/update/{id}")
	public CustomerDetails updateCustomer(@RequestBody @Valid UpdateCustomerDetailsRequest requestData,
			@PathVariable Integer id) {
		Customer customer = customerService.viewCustomer(id);
		customer.setName(requestData.getName());
		customer.setMobileNumber(requestData.getMobileNumber());
		customer.setEmailid(requestData.getEmailId());
		Customer updatedCustomer = customerService.updateCustomer(customer);
		CustomerDetails details = customerUtil.toDetail(updatedCustomer);
		return details;
	}
	
	/*
     * 
     * Rest Controller for getting customers list using city
     * Path: /customers/all/bycity/city
     * @param: city is customer entered city
     * @return: customers list of customers exist at given city
     * 
     * */	
	@GetMapping("/all/bycity/{city}")
	public List<CustomerDetails> allCustomerDetails(@PathVariable("city") String city) {
		List <Customer> customers = customerService.viewCustomerList(city);
		List<CustomerDetails> details = customerUtil.toDetailList(customers);
		return details;

	}
	/*
     * 
     * Rest Controller for updating customer address using customer id
     * Path: /customers/update/address/id
     * @param: id is customerId
     * @return: customer details if updated address successfully
     * 
     * */	

	@PutMapping("/update/address/{id}")
	public CustomerDetails updateAddress(@RequestBody @Valid UpdateCustomerAddressRequest requestData,
			@PathVariable @Min(1) Integer id) {
		Customer customer = customerService.viewCustomer(id);
		customer.getAddress().setFlatNo(requestData.getFlatNo());
		customer.getAddress().setBuildingName(requestData.getBuildingName());
		customer.getAddress().setArea(requestData.getArea());
		customer.getAddress().setCity(requestData.getCity());
		customer.getAddress().setState(requestData.getState());
		customer.getAddress().setPincode(requestData.getPincode());
		Customer updatedAddress = customerService.updateCustomer(customer);
		CustomerDetails addressDetails = customerUtil.toDetail(updatedAddress);
		return addressDetails;
	}
	
	/*
     * 
     * Rest Controller for updating customer password using customer id
     * Path: /customers/update/password/id
     * @param: id is customerId
     * @return: customer details if updated password successfully
     * 
     * */	

	@PutMapping("/update/password/{id}")
	public CustomerDetails updatePassword(@RequestBody @Valid UpdatePasswordRequest requestData,
			@PathVariable @Min(1) Integer id) {
		Customer customer = customerService.viewCustomer(id);
		customer.setPassword(requestData.getPassword());
		Customer updatedPassword = customerService.updateCustomer(customer);
		CustomerDetails details = customerUtil.toDetail(updatedPassword);
		return details;
	}
}
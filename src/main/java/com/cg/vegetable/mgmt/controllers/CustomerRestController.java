package com.cg.vegetable.mgmt.controllers;

import com.cg.vegetable.mgmt.dto.*;
import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.service.ICustomerService;
import com.cg.vegetable.mgmt.util.CustomerUtil;

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

	@DeleteMapping("/remove/{id}")
	public String removeCustomer(@PathVariable @Min(1) Integer id) {
		Customer customer = customerService.viewCustomer(id);
		customerService.removeCustomer(customer);
		return "Customer removed successfully";

	}

	@GetMapping("/view/{id}")
	public CustomerDetails fetchCustomerDetails(@PathVariable @Min(1) int id) {
		Customer customer = customerService.viewCustomer(id);
		CustomerDetails details = customerUtil.toDetail(customer);
		return details;

	}

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
		Customer customer = new Customer(requestData.getName(), requestData.getMobileNumber(),
				requestData.getEmailId(),address);
		Customer addedCustomer = customerService.addCustomer(customer);
		return customerUtil.toDetail(addedCustomer);
	}

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

	@PutMapping("/update/password/{id}")
	public CustomerDetails updatePassword(@RequestBody @Valid UpdatePasswordRequest requestData, @PathVariable @Min(1) Integer id) {
		Customer customer = customerService.viewCustomer(id);
		customer.setPassword(requestData.getPassword());
		Customer updatedPassword = customerService.updateCustomer(customer);
		CustomerDetails details = customerUtil.toDetail(updatedPassword);
		return details;
	}
}
package com.cg.vegetable.mgmt.controllers;

import com.cg.vegetable.mgmt.dto.CustomerDetails;
import com.cg.vegetable.mgmt.dto.UpdateCustomerDetailsRequest;
import com.cg.vegetable.mgmt.dto.UpdateCustomerAddressRequest;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;
import com.cg.vegetable.mgmt.service.ICustomerService;
import com.cg.vegetable.mgmt.util.CustomerUtil;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
@RestController
public class CustomerRestController
{
    @Autowired
    private ICustomerService customerService;
    
    @Autowired
    private CustomerUtil customerUtil;
    
    @Autowired
    private ICustomerRepository  customerRepository;

    @DeleteMapping( "/removeCustomer/{id}")
    public String removeCustomer(@PathVariable Integer id)
    {
    	Optional<Customer> optional= customerRepository.findById(id);
        Customer customer = optional.get();
        customerService.removeCustomer(customer);
        return "Customer removed successfully";
    }
    
        
    @GetMapping("/viewCustomer/{id}")
	public CustomerDetails fetchCustomerDetails(@PathVariable int id) {
    	Optional<Customer> optional= customerRepository.findById(id);
		Customer customer = optional.get();
		//Customer details = customerService.viewCustomer(customer);
		CustomerDetails details= customerUtil.toDetail(customer);
		return details;
  
    }
    
    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody CustomerDetails requestData)
    {
        Customer customer = new Customer(requestData.getName(),
        		requestData.getMobileNumber(),requestData.getEmailId());
        
        Customer addedCustomer= customerService.addCustomer(customer);
        return "Customer with customerId"+addedCustomer.getCustomerId()+" added";
    }
    
    
    @PutMapping("/updateCustomerDetails/{id}")
    public CustomerDetails updateCustomer(@RequestBody UpdateCustomerDetailsRequest requestData ,
    		@PathVariable Integer id) {
    	Optional<Customer> optional= customerRepository.findById(id);
    	Customer customer= optional.get();
    	customer.setName(requestData.getName());
		customer.setMobileNumber(requestData.getMobileNumber());
		customer.setEmailid(requestData.getEmailId());		
		Customer updatedCustomer = customerService.updateCustomer(customer);
		CustomerDetails details = customerUtil.toDetail(updatedCustomer);
		return  details;
    }
    
    @PutMapping("/updateCustomerAddress/{id}")
    public CustomerDetails updateAddress(@RequestBody UpdateCustomerAddressRequest requestData ,
    		@PathVariable Integer id) {

    	Optional<Customer> optional= customerRepository.findById(id);
    	Customer customer= optional.get();
    	customer.getAddress().setFlatNo(requestData.getFlatNo());
    	customer.getAddress().setBuildingName(requestData.getBuildingName());
    	customer.getAddress().setArea(requestData.getArea());
    	customer.getAddress().setCity(requestData.getCity());
    	customer.getAddress().setState(requestData.getState());
    	customer.getAddress().setPincode(requestData.getPincode());
				
		Customer updatedAddress = customerService.updateCustomer(customer);
		CustomerDetails addressDetails = customerUtil.toDetail(updatedAddress);
		return  addressDetails;
    }
    
   
    
    @GetMapping("/viewAll")
    List<Customer> all() {
      return customerRepository.findAll();
    }
}
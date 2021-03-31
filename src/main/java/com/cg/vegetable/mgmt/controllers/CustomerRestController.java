package com.cg.vegetable.mgmt.controllers;

import com.cg.vegetable.mgmt.dto.CustomerDetails;
import com.cg.vegetable.mgmt.dto.UpdateCustomerDetailsRequest;
import com.cg.vegetable.mgmt.dto.UpdatePasswordRequest;
import com.cg.vegetable.mgmt.dto.UpdateCustomerAddressRequest;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;
import com.cg.vegetable.mgmt.service.ICustomerService;
import com.cg.vegetable.mgmt.util.CustomerUtil;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
@RestController
public class CustomerRestController
{
    @Autowired
    private ICustomerService customerService;
    
    @Autowired
    private CustomerUtil customerUtil;
    

    @DeleteMapping( "/removeCustomer/{id}")
    public String removeCustomer(@PathVariable Integer id)
    {
    	Customer customer= customerService.viewCustomer(id); 
        customerService.removeCustomer(customer);
        return "Customer removed successfully";
               
    }
    
        
    @GetMapping("/viewCustomer/{id}")
	public CustomerDetails fetchCustomerDetails(@PathVariable int id) {
    	Customer customer= customerService.viewCustomer(id);
		CustomerDetails details= customerUtil.toDetail(customer);
		return details;
  
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addCustomer")
    public CustomerDetails addCustomer(@RequestBody CustomerDetails requestData)
    {
        Customer customer = new Customer(requestData.getName(),
        		requestData.getMobileNumber(),requestData.getEmailId());
        
        Customer addedCustomer= customerService.addCustomer(customer);
        return customerUtil.toDetail(addedCustomer);
    }
    
    
    @PutMapping("/updateCustomerDetails/{id}")
    public CustomerDetails updateCustomer(@RequestBody UpdateCustomerDetailsRequest requestData ,
    		@PathVariable Integer id) {
    	Customer customer= customerService.viewCustomer(id);
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
    	Customer customer= customerService.viewCustomer(id);
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
    
    @PutMapping("/updatePassword/{id}")
    public CustomerDetails updatePassword(@RequestBody UpdatePasswordRequest requestData ,
    		@PathVariable Integer id) {
    	Customer customer= customerService.viewCustomer(id);
    	customer.setPassword(requestData.getPassword());			
		Customer updatedPassword = customerService.updateCustomer(customer);
		CustomerDetails details = customerUtil.toDetail(updatedPassword);
		return  details;
    }
    
   
    
//    @GetMapping("/viewAll")
//    List<Customer> all() {
//      return customerService.findAll();
//    }
}
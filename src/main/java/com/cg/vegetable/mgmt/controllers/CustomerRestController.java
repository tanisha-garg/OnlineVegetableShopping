package com.cg.vegetable.mgmt.controllers;

import com.cg.vegetable.mgmt.dto.CustomerDetails;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;
import com.cg.vegetable.mgmt.service.ICustomerService;
import com.cg.vegetable.mgmt.util.CustomerUtil;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customers")
@RestController
public class CustomerRestController
{
    @Autowired
    private ICustomerService service;
    
    @Autowired
    private CustomerUtil util;
    
    @Autowired
    private ICustomerRepository  repository;

    @DeleteMapping( "/removeCustomer/{id}")
    public String fetchCustomer(@PathVariable Integer id)
    {
    	Optional<Customer> optional= repository.findById(id);
        Customer customer = optional.get();
        service.removeCustomer(customer);
        return "Customer removed successfully";
    }
    
        
    @GetMapping("/viewCustomer/{id}")
	public CustomerDetails fetchCustomerDetails(@PathVariable Integer id) {
    	Optional<Customer> optional= repository.findById(id);
		Customer customer = optional.get();
		Customer details = service.viewCustomer(customer);
		return util.toDetail(details);
  
    }

}
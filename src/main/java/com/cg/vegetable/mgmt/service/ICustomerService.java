package com.cg.vegetable.mgmt.service;

import java.util.List;

import com.cg.vegetable.mgmt.entities.Customer;

public interface ICustomerService {

	Customer addCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

	Customer removeCustomer(Customer customer);

	Customer viewCustomer(Integer customerId);

	List<Customer> viewCustomerList(String city);

}

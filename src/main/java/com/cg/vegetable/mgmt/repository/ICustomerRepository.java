package com.cg.vegetable.mgmt.repository;

import java.util.List;

import com.cg.vegetable.mgmt.entities.Customer;

public interface ICustomerRepository {

	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer removeCustomer(Customer customer);
	public Customer viewCustomer(Customer customer);
	public List<Customer> viewCustomerList(String location);
}

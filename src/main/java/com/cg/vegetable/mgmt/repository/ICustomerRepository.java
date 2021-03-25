package com.cg.vegetable.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.vegetable.mgmt.entities.Customer;

public interface ICustomerRepository extends JpaRepository<Customer,Integer> {

//	public Customer addCustomer(Customer customer);
//	
//	public Customer updateCustomer(Customer customer);
//	
//	public Customer removeCustomer(Customer customer);
//	
//	public Customer viewCustomer(Customer customer);
//
//	public Customer save(Customer customer);
//	
//	public List<Customer> viewCustomerList(String location);
}

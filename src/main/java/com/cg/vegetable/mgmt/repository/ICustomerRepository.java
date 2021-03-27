package com.cg.vegetable.mgmt.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.vegetable.mgmt.entities.Customer;


public interface ICustomerRepository extends JpaRepository<Customer,Integer> {


}

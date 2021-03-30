package com.cg.vegetable.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.vegetable.mgmt.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
	
    @Query("from Customer c join c.address a where a.city=:city ")
    List<Customer> findByCity(@Param("city")String city);

    @Query("from Customer c join address a where a.city=:city ")
    List<Customer> findByCity(@Param("city")String city);

}

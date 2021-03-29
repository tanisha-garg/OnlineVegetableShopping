package com.cg.vegetable.mgmt.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.vegetable.mgmt.entities.Order;

public interface IOrderRepository extends JpaRepository <Order,Integer> {
	
	List<Order> findByCustomerId(int customerId);
	
	List<Order> findByOrderDate(LocalDate date);

}

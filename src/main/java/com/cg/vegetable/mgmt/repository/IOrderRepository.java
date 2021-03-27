package com.cg.vegetable.mgmt.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.vegetable.mgmt.entities.Order;

public interface IOrderRepository extends JpaRepository <Order,Integer> {

//	public Order addOrder(Order order);
//	public Order viewOrder(int orderid);
//	public Order updateOrderDetails(Order order);
//	public List<Order> viewAllOrders(int custid);
//	public List<Order> viewOrderList(LocalDate date);
//	public List<Order> viewOrderList();
//	public Order cancelOrder(int orderid);
}

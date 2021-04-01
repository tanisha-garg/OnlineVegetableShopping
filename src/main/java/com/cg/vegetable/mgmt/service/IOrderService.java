package com.cg.vegetable.mgmt.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.vegetable.mgmt.entities.Order;

public interface IOrderService {

	public Order addOrder(Order order);
	public Order viewOrder(int orderId);
	public Order updateOrderDetails(Order order);
	public List<Order> viewAllOrders(int custId);
	public List<Order> viewOrderList(LocalDate date);
	public List<Order> viewOrderList();
	public Order cancelOrder(int orderId);
}

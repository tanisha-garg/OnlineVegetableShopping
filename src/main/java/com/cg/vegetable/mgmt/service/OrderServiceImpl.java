package com.cg.vegetable.mgmt.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.entities.Order;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;
import com.cg.vegetable.mgmt.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;
	private ICustomerRepository customerRepository;

	@Transactional
	@Override
	public Order addOrder(Order order) {
		Order saved = orderRepository.save(order);
		return saved;
	}

	@Override
	public Order viewOrder(Order order) {
		int id = order.getOrderNo();
		Optional<Order> optional = orderRepository.findById(id);
		if (!optional.isPresent()) {
			throw new OrderNotFoundException("Order with id " + id + " doesn't exist");
		}
		Order order2 = optional.get();
		return order2;
	}

	@Transactional
	@Override
	public Order updateOrderDetails(Order order) {
		int id = order.getOrderNo();
		boolean exists = orderRepository.existsById(id);
		if (!exists) {
			throw new OrderNotFoundException("Order with id " + id + " doesn't exist");
		}
		Order saved = orderRepository.save(order);
		return saved;
	}

	@Override
		public List<Order> viewAllOrders(int custid){
		return null;
		}
		
	

	@Override
	public List<Order> viewOrderList(LocalDate date) {
		return null;
	}

	@Override
	public List<Order> viewOrderList() {
		return null;
	}

	@Override
	public Order cancelOrder(int orderid) {
		return null;
	}

}

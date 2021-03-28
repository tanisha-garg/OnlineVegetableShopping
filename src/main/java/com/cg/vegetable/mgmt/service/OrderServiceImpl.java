package com.cg.vegetable.mgmt.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.entities.Order;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.IOrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	private IBillingService billingService;

	@Transactional
	@Override
	public Order addOrder(Order order) {
		order.setStatus("Order Placed");
		order.setOrderDate(currentTime());
		Order saved = orderRepository.save(order);
		BillingDetails bill = new BillingDetails();
		bill.setTransactionStatus("Pending");
		bill.setTransactionMode("Cash On Delivery");
		bill.setOrderId(saved.getOrderNo());
		bill = billingService.addBill(bill);
		return saved;
	}

	@Override
	public Order viewOrder(Order order) {
		int id = order.getOrderNo();
		Optional<Order> orderOptional = orderRepository.findById(id);
		if (!orderOptional.isPresent()) {
			throw new OrderNotFoundException("Order with id " + id + " doesn't exist");
		}
		Order order2 = orderOptional.get();
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
		List<Order> orderList = orderRepository.findAll();
		List<Order> desiredList = new ArrayList<>();
		if(orderList.isEmpty()) {
			throw new OrderNotFoundException("Order Not Found");
		}
		else {
			for(Order order : orderList) {
				if(order.getCustId() == custid) {
					desiredList.add(order);
				}
			}
		}
		return desiredList;
	}
		
	

	@Override
	public List<Order> viewOrderList(LocalDate date) {
		List<Order> orderList = orderRepository.findAll();
		List<Order> desiredList = new ArrayList<>();
		if(orderList.isEmpty()) {
			throw new OrderNotFoundException("Order Not Found");
		}
		else {
			for(Order order : orderList) {
				LocalDate orderDate = order.getOrderDate();
				if(orderDate.isEqual(date)) {
					desiredList.add(order);
				}
			}
		}
		return desiredList;
	}

	@Override
	public List<Order> viewOrderList() {
		List<Order> orderList = orderRepository.findAll();
		if(orderList.isEmpty()) {
			throw new OrderNotFoundException("Orders not found");
		}
		return orderList;
	}

	@Transactional
	@Override
	public void cancelOrder(int orderid) {
		 orderRepository.deleteById(orderid);
	}
	
	
	public LocalDate currentTime() {
		return LocalDate.now();
	}

}

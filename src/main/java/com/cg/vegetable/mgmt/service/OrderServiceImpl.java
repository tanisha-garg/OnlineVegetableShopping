package com.cg.vegetable.mgmt.service;

import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.vegetable.mgmt.constants.*;
import com.cg.vegetable.mgmt.entities.*;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.*;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	private IBillingService billingService;
	
	@Autowired
	private ICartRepository cartRepository;
	
	@Autowired
	private ICartVegetableRepository cartVegetableRepository;

	@Autowired
	private IVegetableMgmtRepository vegetableMgmtRepository;


	@Transactional
	@Override
	public Order addOrder(Order order) {
		order.setStatus("Order Placed");
		order.setOrderDate(currentTime());
		Cart cart = cartRepository.findCartByCustId(order.getCustomerId());
		List<CartVegetable> vegetableList = cartVegetableRepository.findByCart(cart);
		Optional<Double> optionalCost = vegetableList.stream().
										map(cv -> cv.getQuantity() * cv.getVegetable().getPrice()).
										reduce((cost1, cost2) -> cost1+cost2);
		if(!optionalCost.isPresent()) {
			throw new InvalidVegetablePriceException("Cannot find the cost of the vegetable");
		}
		List<CartVegetable>cartVegetables=cartVegetableRepository.findByCart(cart);
		reduceVegetableStockAfterOrder(cartVegetables);
		order.setTotalAmount(optionalCost.get());
		Order saved = orderRepository.save(order);
		BillingDetails bill = new BillingDetails();
		bill.setTransactionStatus(TransactionStatus.PENDING);
		bill.setTransactionMode(TransactionMode.CASH_ON_DELIVERY);
		bill.setOrderId(saved.getOrderNo());
		billingService.addBill(bill);
		cartVegetableRepository.deleteByCart(cart);
		return saved;
	}

	public void reduceVegetableStockAfterOrder(Collection<CartVegetable>cartVegetables){
		for(CartVegetable cartVegetable:cartVegetables){
			int vegetableQuantInCart=cartVegetable.getQuantity();
			Vegetable vegetable=cartVegetable.getVegetable();
			int stockedQuantity=vegetable.getQuantity();
			stockedQuantity=stockedQuantity-vegetableQuantInCart;
			vegetable.setQuantity(stockedQuantity);
			vegetableMgmtRepository.save(vegetable);
		}
	}

	@Override
	public Order viewOrder(Order order) {
		int id = order.getOrderNo();
		Optional<Order> orderOptional = orderRepository.findById(id);
		if (!orderOptional.isPresent()) {
			throw new OrderNotFoundException("Order with id " + id + " doesn't exist");
		}
		return orderOptional.get();
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
		List<Order> orderList = orderRepository.findByCustomerId(custid);		
		if(orderList.isEmpty()) {
			throw new OrderNotFoundException("Orders not found");
		}
		return orderList;
	}
		
	

	@Override
	public List<Order> viewOrderList(LocalDate date) {
		List<Order> orderList = orderRepository.findByOrderDate(date);	
		if(orderList.isEmpty()) {
			throw new OrderNotFoundException("Orders not found");
		}
		return orderList;
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

package com.cg.vegetable.mgmt.service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

	
	/*
	 * 
	 * Saves order in database and reduces the vegetable stock quantity from cartVegetable.
	 * Bill is generated automatically when an order is placed.
	 * 
	 * @param order is Order
	 * @return saved Order
	 * 
	 * */

	@Transactional
	@Override
	public Order addOrder(Order order) {
		order.setStatus(OrderStatus.PLACED);
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
		List<Vegetable> orderVegList = vegetableList.stream().map(veg -> veg.getVegetable()).
				collect(Collectors.toList());
		order.setVegetableList(orderVegList);
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


	/*
	 * 
	 * View order based on order id
	 * 
	 * @param orderId is orderNo
	 * @return Order
	 * 
	 * */

	@Override
	public Order viewOrder(int orderId) {
		Optional<Order> orderOptional = orderRepository.findById(orderId);
		if (!orderOptional.isPresent()) {
			throw new OrderNotFoundException("Order with id " + orderId + " doesn't exist");
		}
		return orderOptional.get();
	}
	
	
	/*
	 * 
	 * Update order 
	 * 
	 * @param order is Order to be updated
	 * @return saved is updated Order
	 * 
	 * */

	
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
	
	/*
	 * 
	 * View all orders placed by a particular customer
	 * 
	 * @param custId is customerId whose orders have to be fetched
	 * @return orderList is the list of orders placed by a particular customer
	 * 
	 * */

	@Override
	public List<Order> viewAllOrders(int custId){
		List<Order> orderList = orderRepository.findByCustomerId(custId);		
		if(orderList.isEmpty()) {
			throw new OrderNotFoundException("Orders not found");
		}
		return orderList;
	}
		
	
	/*
	 * 
	 * View all orders placed on a particular date
	 * 
	 * @param date is Date 
	 * @return orderList is list of orders which are placed on a particular date
	 * 
	 * */

	@Override
	public List<Order> viewOrderList(LocalDate date) {
		List<Order> orderList = orderRepository.findByOrderDate(date);	
		if(orderList.isEmpty()) {
			throw new OrderNotFoundException("Orders not found");
		}
		return orderList;
	}
	
	/*
	 * 
	 * View the list of orders present in database  
	 * 
	 * @return orderList is the list of all the orders
	 * 
	 * */

	@Override
	public List<Order> viewOrderList() {
		List<Order> orderList = orderRepository.findAll();
		if(orderList.isEmpty()) {
			throw new OrderNotFoundException("Orders not found");
		}
		return orderList;
	}
	
	/*
	 * 
	 * Canceling an order based on orderId
	 * 
	 * @param orderId is the orderId which has to be cancelled
	 *
	 * 
	 * */

	@Transactional
	@Override
	public Order cancelOrder(int orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		Order order = optionalOrder.get();
		orderRepository.deleteById(orderId);
		return order;
	}
	
	/*
	 * 
	 * Reduce the vegetable stock in database once the order is placed
	 * 
	 * @param cartVegetables is a collection of CartVegetables
	 * 
	 * */
	
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
	
	
	/*
	 * 
	 * Increase the vegetable stock in database once the order is placed
	 * 
	 * @param cartVegetables is a collection of CartVegetables
	 * 
	 * */
	
	public void increaseVegetableStockAfterCancellingOrder(Collection<CartVegetable>cartVegetables) {
		for(CartVegetable cartVegetable : cartVegetables) {
			int vegetableQuantityInCart = cartVegetable.getQuantity();
			Vegetable vegetable = cartVegetable.getVegetable();
			int stockedQuantity = vegetable.getQuantity();
			stockedQuantity =stockedQuantity + vegetableQuantityInCart;
			vegetable.setQuantity(stockedQuantity);
			vegetableMgmtRepository.save(vegetable);
		}
	}
	
	/*
	 * 
	 * Generates the time when order is placed
	 * 
	 * @return LocalDateTime.now() is current time 
	 * 
	 * */
	
	public LocalDate currentTime() {
		return LocalDate.now();
	}

}

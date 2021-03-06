package com.cg.vegetable.mgmt.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.entities.Order;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.service.ICartService;
import com.cg.vegetable.mgmt.service.ICustomerService;
import com.cg.vegetable.mgmt.service.IOrderService;
import com.cg.vegetable.mgmt.service.IVegetableMgmtService;

@Component
public class OrderUI {
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IVegetableMgmtService vegetableService;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private ICartService cartService;
	
	public void start() {
		
		/*
		 * Creating Address object
		 * 
		 */

		Address address1 = new Address();
		address1.setFlatNo("304");
		address1.setBuildingName("RadhaKrishna Apartments");
		address1.setArea("Phase 6");
		address1.setCity("Chandigarh");
		address1.setState("Punjab");
		address1.setPincode("123456");
		
		Address address2 = new Address();
		address2.setFlatNo("176");
		address2.setBuildingName("Sushma Towers");
		address2.setArea("Phase 19");
		address2.setCity("Zirakpur");
		address2.setState("Punjab");
		address2.setPincode("987654");
		
		Address address3 = new Address();
		address3.setFlatNo("567");
		address3.setBuildingName("ABC Towers");
		address3.setArea("Phase 67");
		address3.setCity("Sector 9");
		address3.setState("Punjab");
		address3.setPincode("987754");
		/*
		 * Creating Vegetable object
		 * 
		 */

		
		Vegetable cabbage = vegetableService
				.addVegetable(new Vegetable("Cabbage", "allium", "aboveground", 30.0, 10));
		Vegetable capsicum = vegetableService
				.addVegetable(new Vegetable("Capsicum", "cruciferous","aboveground",  50.0, 5));
		Vegetable onion = vegetableService
				.addVegetable(new Vegetable("Onion","root", "aboveground",  40.0, 3));
		
		
		/*
		 * Creating Customer object
		 * 
		 */
		
		Customer srinidhiCustomer = new Customer("Srinidhi", "6666666666", "srinidhi@gmail.com", address2);
		customerService.addCustomer(srinidhiCustomer);
		
		Customer pallaviCustomer = new Customer("Pallavi", "8888888888", "pallavigupta@gmail.com", address1);
		customerService.addCustomer(pallaviCustomer);
		
		Customer sindhujaCustomer = new Customer("Sindhuja", "8789809809", "sindhuja@gmail.com", address3);
		customerService.addCustomer(sindhujaCustomer);
		
		
		
		
		/*
		 * 
		 * Adding something to cart
		 * 
		 * */
		
		Vegetable srinidhiCart = cartService.addToCart(srinidhiCustomer.getCustomerId(), onion);
		Vegetable pallaviCart = cartService.addToCart(pallaviCustomer.getCustomerId(), cabbage);	
		pallaviCart = cartService.addToCart(pallaviCustomer.getCustomerId(), capsicum);
		Vegetable sindhujaCart = cartService.addToCart(sindhujaCustomer.getCustomerId(), capsicum);
		
		
		/*
		 * Creating Order object
		 * 
		 */
		
		Order srinidhiOrder = new Order();
		srinidhiOrder.setCustomerId(srinidhiCustomer.getCustomerId());
		
		List<Vegetable> srinidhiVegetableList = cartService.viewAllItems(srinidhiCustomer.getCart());
		srinidhiOrder.setVegetableList(srinidhiVegetableList);
		
		Order pallaviOrder = new Order();
		pallaviOrder.setCustomerId(pallaviCustomer.getCustomerId());
		List<Vegetable> pallaviVegetableList = cartService.viewAllItems(pallaviCustomer.getCart());
		pallaviOrder.setVegetableList(pallaviVegetableList);
		
		
		/*
		 * Adding order to Order Repository - Placing an order
		 * 
		 */
		
		System.out.println();
		System.out.println("****************************");
		System.out.println("Adding an order to repository\n");
		
		srinidhiOrder = orderService.addOrder(srinidhiOrder);
		System.out.println("Order Placed for customer "+srinidhiCustomer.getName());
		
		pallaviOrder = orderService.addOrder(pallaviOrder);
		System.out.println("Order Placed for customer "+pallaviCustomer.getName());
		
		/*
		 * Viewing order by passing order object
		 *  
		 * */
		
		System.out.println();
		System.out.println("Viewing order by passing order object\n");
		
		Order fetchedOrder = orderService.viewOrder(pallaviOrder.getOrderNo());
		displayOrderDetails(fetchedOrder);
		
		/*
		 * Updating order details
		 * 
		 * */
		
//		System.out.println();
//		System.out.println("Updating order details\n");
//		
//		List<Vegetable> updateList = srinidhiOrder.getVegetableList();
//		updateList.add(capsicum);
//		
//		double updateAmount = srinidhiOrder.getTotalAmount() + 30;
//		
//		srinidhiOrder.setTotalAmount(updateAmount);
//		srinidhiOrder.setVegetableList(updateList);
//		
//		srinidhiOrder = orderService.updateOrderDetails(srinidhiOrder);
//		
//		displayOrderDetails(srinidhiOrder);
//		
		
		/*
		 *View All Orders by passing customer id
		 *
		 * */
		
		System.out.println();
		System.out.println("Viewing All Orders placed by a particular customer\n");
		
		int pallaviId = pallaviCustomer.getCustomerId();
		List<Order> orderList = orderService.viewAllOrders(pallaviId);
		for(Order order : orderList) {
			displayOrderDetails(order);
		}
		
		
		/*
		 * View all customer by passing date as a parameter
		 * 
		 * */
		
		System.out.println();
		System.out.println("Viewing All Orders placed on a particular date\n");

		

		LocalDate date = LocalDate.now();
		List<Order> desiredList = orderService.viewOrderList(date);
		for(Order order : desiredList) {
			displayOrderDetails(order);
		}
		
		
		/*
		 * View all customer by passing date
		 * 
		 * */
		
		System.out.println();
		System.out.println("Viewing All Orders placed\n");
		
		orderList = orderService.viewOrderList();
		for(Order order : orderList) {
			displayOrderDetails(order);
		}
		
		/*
		 * Cancelling an order
		 * 
		 * */
		
//		System.out.println();
//		System.out.println("Cancelling an order by order id");
//		orderService.cancelOrder(pallaviOrder.getOrderNo());
		
		
	}
	
	/*
	 * Displaying order details
	 * 
	 * */
	
	public void displayOrderDetails(Order order) {

		
		if(order.getVegetableList().isEmpty()) {
			System.out.println("Order "+order.getOrderNo() + " Details:"+"\nCustomer Id: "+ order.getCustomerId()
								+"\nDate Order Placed: " + order.getOrderDate()
								+ "\nAmount: "+ order.getTotalAmount()+" \nStatus"+ order.getStatus()+"\nOrder Id:"
								+ order.getOrderNo());
		}
		else {
			System.out.println("Order "+order.getOrderNo() + " Details:\nDate Order Placed: " + order.getOrderDate()
								+ "\nAmount: "+ order.getTotalAmount()+" \nStatus: "+ order.getStatus()+"\nOrder Id:"
								+ order.getOrderNo());
			List<Vegetable> orderVegList = order.getVegetableList();
			for(Vegetable vegetable : orderVegList) {
				displayVegetableDetails(vegetable);
			}
		}
		
	}
	
	/*
	 * Displaying Vegetable Details
	 * 
	 * */
	
	public void displayVegetableDetails(Vegetable vegetable) {

		System.out.println("\nVeg ID:" + vegetable.getVegId() + "\nName:" + vegetable.getName() + "\nCategory: "
				+ vegetable.getCategory() + "\nType: " + vegetable.getType() + "\nPrice:" + vegetable.getPrice()
				+ "\nQuantity" + vegetable.getQuantity());
	}

}
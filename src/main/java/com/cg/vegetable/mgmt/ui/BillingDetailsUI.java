package com.cg.vegetable.mgmt.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.entities.Order;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.service.IBillingService;
import com.cg.vegetable.mgmt.service.ICartService;
import com.cg.vegetable.mgmt.service.ICustomerService;
import com.cg.vegetable.mgmt.service.IOrderService;
import com.cg.vegetable.mgmt.service.IVegetableMgmtService;

@Component
public class BillingDetailsUI {
	
	@Autowired
	IBillingService billingService;
	
	@Autowired
	private IVegetableMgmtService vegetableService;
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private ICustomerService customerService;
	
	public void start() {
		
		/*
		 * Creating address object
		 * 
		 * */
		
		Address address = new Address("109", "Grove Apartments", "Phase 6", "Sector 56", "Punjab", "675388");
		
		/*
		 * Creating customer object
		 * 
		 * */
		
		Customer tanishaCustomer = new Customer("Tanisha", "9999999999", "tanishagarg804@gmail.com");
		tanishaCustomer.setAddress(address);
		customerService.addCustomer(tanishaCustomer);
		
		/*
		 * Creating a vegetable object
		 * */
		Vegetable lettuce = vegetableService
				.addVegetable(new Vegetable("Lettuce", "Green Cabbage", "Lettuce", 30.0, 10));
		
		/*
		 * Adding the vegetable to cart
		 * */
		Vegetable tanishaCart = cartService.addToCart(tanishaCustomer.getCustomerId(), lettuce);
		
		/*
		 * Creating Order Object and placing it
		 * */
		Order tanishaOrder = new Order();
		tanishaOrder.setCustomerId(tanishaCustomer.getCustomerId());		
		List<Vegetable> tanishaVegetableList = cartService.viewAllItems(tanishaCustomer.getCart());
		tanishaOrder.setVegetableList(tanishaVegetableList);
		tanishaOrder = orderService.addOrder(tanishaOrder);
		
		/*
		 * Adding a bill to the repository
		 * 
		 * */
		
		System.out.println();
		System.out.println("****************************");
		System.out.println();
		System.out.println("Adding a bill to repository");
		BillingDetails bill1 = new BillingDetails(tanishaOrder.getOrderNo(), "COD", "Successful");
		bill1.setAddress(tanishaCustomer.getAddress());
		BillingDetails addBill1 = billingService.addBill(bill1);
		displayBill(addBill1);
		
		/*
		 * Updating a bill
		 * 
		 * */

		System.out.println();
		System.out.println("Updating a bill");
		bill1.setTransactionMode("UPI");
		BillingDetails updatedBill = billingService.updateBill(bill1);
		displayBill(updatedBill);

		/*
		 * Finding a bill by passing billingId
		 * 
		 * */
		
		System.out.println();
		System.out.println("Finding a bill with billingId");
		int bill1Id = bill1.getBillingId();
		BillingDetails viewBill1 = billingService.viewBill(bill1Id);
		displayBill(viewBill1);
		
	}
	
	/*
	 * Displaying Bill Details
	 * 
	 * */
	
	public void displayBill(BillingDetails bill) {

		if (bill.getAddress() != null) {
			System.out.println("Bill " + bill.getBillingId() + " Details: " + "\nOrderId: " + bill.getOrderId()
					+ "\nTransaction Mode: " + bill.getTransactionMode() + "\nTransaction Date: "
					+ bill.getTransactionDate() + "\nTransaction Status: " + bill.getTransactionStatus() + "\n Address:"
					+ bill.getAddress().getFlatNo() + ", " + bill.getAddress().getBuildingName() + ", "
					+ bill.getAddress().getArea() + "\n" + bill.getAddress().getCity() + ", "
					+ bill.getAddress().getState() + ", " + bill.getAddress().getPincode());
		}

		else {
			System.out.println("Bill " + bill.getBillingId() + " Details: " + "\nOrderId: " + bill.getOrderId()
					+ "\nTransaction Mode: " + bill.getTransactionMode() + "\nTransaction Date: "
					+ bill.getTransactionDate() + "\nTransaction Status: " + bill.getTransactionStatus());
			System.out.println();
		}

	}

}

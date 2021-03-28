package com.cg.vegetable.mgmt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.service.IBillingService;
import com.cg.vegetable.mgmt.service.IOrderService;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.service.ICustomerService;


@Component
public class OnlineVegetableShoppingUI {

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	IBillingService billingService;
	
	public void start() {
		
		try {
			
			System.out.println("Adding a bill to repository");
			BillingDetails bill1 = new BillingDetails(1, "COD", "Successful");
			BillingDetails addBill1 = billingService.addBill(bill1);
			displayBill(addBill1);
			
			System.out.println("****************************");
			System.out.println("Updating a bill");
			bill1.setTransactionMode("UPI");
			BillingDetails updatedBill = billingService.updateBill(bill1);
			displayBill(bill1);
			
			System.out.println("****************************");
			System.out.println("Finding a bill with billingId");
			int bill1Id = bill1.getBillingId();
			BillingDetails viewBill1 = billingService.viewBill(bill1Id);
			displayBill(viewBill1);
			
		}catch(BillNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(InvalidTransactionModeException e) {
			System.out.println(e.getMessage());
		}catch(InvalidTransactionStatusException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println("Sorry, Something went wrong");
		}
		
		
		//Customers 
		
		try {
		System.out.println("   Adding Customers      ");
		Customer customer1 =customerService.addCustomer(new Customer(1,"Ayesha","987654321","abc@def.com"));
		customerService.addCustomer(customer1);
		display(customer1);
		
		Customer customer2 = customerService.addCustomer(new Customer(2,"Shivangi","9456704321","ghikhsa@def.com"));
		customerService.addCustomer(customer2);
		display(customer2);
		
		Customer customer3 = customerService.addCustomer(new Customer(3,"Aashi","7977904321","hdwtetwxnna@def.com"));
		customerService.addCustomer(customer3);
		display(customer3);
		
		System.out.println("    \n   View  Customer \n      ");

		Customer customerDetails1 = customerService.viewCustomer(customer1);
		display(customerDetails1);
		
		Customer customerDetails2 = customerService.viewCustomer(customer2);
		display(customerDetails2);
		
		Customer customerDetails3 = customerService.viewCustomer(customer3);
		display(customerDetails3);
		
		
		
		System.out.println("      \n   Remove Customer \n     ");

		Customer removedCustomer = customerService.removeCustomer(customer2);
		System.out.println( "Customer " +removedCustomer+ " removed successfully.");
		
		
		
		System.out.println("         \n  Update   customer   \n");
		}
		catch(CustomerNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(CustomerIdNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(InvalidCustomerNameException e) {
			System.out.println(e.getMessage());
		}catch(InvalidMobileNumberException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println("Sorry, Something went wrong");
		}
		
		
		
		
	}
	
	public void displayBill(BillingDetails bill) {
		System.out.println("Bill "+bill.getBillingId()+" Details: "+
						   "\nOrderId: "+bill.getOrderId()+
						   "\nTransaction Mode: "+bill.getTransactionMode()+
						   "\nTransaction Date: "+bill.getTransactionDate()+
						   "\nTransaction Status: "+bill.getTransactionStatus());
		System.out.println();
	}
	
	void  display(Customer customer){		
			System.out.println("Customer Id: " + customer.getCustomerId() +
					" \n Customer Name: " + customer.getName() +
					"\n Customer Mobile Number: "+ customer.getMobileNumber()+
					"\n Customer EmailId: "+ customer.getEmailId()+
					"\n\n");
		}
	

}
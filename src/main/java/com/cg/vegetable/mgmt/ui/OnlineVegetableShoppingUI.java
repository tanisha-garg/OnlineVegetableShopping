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
		
		
		/*
		 * Creating Address object for Customer and Billing Module
		 * */
		
		Address address1 = new Address("304", "RadhaKrishna Apartments", "Phase 6", "Chandigarh", "Punjab", "123456");
		Address address2 = new Address("176", "Sushma Towers", "Phase 19", "Zirakpur", "Punjab", "987654");

		

		/*
		 * Manual Testing of Billing Module
		 */

		System.out.println("Adding a bill to repository");
		BillingDetails bill1 = new BillingDetails(1, "COD", "Successful");
		//bill1.setAddress(address1);
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
		
		/*
		 * End of testing for billing module
		 * */

		
		/*
		 * Manual Testing for Customer Module
		 * */
		
		Customer ayesha = customerService.addCustomer(new Customer("Ayesha", "987654321", "abc@def.com"));
		displayCustomer(ayesha);

		System.out.println("View  Customer \n");

		Customer anu = customerService.viewCustomer(ayesha);
		displayCustomer(anu);

		System.out.println(" Remove Customer \n");
		// Customer shivangi = service.removeCustomer(ayesha);
		
		
		
		/*
		 * End of manual testing for Customer module
		 * */

	}

	public void displayBill(BillingDetails bill) {
		
		if(bill.getAddress()!= null) {
			System.out.println("Bill " + bill.getBillingId() + " Details: " + "\nOrderId: " + bill.getOrderId()
			+ "\nTransaction Mode: " + bill.getTransactionMode() + "\nTransaction Date: "
			+ bill.getTransactionDate() + "\nTransaction Status: " + bill.getTransactionStatus() + "\n Address:"+
			bill.getAddress().getFlatNo()+", "+bill.getAddress().getBuildingName()+", "+bill.getAddress().getArea()+
			"\n"+bill.getAddress().getCity()+", "+bill.getAddress().getState()+", "+bill.getAddress().getPincode());
		}
		
		else {
			System.out.println("Bill " + bill.getBillingId() + " Details: " + "\nOrderId: " + bill.getOrderId()
			+ "\nTransaction Mode: " + bill.getTransactionMode() + "\nTransaction Date: "
			+ bill.getTransactionDate() + "\nTransaction Status: " + bill.getTransactionStatus());
			System.out.println();
		}
		
	}

	void displayCustomer(Customer customer) {
		System.out.println("Customer \t" + customer.getCustomerId() + "\t " + customer.getName() + "\t"
				+ customer.getMobileNumber() + "\t" + customer.getEmailId());
	}

}
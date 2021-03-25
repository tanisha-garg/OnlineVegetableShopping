package com.cg.vegetable.mgmt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.service.IBillingService;
import com.cg.vegetable.mgmt.service.IOrderService;

@Component
public class OnlineVegetableShoppingUI {
	
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
		
		
	}
	
	public void displayBill(BillingDetails bill) {
		System.out.println("Bill "+bill.getBillingId()+" Details: "+
						   "\nOrderId: "+bill.getOrderId()+
						   "\nTransaction Mode: "+bill.getTransactionMode()+
						   "\nTransaction Date: "+bill.getTransactionDate()+
						   "\nTransaction Status: "+bill.getTransactionStatus());
		System.out.println();
	}

}

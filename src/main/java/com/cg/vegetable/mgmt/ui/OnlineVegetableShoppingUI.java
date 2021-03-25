package com.cg.vegetable.mgmt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.BillingDetails;
import com.cg.vegetable.mgmt.service.IBillingService;
import com.cg.vegetable.mgmt.service.IOrderService;

@Component
public class OnlineVegetableShoppingUI {
	
	@Autowired
	IBillingService billingService;
	
	public void start() {
		
		BillingDetails bill1 = new BillingDetails(1, "UPI", "Successful");
		BillingDetails addBill1 = billingService.addBill(bill1);
		displayBill(addBill1);
		
	}
	
	public void displayBill(BillingDetails bill) {
		System.out.println("Bill "+bill.getBillingId()+" Details: "+
						   "\nOrderId: "+bill.getOrderId()+
						   "\nTransaction Mode: "+bill.getTransactionMode()+
						   "\nTransaction Date: "+bill.getTransactionDate()+
						   "\nTransaction Status: "+bill.getTransactionStatus());
	}

}

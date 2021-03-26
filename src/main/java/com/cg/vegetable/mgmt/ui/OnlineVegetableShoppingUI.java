package com.cg.vegetable.mgmt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.service.ICustomerService;

@Component
public class OnlineVegetableShoppingUI {
	
	@Autowired
	private ICustomerService service;
	
	public void start() {
	System.out.println(" Adding Customer \n");

	Customer ayesha = service.addCustomer(1,"Ayesha","987654321","plot2 , Dattani Nagar , AP","abc@def.com");
	//display(ayesha);

	Customer shivangi = service.addCustomer("Shivangi");
	//display(shivangi);

	}
}

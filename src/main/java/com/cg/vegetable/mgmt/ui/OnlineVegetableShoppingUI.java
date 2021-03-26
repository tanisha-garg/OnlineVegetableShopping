package com.cg.vegetable.mgmt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.service.ICustomerService;

@Component
public class OnlineVegetableShoppingUI {
	
	@Autowired
	private ICustomerService service;
	
	public void start() {
	System.out.println(" Adding Customer \n");

	Customer ayesha = service.addCustomer(new Customer("Ayesha","987654321","abc@def.com","pdbgddgcbb","pdbgddgcbb"));
	display(ayesha);
	
	System.out.println("View  Customer \n");

	Customer anu = service.viewCustomer(ayesha);
	display(anu);
	
	System.out.println(" Remove Customer \n");

	Customer shivangi = service.removeCustomer(ayesha);
	}
	
	
	void  display(Customer customer){		
		System.out.println("Customer \t" + customer.getCustomerId() + "\t " + customer.getName() + "\t"
	+customer.getMobileNumber()	+"\t"+customer.getEmailId()+"\t"+customer.getPassword()+"\t"+customer.getConfirmPassword());
		}

	
	}


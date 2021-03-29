package com.cg.vegetable.mgmt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.service.ICustomerService;

@Component
public class CustomerUI {
	
	@Autowired
	private ICustomerService customerService;
	
	public void start() {
		
		Customer ayesha = customerService.addCustomer(new Customer("Ayesha", "987654321", "abc@def.com"));
		displayCustomer(ayesha);

		System.out.println("View  Customer \n");

		Customer anu = customerService.viewCustomer(ayesha.getCustomerId());
		displayCustomer(anu);

		//System.out.println(" Remove Customer \n");
		// Customer shivangi = service.removeCustomer(ayesha);
		
	}
	
	void displayCustomer(Customer customer) {
		System.out.println("Customer \t" + customer.getCustomerId() + "\t " + customer.getName() + "\t"
				+ customer.getMobileNumber() + "\t" + customer.getEmailId());
	}

}

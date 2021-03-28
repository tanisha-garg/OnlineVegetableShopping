package com.cg.vegetable.mgmt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.service.ICartService;
import com.cg.vegetable.mgmt.service.ICustomerService;
import com.cg.vegetable.mgmt.service.IVegetableMgmtService;

@Component
public class CartUI {
	
	@Autowired
	ICartService cartService;
	
	@Autowired
	IVegetableMgmtService vegetableService;
	
	@Autowired
	ICustomerService customerService;
	
	public void start() {
		/*
		 * Adding to cart
		 */
		
		Address address1 = new Address("123", "abc Apartments", "sss 3", "Chennai", "Tamil Nadu", "123777");
		Vegetable veg = vegetableService.viewVegetable(3);
		Customer nidhiCustomer = new Customer("nidhi", "167782889", "nidhi@gmail.com");
		nidhiCustomer.setAddress(address1);
		customerService.addCustomer(nidhiCustomer);
		
//		Customer cus = customerService.
		 Vegetable cart1 = cartService.addToCart(nidhiCustomer.getCustomerId(), veg);
		 
		 displayCart(cart1);
	}
	
	public void displayCart(Vegetable cart) {
		System.out.println("Cart Details: "+ cart.getCart().getCartId());
	}
	
	

}

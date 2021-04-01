package com.cg.vegetable.mgmt.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.CartVegetable;
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
		Vegetable veg = vegetableService.viewVegetable(10);
		Customer nidhiCustomer = new Customer("nidhi", "167782889", "nidhi@gmail.com");
		nidhiCustomer.setAddress(address1);
		customerService.addCustomer(nidhiCustomer);
		
		/**
		 * Increase quantity
		 */
		 int id = nidhiCustomer.getCustomerId();
		 cartService.addToCart(id, veg);
		 Cart cart = cartService.increaseVegQuantity(id, veg.getVegId(), 10);
		 CartVegetable cartVeg = cartService.findCartVegetableAndQuantity(id,veg.getVegId());
		 displayIncreasedVegetable(cartVeg);
		
		/**
		 * Decrease quantity
		 */
		
		 int id2 = nidhiCustomer.getCustomerId();
		 cartService.addToCart(id2, veg);
		 Cart cart2 = cartService.decreaseVegQuantity(id, veg.getVegId(), 3);
		 CartVegetable cartVeg2 = cartService.findCartVegetableAndQuantity(id2,veg.getVegId());
		 displayDecreasedVegetable(cartVeg);
		
		/**
		 * Remove Vegetable
		 */
//		 int id3= nidhiCustomer.getCustomerId();
//		 cartService.addToCart(id3, veg);
//		 Cart cart3 = cartService.removeVegetable(id3, veg.getVegId());
//		 CartVegetable cartVeg3 = cartService.findCartVegetableAndQuantity(id3, veg.getVegId());
//		 displayRemovedVegetable(cartVeg);
		
		
		/**
		 * View All Vegetable
		 */
//		Customer cus = customerService.
		 Vegetable veg1 = cartService.addToCart(nidhiCustomer.getCustomerId(), veg);
		 System.out.println("****Printing cart vegetables***********");
		 displayVegetableValue(veg1);
		 
	}
	
	private void displayRemovedVegetable(CartVegetable cartVeg) {
		System.out.println(cartVeg.getQuantity());
		
	}
	
	
    private void displayDecreasedVegetable(CartVegetable cartVeg) {
		System.out.println(cartVeg.getQuantity());
		
	}
	public void displayVegetableValue(Vegetable vegVal){
    	System.out.println(vegVal.getName());
    }
    
    public void displayIncreasedVegetable(CartVegetable cart){
    	System.out.println(cart.getQuantity());
    }
	

}

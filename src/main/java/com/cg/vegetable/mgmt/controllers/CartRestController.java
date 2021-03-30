package com.cg.vegetable.mgmt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vegetable.mgmt.dto.AddVegetableToCart;
import com.cg.vegetable.mgmt.dto.CartDetails;
import com.cg.vegetable.mgmt.dto.RemoveVegetableFromCart;
import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.repository.ICartRepository;
import com.cg.vegetable.mgmt.service.ICartService;
import com.cg.vegetable.mgmt.util.CartUtil;

@RequestMapping("/cart")
@RestController
public class CartRestController {
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private CartUtil cartUtil;
    
	@Autowired
	private ICartRepository cartRepository;
	
	
	@PostMapping("/addingItem")
	public CartDetails addVegetable(@PathVariable AddVegetableToCart requestData) {
		return null;
	}
//	@PutMapping("")
	
	@DeleteMapping("/removeVegetable/{custId}")
	public String removeVegetable(@PathVariable RemoveVegetableFromCart requestData) {
		Cart cartVal = cartService.removeVegetable(requestData.getCustId(), requestData.getVegId());
		return "Vegetable removed for the customer id"+requestData.getCustId();
	}
	
	
	
}

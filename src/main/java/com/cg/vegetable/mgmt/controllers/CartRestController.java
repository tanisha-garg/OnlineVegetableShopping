package com.cg.vegetable.mgmt.controllers;

import com.cg.vegetable.mgmt.dto.CartVegetableDetails;
import com.cg.vegetable.mgmt.entities.CartVegetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.vegetable.mgmt.dto.AddVegetableToCart;
import com.cg.vegetable.mgmt.dto.CartDetails;
import com.cg.vegetable.mgmt.dto.RemoveVegetableFromCart;
import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.repository.ICartRepository;
import com.cg.vegetable.mgmt.service.ICartService;
import com.cg.vegetable.mgmt.util.CartUtil;

import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartRestController {
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private CartUtil cartUtil;
    
	@Autowired
	private ICartRepository cartRepository;
	
	
	@PostMapping("/add")
	public CartDetails addVegetable(@PathVariable AddVegetableToCart requestData) {
		return null;
	}
//	@PutMapping("")
	
	@DeleteMapping("/removeVegetable/{custId}")
	public String removeVegetable(@PathVariable RemoveVegetableFromCart requestData) {
		Cart cartVal = cartService.removeVegetable(requestData.getCustId(), requestData.getVegId());
		return "Vegetable removed for the customer id"+requestData.getCustId();
	}


	@GetMapping("/customer/{customerId}")
	public CartDetails fetchCartDetails(@PathVariable("customerId") int customerId){
		Cart cart=cartService.findCartByCustomerId(customerId);
		List<CartVegetable> vegetables=cartService.findCartVegetablesAndQuantity(cart);
		List<CartVegetableDetails>vegetablesDetails=cartUtil.toCarVegetablesList(vegetables);
		CartDetails cartDetails=cartUtil.toDetails(cart,vegetablesDetails);
		return cartDetails;
	}
	
	
}

package com.cg.vegetable.mgmt.controllers;

import com.cg.vegetable.mgmt.dto.CartVegetableDetails;
import com.cg.vegetable.mgmt.dto.DecreaseQuantityOfVegetable;
import com.cg.vegetable.mgmt.dto.IncreaseQuantityOfVegetable;
import com.cg.vegetable.mgmt.dto.RemoveAllVegetablesFromCart;
import com.cg.vegetable.mgmt.entities.CartVegetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.vegetable.mgmt.dto.AddVegetableToCart;
import com.cg.vegetable.mgmt.dto.CartDetails;
import com.cg.vegetable.mgmt.dto.RemoveVegetableFromCart;
import com.cg.vegetable.mgmt.dto.VegetableDetails;
import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.repository.ICartRepository;
import com.cg.vegetable.mgmt.service.ICartService;
import com.cg.vegetable.mgmt.service.IVegetableMgmtService;
import com.cg.vegetable.mgmt.util.CartUtil;
import com.cg.vegetable.mgmt.util.VegetableUtil;

import java.util.List;

@RequestMapping("/cart")
@RestController
public class CartRestController {
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private CartUtil cartUtil;
    
    @Autowired
    private VegetableUtil vegUtil;
	
	
	@Autowired
	private IVegetableMgmtService vegService;
	
	
	
	@PostMapping("/add")
	public VegetableDetails addVegetable(@PathVariable AddVegetableToCart requestData) {
		Vegetable veg = vegService.viewVegetable(requestData.getVegId());
		Vegetable vegetable = cartService.addToCart(requestData.getCustId(), veg);
		return vegUtil.toDetails(vegetable);
	}
	
	
    @PutMapping("/increasequantity")
	public CartVegetableDetails increasequantity(@RequestBody IncreaseQuantityOfVegetable requestData) {
		Cart cart = cartService.increaseVegQuantity(requestData.getCustId(), requestData.getVegId(), requestData.getQuantity());
		CartVegetable cartVeg = cartService.findCartVegetableAndQuantity(requestData.getCustId(), requestData.getVegId());
	    return cartUtil.toCartVegetableDetails(cartVeg);
		
	}
	
	
    @PutMapping("/decreasequantity")
    public CartVegetableDetails decreasequantity(@RequestBody DecreaseQuantityOfVegetable requestData) {
    	Cart cart = cartService.decreaseVegQuantity(requestData.getCustId(), requestData.getVegId(), requestData.getQuantity());
		CartVegetable cartVeg = cartService.findCartVegetableAndQuantity(requestData.getCustId(), requestData.getVegId());
	    return cartUtil.toCartVegetableDetails(cartVeg);
    }
	
	@DeleteMapping("/removeVegetable/{custId}")
	public String removeVegetable(@PathVariable RemoveVegetableFromCart requestData) {
		Cart cartVal = cartService.removeVegetable(requestData.getCustId(), requestData.getVegId());
		return "Vegetable removed for the customer id"+requestData.getCustId();
	}
	
	@DeleteMapping("/removeAllVegetable")
	public String removeAllVegetable(@PathVariable RemoveAllVegetablesFromCart requestData) {
		Cart cartVal = cartService.findCartByCustomerId(requestData.getCustId());
		Cart removed = cartService.removeAllVegetables(cartVal);
		return "removeAllVegetable"+requestData.getCustId();
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

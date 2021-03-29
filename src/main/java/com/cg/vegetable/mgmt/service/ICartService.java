package com.cg.vegetable.mgmt.service;

import java.util.List;

import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Vegetable;

public interface ICartService {

	public Vegetable addToCart(int customer,Vegetable veg);
	public Cart increaseVegQuantity(int custId, int vegid,int quantity);
	public Cart decreaseVegQuantity(int custId, int vegid,int quantity);
	public Cart removeVegetable(int customerId, int vegid);
	public Cart removeAllVegetables(Cart cart);
	public List<Vegetable> viewAllItems(Cart cart);
	
}

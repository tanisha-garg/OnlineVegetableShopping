package com.cg.vegetable.mgmt.repository;

import java.util.List;

import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Vegetable;

public interface ICartRepository {

	public Vegetable addToCart(Vegetable item);
	public Vegetable updateItemQuantity(Vegetable id,int quantity);
	public List<Vegetable> viewAllItems(Cart cart);
	public Cart removeAllItems(Cart cart);
	
}

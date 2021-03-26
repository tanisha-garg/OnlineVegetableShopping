package com.cg.vegetable.mgmt.service;

import java.util.List;

import com.cg.vegetable.mgmt.entities.CartDTO;
import com.cg.vegetable.mgmt.entities.Vegetable;

public interface ICartService {

	public Vegetable addToCart(Vegetable item);
	public Vegetable updateItemQuantity(Vegetable id,int quantity);
	public List<Vegetable> viewAllItems(CartDTO cart);
	public CartDTO removeAllItems(CartDTO cart);
	
}

package com.cg.vegetable.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Vegetable;

public interface ICartRepository extends JpaRepository<Cart,Integer> {
	
	Cart findCartByCustId(Integer custId);
//
//	public Vegetable addToCart(Vegetable item);
//	public Vegetable updateItemQuantity(Vegetable id,int quantity);
//	public List<Vegetable> viewAllItems(Cart cart);
//	public Cart removeAllItems(Cart cart);
	
}

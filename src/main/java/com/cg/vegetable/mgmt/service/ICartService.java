package com.cg.vegetable.mgmt.service;

import java.util.List;

import com.cg.vegetable.mgmt.entities.CartDTO;
import com.cg.vegetable.mgmt.entities.VegetableDTO;

public interface ICartService {

	public VegetableDTO addToCart(VegetableDTO item);
	public VegetableDTO updateItemQuantity(VegetableDTO id,int quantity);
	public List<VegetableDTO> viewAllItems(CartDTO cart);
	public CartDTO removeAllItems(CartDTO cart);
	
}

package com.cg.vegetable.mgmt.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.cg.vegetable.mgmt.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.exceptions.CartIsEmptyException;
import com.cg.vegetable.mgmt.exceptions.CustomerNotFoundException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableQuantityException;
import com.cg.vegetable.mgmt.exceptions.VegValAtleastOneException;
import com.cg.vegetable.mgmt.exceptions.VegetableIsNullException;
import com.cg.vegetable.mgmt.exceptions.VegetableMustHaveValueException;
import com.cg.vegetable.mgmt.exceptions.VegetableNotFoundException;
import com.cg.vegetable.mgmt.repository.ICartRepository;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;
import com.cg.vegetable.mgmt.repository.IVegetableMgmtRepository;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private ICartRepository cartRepository;

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IVegetableMgmtRepository vegRepository;



	@Override
	public Vegetable addToCart(int customerId, Vegetable vegetable) {
		Optional<Vegetable> vegOptional = vegRepository.findById(vegetable.getVegId());
		if (vegOptional.isEmpty()) {
			throw new VegetableNotFoundException("vegetable not found for this id");
		}

		Vegetable veg = vegOptional.get();
		Cart cart = cartRepository.findCartByCustId(customerId);

		List<Vegetable> items = cart.getVegetables(); // suspected
		if (items == null) {
			items = new ArrayList<>();
			cart.setVegetables(items);
		}

		if (!items.contains(vegetable)) {
			items.add(veg);
			cart.setVegetables(items);
			cartRepository.save(cart);

		} else {
			increaseVegQuantity(vegetable.getVegId(), vegetable.getQuantity());
		}
		return vegetable;
	}

	 

	@Override
	public Cart increaseVegQuantity(int vegid, int quantity) {
//		Optional<Vegetable> incOptional = vegRepository.findById(vegid);
//		Vegetable veg = incOptional.get();
//		List<Vegetable> vegList = cart.getVegetables();
//		for (Vegetable vegetable : vegList) {
//			if (vegetable == veg) {
//				vegetable.setQuantity(quantity + vegetable.getQuantity());
//			}
//		}
//		return cartRepository.save(cart);
		return null;
	}

	@Override
	public Cart decreaseVegQuantity(int vegid, int quantity) {
		// TODO Auto-generated method stub
//		Optional<Vegetable> incOptional = vegRepository.findById(vegid);
//		Vegetable veg = incOptional.get();
	
//		List<Vegetable> vegList = cart.getVegetables();
//		for (Vegetable vegetable : vegList) {
//			if (vegetable == veg) {
//				vegetable.setQuantity(vegetable.getQuantity() - quantity);
//			}
////		}
//		return cartRepository.save(cart);
		return null;
	}

	@Override
	public Cart removeVegetable(Cart cart, int vegid) {
		Optional<Vegetable> optional = vegRepository.findById(vegid);
		if (!optional.isPresent()) {
			throw new VegetableNotFoundException("Vegetable to be removed does not exist");
		}
		Vegetable removed = optional.get();
		List<Vegetable> vegList = cart.getVegetables();
		Iterator vegItr = vegList.iterator();
		for (Vegetable v : vegList) {
			if (v == removed) {
				vegItr.remove();
			}
		}
		return cartRepository.save(cart);

	}

	@Override
	public Cart removeAllVegetables(Cart cart) {
		List<Vegetable> allVeg = cart.getVegetables();
		allVeg.removeAll(allVeg);
		return cartRepository.save(cart);

	}

	@Override
	public List<Vegetable> viewAllItems(Cart cart) {
		List<Vegetable> allVeg = cart.getVegetables();
		return allVeg;
	}
	
	
	
	
	

	public void validateVegetable(Vegetable vegetable) {
		if (vegetable == null) {
			throw new VegetableIsNullException("Vegetable can't be null ");
		}

	}

//
	public void validateQuantity(int quantity) {
		if (quantity <= 0) {
			throw new InvalidVegetableQuantityException(" Quantity can't be zero or negative");
		}

	}

//
//
	public void validateInc(int quantity) {
		// TODO Auto-generated method stub
		if (quantity <= 1) {
			throw new VegetableMustHaveValueException("Cart Must have the value");
		}

	}

	public void validateDec(int value) {
		// TODO Auto-generated method stub
		if (value >= 1) {
			throw new VegetableMustHaveValueException("Cart Must have the value");
		}

	}

	public void validateId(int val) {
		if (val < 1 || val == 0) {
			throw new VegValAtleastOneException("To Remove the value should be 1 or above");
		}
	}
//
//
//	

//	public void addToCart(CartDTO cart) {
//		// TODO Auto-generated method stub
//		if(cart == null) {
//			throw new CartIsEmptyException("Cart is null");
//		}
//	}

}

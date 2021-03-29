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
import com.cg.vegetable.mgmt.entities.CartVegetable;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.exceptions.CartException;
import com.cg.vegetable.mgmt.exceptions.CartIsEmptyException;
import com.cg.vegetable.mgmt.exceptions.CustomerNotFoundException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableQuantityException;
import com.cg.vegetable.mgmt.exceptions.VegValAtleastOneException;
import com.cg.vegetable.mgmt.exceptions.VegetableIsNullException;
import com.cg.vegetable.mgmt.exceptions.VegetableMustHaveValueException;
import com.cg.vegetable.mgmt.exceptions.VegetableNotFoundException;
import com.cg.vegetable.mgmt.repository.ICartRepository;
import com.cg.vegetable.mgmt.repository.ICartVegetableRepository;
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

	@Autowired
	private ICartVegetableRepository cartVegetableRepository;

	@Override
	public Vegetable addToCart(int customerId, Vegetable vegetable) {
		Optional<Vegetable> vegOptional = vegRepository.findById(vegetable.getVegId());
		if (!vegOptional.isPresent()) {
			throw new VegetableNotFoundException("vegetable not found for this id");
		}
		vegetable = vegOptional.get();
		Cart cart = cartRepository.findCartByCustId(customerId);
		boolean exists = cartVegetableRepository.existsByCartAndVegetable(cart, vegetable);
		if (exists) {
			CartVegetable cartVegetable = cartVegetableRepository.findCartVegetableByCartAndVegetable(cart, vegetable);
			int quantity = cartVegetable.getQuantity();
			quantity++;
			cartVegetable.setQuantity(quantity);
			cartVegetableRepository.save(cartVegetable);
			return vegetable;
		}
		CartVegetable cartVegetable = new CartVegetable(cart, vegetable, 1);
		cartVegetableRepository.save(cartVegetable);
		return vegetable;
	}

	@Override
	public Cart increaseVegQuantity(int customerId, int vegid, int quantityToBeAdded) {
		Optional<Vegetable> vegOptional = vegRepository.findById(vegid);
		if (!vegOptional.isPresent()) {
			throw new VegetableNotFoundException("vegetable not found for this id");
		}
		Vegetable vegetable = vegOptional.get();
		Cart cart = cartRepository.findCartByCustId(customerId);
		boolean exists = cartVegetableRepository.existsByCartAndVegetable(cart, vegetable);
		if (!exists) {
			throw new CartException("quantity can't be increased as vegetable is not found in cart");
		}
		CartVegetable cartVegetable = cartVegetableRepository.findCartVegetableByCartAndVegetable(cart, vegetable);
		int existingQuantity = cartVegetable.getQuantity();
		int updatedQuantity = existingQuantity + quantityToBeAdded;
		cartVegetable.setQuantity(updatedQuantity);
		cartVegetableRepository.save(cartVegetable);
		return cart;
	}

	@Override
	public Cart decreaseVegQuantity(int customerId, int vegid, int quantityToBeRemoved) {
		Optional<Vegetable> vegOptional = vegRepository.findById(vegid);
		if (!vegOptional.isPresent()) {
			throw new VegetableNotFoundException("vegetable not found for this id");
		}
		Vegetable vegetable = vegOptional.get();
		Cart cart = cartRepository.findCartByCustId(customerId);
		boolean exists = cartVegetableRepository.existsByCartAndVegetable(cart, vegetable);
		if (!exists) {
			throw new CartException("quantity can't be increased as vegetable is not found in cart");
		}
		CartVegetable cartVegetable = cartVegetableRepository.findCartVegetableByCartAndVegetable(cart, vegetable);
		int existingQuantity = cartVegetable.getQuantity();
		int updatedQuantity = existingQuantity - quantityToBeRemoved;
		if (updatedQuantity <= 0) {
			cartVegetableRepository.delete(cartVegetable);
		}
		cartVegetable.setQuantity(updatedQuantity);
		cartVegetableRepository.save(cartVegetable);
		return cart;
	}

	@Override
	public Cart removeVegetable(int customerId, int vegid) {
		Optional<Vegetable> vegOptional = vegRepository.findById(vegid);
		if (!vegOptional.isPresent()) {
			throw new VegetableNotFoundException("vegetable not found for this id");
		}
		Vegetable vegetable = vegOptional.get();
		Cart cart = cartRepository.findCartByCustId(customerId);
		cartVegetableRepository.deleteByCartAndVegetable(cart, vegetable);
		return cart;

	}

	@Override
	public Cart removeAllVegetables(Cart cart) {
		cartVegetableRepository.deleteByCart(cart);
		return cart;

	}

	@Override
	public List<Vegetable> viewAllItems(Cart cart) {
		List<Vegetable> allVeg = cartVegetableRepository.findVegetablesByCart(cart);
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

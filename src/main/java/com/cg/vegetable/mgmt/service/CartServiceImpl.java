package com.cg.vegetable.mgmt.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

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

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IVegetableMgmtService vegService;

	@Override
	public Vegetable addToCart(int customerId, Vegetable vegetable) {
		increaseVegQuantity(customerId, vegetable.getVegId(), 1);
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
			CartVegetable cartVegetable = new CartVegetable(cart, vegetable, quantityToBeAdded);
			cartVegetableRepository.save(cartVegetable);
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

	@Transactional
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

	@Override
	public List<CartVegetable> findCartVegetablesAndQuantity(Cart cart) {
		return cartVegetableRepository.findByCart(cart);
	}

	@Override
	public Cart findCartByCustomerId(int customerId) {
		Cart cart = cartRepository.findCartByCustId(customerId);
		return cart;
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


	@Override
	public CartVegetable findCartVegetableAndQuantity(int custId, int vegId) {
		Cart cart = findCartByCustomerId(custId);
		List<CartVegetable> allCartVeg = findCartVegetablesAndQuantity(cart);
		for(CartVegetable cartVeg: allCartVeg) {
			if(cartVeg.getCart().getCustId() == custId && cartVeg.getVegetable().getVegId() == vegId)  {
				return cartVeg;
			}
		}
		throw new VegetableNotFoundException("Vegetable Not found for this id");
	}


}

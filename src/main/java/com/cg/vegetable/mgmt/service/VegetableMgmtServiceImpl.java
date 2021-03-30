package com.cg.vegetable.mgmt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableCategoryException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableIdException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableNameException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetablePriceException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableQuantityException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableTypeException;
import com.cg.vegetable.mgmt.exceptions.VegetableNotFoundException;
import com.cg.vegetable.mgmt.repository.IVegetableMgmtRepository;

@Service
public class VegetableMgmtServiceImpl implements IVegetableMgmtService{
	
	@Autowired
	IVegetableMgmtRepository vegetableRepository;
	
	
	/*
	 * scenario : Vegetable saved successfully in database
	 * input: Vegetable type argument 	
	 * expectation:Vegetable should be added in database after validation
	 * 
	 */
	
	@Override
	public Vegetable addVegetable(Vegetable vegetable) {
		validateVegetable(vegetable);
		return  vegetableRepository.save(vegetable);		
	}

	
	
	/*
	 * scenario :   UpdatedVegetable saved successfully in database
	 * input:       Vegetable type argument after updation 	
	 * expectation: UpdatedVegetable should be added in database after validation
	 * 
	 */
	
	@Override
	public Vegetable updateVegetable(Vegetable vegetable) {
		int vegId = vegetable.getVegId();
		boolean exists = vegetableRepository.existsById(vegId);
		if(!exists) {
			throw new VegetableNotFoundException("Vegetable not found with id" + vegId);
		}
		Vegetable saved = vegetableRepository.save(vegetable);
		return saved;
	}
	
	/*
	 * scenario :   Vegetable to be removed from database
	 * input:       Vegetable type argument which has to be removed
	 * expectation: UpdatedVegetable should be added in database after validation
	 * 
	 */
	
	@Override
	public Vegetable removeVegetable(Vegetable vegetable) {
		validateVegetable(vegetable);
		Optional<Vegetable>optional=vegetableRepository.findById(vegetable.getVegId());
		if(!optional.isPresent()) {
			 throw new VegetableNotFoundException("Vegetable to be removed does not exist");
		 }
		 Vegetable removed = optional.get();
		 vegetableRepository.delete(removed);
		 return removed;
	}

	@Override
	public Vegetable viewVegetable(int vegId) {
		validateId(vegId);
		Optional<Vegetable>optional=vegetableRepository.findById(vegId);		
		if(!optional.isPresent()) 
			 throw new VegetableNotFoundException("Vegetable to be removed does not exist");
		return optional.get();
	}
	
	@Override
	public List<Vegetable> viewAllVegetables() {
		List<Vegetable>allVegetables=vegetableRepository.findAll();
		return allVegetables;
	}

	@Override
	public List<Vegetable> viewVegetableList(String category) {
		validateCategory(category);
		List<Vegetable>allVegetables=vegetableRepository.findByCategory(category);
		return allVegetables;
	}

	@Override
	public List<Vegetable> viewVegetableByName(String name) {
		validateCategory(name);
		List<Vegetable>allVegetables=vegetableRepository.findByName(name);
		return allVegetables;
	}
	
	public void validateVegetable(Vegetable vegetable) {
		validateName(vegetable.getName());
		validateCategory(vegetable.getCategory());
		validateType(vegetable.getType());
		validatePrice(vegetable.getPrice());
		validateQuantity(vegetable.getQuantity());
	}
	
	public void validateName(String name) {
		if(name.isEmpty()|| name==null || name.trim().isEmpty()) 
			throw new InvalidVegetableNameException("name can't empty or null");
	}
	
	public void validateCategory(String category) {
		if(category.isEmpty()|| category==null || category.trim().isEmpty()) 
			throw new InvalidVegetableCategoryException("category can't empty or null");
	}
	
	public void validateType(String type) {
		if(type.isEmpty()|| type==null || type.trim().isEmpty())
			throw new InvalidVegetableTypeException("category can't empty or null");
	}
	
	public void validatePrice(double price) {
		if(price<=0)
			throw new InvalidVegetablePriceException("price can't be negative");
	}
	
	public void validateQuantity(int quantity) {
		if(quantity<0)
			throw new InvalidVegetableQuantityException("Quantity can't be negative");
	}
	
	public void validateId(int id) {
		if(id<0)	
			throw new InvalidVegetableIdException("Id can't be negative");
	}

}

package com.cg.vegetable.mgmt.service;

import java.util.List;
import java.util.Optional;

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
	
	
	public void validateVegetable(Vegetable vegetable) {
		validateName(vegetable.getName());
		validateCategory(vegetable.getCategory());
		validateType(vegetable.getType());
		validatePrice(vegetable.getPrice());
		validateQuantity(vegetable.getQuantity());
	}
	@Override
	public Vegetable addVegetable(Vegetable dto) {
		validateVegetable(dto);
		Vegetable vegetable= vegetableRepository.save(dto);		
		return vegetable;
	}

	@Override
	public Vegetable updateVegetable(Vegetable dto) {
		validateVegetable(dto);
		viewVegetable(dto.getVegId());
		return vegetableRepository.save(dto);
	}

	@Override
	public Vegetable removeVegetable(Vegetable dto) {
		Optional<Vegetable>optional=vegetableRepository.findById(dto.getVegId());
		if(!optional.isPresent()) {
			 throw new VegetableNotFoundException("Vegetable to be removed does not exist");
		 }
		 Vegetable removed = optional.get();
		 vegetableRepository.deleteById(dto.getVegId());
		 return removed;
	}

	@Override
	public Vegetable viewVegetable(int vegId) {
		validateId(vegId);
		Optional<Vegetable>optional=vegetableRepository.findById(vegId);
		if(!optional.isPresent()) {
			 throw new VegetableNotFoundException("Vegetable to be removed does not exist");
		 }
		return optional.get();
	}
	
	public void validateName(String name) {
		if(name.isEmpty()|| name==null || name.trim().isEmpty()) {
			throw new InvalidVegetableNameException("name can't empty or null");
		}
	}
	
	public void validateCategory(String category) {
		if(category.isEmpty()|| category==null || category.trim().isEmpty()) {
			throw new InvalidVegetableCategoryException("category can't empty or null");
		}
	}
	
	public void validateType(String type) {
		if(type.isEmpty()|| type==null || type.trim().isEmpty()) {
			throw new InvalidVegetableTypeException("category can't empty or null");
		}
	}
	
	public void validatePrice(double price) {
		if(price<=0) {
			throw new InvalidVegetablePriceException("price can't be negative");
		}
	}
	
	public void validateQuantity(int quantity) {
		if(quantity<0) {
			throw new InvalidVegetableQuantityException("Quantity can't be negative");
		}
	}
	
	public void validateId(int id) {
		if(id<0) {
			throw new InvalidVegetableIdException("Id can't be negative");
		}
	}

	/*@Override
	public List<VegetableDTO> viewAllVegetables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VegetableDTO> viewVegetableList(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VegetableDTO> viewVegetableByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}*/

}

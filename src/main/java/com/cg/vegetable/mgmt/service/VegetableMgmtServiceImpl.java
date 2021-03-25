package com.cg.vegetable.mgmt.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vegetable.mgmt.entities.VegetableDTO;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableCategoryException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableIdException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableNameException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetablePriceException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableQuantityException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableTypeException;
import com.cg.vegetable.mgmt.repository.IVegetableMgmtRepository;
@Service
public class VegetableMgmtServiceImpl implements IVegetableMgmtService{
	
	@Autowired
	IVegetableMgmtRepository vegetableRepository;
	@Override
	public VegetableDTO addVegetable(VegetableDTO dto) {
		validateName(dto.getName());
		validateCategory(dto.getCategory());
		validateType(dto.getType());
		validatePrice(dto.getPrice());
		validateQuantity(dto.getQuantity());
		VegetableDTO vegetable= vegetableRepository.save(dto);
		return vegetable;
	}

	@Override
	public VegetableDTO updateVegetable(VegetableDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VegetableDTO removeVegetable(VegetableDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VegetableDTO viewVegetable(int VegId) {
		// TODO Auto-generated method stub
		return null;
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

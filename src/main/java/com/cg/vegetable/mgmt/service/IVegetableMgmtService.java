package com.cg.vegetable.mgmt.service;

import java.util.List;

import com.cg.vegetable.mgmt.entities.Vegetable;

public interface IVegetableMgmtService {

	public Vegetable addVegetable(Vegetable dto);
	public Vegetable updateVegetable(Vegetable dto);
	public Vegetable removeVegetable(Vegetable dto);
	public Vegetable viewVegetable(int vegId);
	public List<Vegetable> viewAllVegetables();
	public List<Vegetable> viewVegetableByCategory(String category);
	public List<Vegetable> viewVegetableByName(String name);
	
}

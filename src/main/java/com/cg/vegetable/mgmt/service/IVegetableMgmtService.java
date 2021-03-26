package com.cg.vegetable.mgmt.service;

import java.util.List;

import com.cg.vegetable.mgmt.entities.Vegetable;

public interface IVegetableMgmtService {

	public Vegetable addVegetable(Vegetable dto);
	public Vegetable updateVegetable(Vegetable dto);
	public Vegetable removeVegetable(Vegetable dto);
	public Vegetable viewVegetable(Vegetable dto);
	/*public List<VegetableDTO> viewAllVegetables();
	public List<VegetableDTO> viewVegetableList(String category);
	public List<VegetableDTO> viewVegetableByName(String name);
	*/
}

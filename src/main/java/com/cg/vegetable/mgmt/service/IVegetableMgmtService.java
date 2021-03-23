package com.cg.vegetable.mgmt.service;

import java.util.List;

import com.cg.vegetable.mgmt.entities.VegetableDTO;

public interface IVegetableMgmtService {

	public VegetableDTO addVegetable(VegetableDTO dto);
	public VegetableDTO updateVegetable(VegetableDTO dto);
	public VegetableDTO removeVegetable(VegetableDTO dto);
	public VegetableDTO viewVegetable(VegetableDTO dto);
	public List<VegetableDTO> viewAllVegetables();
	public List<VegetableDTO> viewVegetableList(String category);
	public List<VegetableDTO> viewVegetableByName(String name);
}

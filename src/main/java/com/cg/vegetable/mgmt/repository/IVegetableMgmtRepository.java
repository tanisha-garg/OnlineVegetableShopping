package com.cg.vegetable.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.vegetable.mgmt.entities.VegetableDTO;

public interface IVegetableMgmtRepository extends JpaRepository<VegetableDTO,Integer>{

	/*public VegetableDTO addVegetable(VegetableDTO dto);
	public VegetableDTO updateVegetable(VegetableDTO dto);
	public VegetableDTO removeVegetable(VegetableDTO dto);
	public VegetableDTO viewVegetable(VegetableDTO dto);
	public List<VegetableDTO> viewAllVegetables();
	public List<VegetableDTO> viewVegetableList(String category);
	public List<VegetableDTO> viewVegetableByName(String name);*/
}

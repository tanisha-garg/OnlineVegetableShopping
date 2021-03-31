package com.cg.vegetable.mgmt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.vegetable.mgmt.entities.Vegetable;

public interface IVegetableMgmtRepository extends JpaRepository<Vegetable,Integer>{

	List<Vegetable> findByCategory(String category);
	List<Vegetable> findByName(String name);

}

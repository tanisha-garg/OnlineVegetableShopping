package com.cg.vegetable.mgmt.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.vegetable.mgmt.entities.VegetableDTO;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableCategoryException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableIdException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableNameException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetablePriceException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableQuantityException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableTypeException;
import com.cg.vegetable.mgmt.exceptions.VegetableNotFoundException;
import com.cg.vegetable.mgmt.repository.IVegetableMgmtRepository;

@ExtendWith(MockitoExtension.class)
class VegetableMgmtServiceImplUnitTest {
	
	@Mock
	IVegetableMgmtRepository vegetableMgmtRepository;
	
	
	@Spy
	@InjectMocks
	VegetableMgmtServiceImpl vegetableMgmtServiceImplservice;
	

	/*
	 * scenario : success scenario
	 * 
	 */

	@Test
	public void test_Add1() {
		int vegId=1;
		String name="potato";
		String type="root";
		String category="underground";
		double price=20.0;
		int quantity=3;
		VegetableDTO saved =Mockito.mock(VegetableDTO.class);
		Mockito.when(vegetableMgmtRepository.save(Mockito.any(VegetableDTO.class))).thenReturn(saved);
		VegetableDTO result=vegetableMgmtServiceImplservice.addVegetable(new VegetableDTO(vegId,name,type,category,price,quantity));
		Assertions.assertNotNull(result);
		Assertions.assertEquals(saved,result);
	}
	
	
	/*
	 * 
	 *   scenario : vegId is negative 
	 * 
	 */
	
	@Test
	public void test_ValidateId() {
		
		int vegId=-10;
		VegetableDTO vegetable = new VegetableDTO(vegId,"potato","root","underground",20.0,3);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetableIdException.class, executable);
		
	}
	
	/*
	 *  scenario : name is empty
	 * 
	 */
	@Test
	public void test_ValidateName() {
		String name="";
		VegetableDTO vegetable = new VegetableDTO(2,name,"root","underground",20.0,3);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetableNameException.class, executable);
	}
	
	/*
	 *  scenario : type is empty
	 * 
	 */
	@Test
	public void test_ValidateType() {
		String type="";
		VegetableDTO vegetable = new VegetableDTO(2,"potato",type,"underground",20.0,3);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetableTypeException.class, executable);
	}
	
	/*
	 *  scenario : category is empty
	 * 
	 */
	@Test
	public void test_ValidateCategory() {
		String category="";
		VegetableDTO vegetable = new VegetableDTO(2,"potato","root",category,20.0,3);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetableCategoryException.class, executable);
	}
	
	/*
	 *  scenario : price is negative
	 * 
	 */
	@Test
	public void test_ValidatePrice() {
		double price=-20.0;
		VegetableDTO vegetable = new VegetableDTO(2,"potato","root","underground",price,3);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetablePriceException.class, executable);
	}
	
	/*
	 *  scenario : quantity is negative
	 * 
	 */
	@Test
	public void test_ValidateQuantity() {
		int quantity=-5;;
		VegetableDTO vegetable = new VegetableDTO(2,"potato","root","underground",20.0,quantity);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetableQuantityException.class, executable);
	}
	
	/*
	 * scenario : to view the vegetable if id is given and 
	 * 			  vegetable is found successfully
	 * 
	 */
	@Test
	public void viewVegetable_1() {
		int vegId=1;
		VegetableDTO vegetable = Mockito.mock(VegetableDTO.class);
		Optional<VegetableDTO>optional=Optional.of(vegetable);
		when(vegetableMgmtRepository.findById(vegId)).thenReturn(optional);
		VegetableDTO result = vegetableMgmtServiceImplservice.viewVegetable(vegetable);
		Assertions.assertEquals(vegetable,result);
		
	}
	
	/*
	 * scenario : to view the vegetable if id is given and 
	 * 			  vegetable is not found 
	 * 
	 */
	@Test
	public void viewVegetable_2() {
		int vegId=50;
		Optional<VegetableDTO>optional=Optional.empty();
		when(vegetableMgmtRepository.findById(vegId)).thenReturn(optional);
		Executable executable = () -> vegetableMgmtServiceImplservice.viewVegetable(optional.get());
		Assertions.assertThrows(VegetableNotFoundException.class, executable);
		System.out.print("Hello");
		
	}
	
	
	/*
	 * 
	 * scenario : first vegetable is fetched then it is updated
	 * 
	 */
	@Test
	public void updateVegetable_1() {
		
	}
	
	
	/*
	 * 
	 *  scenario : if
	 * 
	 */
	
	
}

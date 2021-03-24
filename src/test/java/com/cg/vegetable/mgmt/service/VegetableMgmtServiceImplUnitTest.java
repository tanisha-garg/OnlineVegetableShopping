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
		String name="potato";
		String type="root";
		String category="underground";
		double price=20.0;
		int quantity=3;
		VegetableDTO user =Mockito.mock(VegetableDTO.class);
		VegetableDTO saved =Mockito.mock(VegetableDTO.class);
		doNothing().when(vegetableMgmtServiceImplservice).validateName(name);
		doNothing().when(vegetableMgmtServiceImplservice).validateCategory(category);
		doNothing().when(vegetableMgmtServiceImplservice).validateType(type);
		doNothing().when(vegetableMgmtServiceImplservice).validatePrice(price);
		doNothing().when(vegetableMgmtServiceImplservice).validateQuantity(quantity);
		when(vegetableMgmtRepository.save(any(VegetableDTO.class))).thenReturn(saved);
		VegetableDTO result=vegetableMgmtServiceImplservice.addVegetable(user);
		Assertions.assertNotNull(result);
		Assertions.assertSame(saved,result);
		verify(vegetableMgmtRepository).save(user);
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
		doThrow(InvalidVegetableIdException.class).when(vegetableMgmtServiceImplservice).validateId(vegId);
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
		doThrow(InvalidVegetableNameException.class).when(vegetableMgmtServiceImplservice).validateName(name);
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
		doThrow(InvalidVegetableTypeException.class).when(vegetableMgmtServiceImplservice).validateType(type);
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
		doThrow(InvalidVegetableCategoryException.class).when(vegetableMgmtServiceImplservice).validateCategory(category);
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
		doThrow(InvalidVegetablePriceException.class).when(vegetableMgmtServiceImplservice).validatePrice(price);
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
		doThrow(InvalidVegetableQuantityException.class).when(vegetableMgmtServiceImplservice).validateQuantity(quantity);
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
		
	}
	


	
	
}

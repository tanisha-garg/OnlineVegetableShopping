package com.cg.vegetable.mgmt.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
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

import com.cg.vegetable.mgmt.entities.Vegetable;
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
		Vegetable vegetable =Mockito.mock(Vegetable.class);
		Vegetable saved =Mockito.mock(Vegetable.class);
		doNothing().when(vegetableMgmtServiceImplservice).validateVegetable(vegetable);
		when(vegetableMgmtRepository.save(vegetable)).thenReturn(saved);
		Vegetable result=vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertNotNull(result);
		Assertions.assertSame(saved,result);
		verify(vegetableMgmtRepository).save(vegetable);
		verify(vegetableMgmtServiceImplservice).validateVegetable(vegetable);
	}
	
	
	/*
	 *  scenario : name is empty
	 * 				add test case
	 */
	@Test
	public void test_Add2() {
		String name="";
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetableNameException.class).when(vegetableMgmtServiceImplservice).validateVegetable(vegetable);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetableNameException.class, executable);
	}
	
	
	
	/*
	 *  scenario : category is empty
	 * 				add test case
	 */
	@Test
	public void test_Add3() {
		String category="";
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetableCategoryException.class).when(vegetableMgmtServiceImplservice).validateVegetable(vegetable);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetableCategoryException.class, executable);
	}
	
	/*
	 *  scenario : type is empty
	 * 				add test case
	 */
	@Test
	public void test_Add4() {
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetableTypeException.class).when(vegetableMgmtServiceImplservice).validateVegetable(vegetable);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetableTypeException.class, executable);
	}
	
	/*
	 *  scenario : price is negative
	 * 				add test case
	 */
	@Test
	public void test_Add5() {
		double price=-20.0;
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetablePriceException.class).when(vegetableMgmtServiceImplservice).validateVegetable(vegetable);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetablePriceException.class, executable);
	}
	
	/*
	 *  scenario : quantity is negative
	 * 				add test case
	 */
	@Test
	public void test_Add6() {
		int quantity=-3;
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetableQuantityException.class).when(vegetableMgmtServiceImplservice).validateVegetable(vegetable);
		Executable executable = () -> vegetableMgmtServiceImplservice.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetableQuantityException.class, executable);
	}
	
	/*
	 *  scenario : name is empty
	 * 				add test case
	 */
	@Test
	public void test_ValidateName() {
		String name="";
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetableNameException.class).when(vegetableMgmtServiceImplservice).validateName(name);
		Executable executable = () -> vegetableMgmtServiceImplservice.validateName(name);
		Assertions.assertThrows(InvalidVegetableNameException.class, executable);
	}
	
	/*
	 *  scenario : type is empty
	 * 				validate test case
	 */
	@Test
	public void test_ValidateType() {
		String type="";
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetableTypeException.class).when(vegetableMgmtServiceImplservice).validateType(type);
		Executable executable = () -> vegetableMgmtServiceImplservice.validateType(type);
		Assertions.assertThrows(InvalidVegetableTypeException.class, executable);
	}
	
	/*
	 *  scenario : category is empty
	 * 
	 */
	@Test
	public void test_ValidateCategory() {
		String category="";
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetableCategoryException.class).when(vegetableMgmtServiceImplservice).validateCategory(category);
		Executable executable = () -> vegetableMgmtServiceImplservice.validateCategory(category);
		Assertions.assertThrows(InvalidVegetableCategoryException.class, executable);
	}
	
	/*
	 *  scenario : price is negative
	 * 
	 */
	@Test
	public void test_ValidatePrice() {
		double price=-20.0;
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetablePriceException.class).when(vegetableMgmtServiceImplservice).validatePrice(price);
		Executable executable = () -> vegetableMgmtServiceImplservice.validatePrice(price);
		Assertions.assertThrows(InvalidVegetablePriceException.class, executable);
	}
	
	/*
	 *  scenario : quantity is negative
	 * 
	 */
	@Test
	public void test_ValidateQuantity() {
		int quantity=-5;
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetableQuantityException.class).when(vegetableMgmtServiceImplservice).validateQuantity(quantity);
		Executable executable = () -> vegetableMgmtServiceImplservice.validateQuantity(quantity);
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
		Vegetable vegetable = Mockito.mock(Vegetable.class);
		Optional<Vegetable>optional=Optional.of(vegetable);
		when(vegetableMgmtRepository.findById(vegId)).thenReturn(optional);
		Vegetable result = vegetableMgmtServiceImplservice.viewVegetable(vegId);
		Assertions.assertEquals(vegetable,result);
		verify(vegetableMgmtServiceImplservice).validateId(vegId);
		
	}
	
	/*
	 * scenario : to view the vegetable if id is given and 
	 * 			  vegetable is not found 
	 * 
	 */
	@Test
	public void viewVegetable_2() {
		int vegId=50;
		Optional<Vegetable>optional=Optional.empty();
		when(vegetableMgmtRepository.findById(vegId)).thenReturn(optional);
		Executable executable = () -> vegetableMgmtServiceImplservice.viewVegetable(vegId);
		Assertions.assertThrows(VegetableNotFoundException.class, executable);
		
	}
	
	/*
	 * scenario : to view the vegetable if id is given
	 * 			   but given id is negative
	 */
	
	@Test
	public void viewVegetable_3() {
		int vegId=-5;
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetableIdException.class).when(vegetableMgmtServiceImplservice).validateId(vegId);
		Executable executable = () -> vegetableMgmtServiceImplservice.viewVegetable(vegId);
		Assertions.assertThrows(InvalidVegetableIdException.class, executable);
	}
	
	/*
	 * updation of vegetable
	 * scenario : vegetable is fetched then updated successfully
	 * 
	 */
	@Test
	public void updateVegetable_1() {
		int vegId=1;
		Vegetable vegetable = Mockito.mock(Vegetable.class);
		Vegetable saved = Mockito.mock(Vegetable.class);
		when(vegetableMgmtServiceImplservice.viewVegetable(vegId)).thenReturn(vegetable);
		when(vegetableMgmtRepository.save(vegetable)).thenReturn(saved);
		Vegetable result = vegetableMgmtServiceImplservice.updateVegetable(vegetable);
		doNothing().when(vegetableMgmtServiceImplservice).validateVegetable(vegetable);
		assertNotNull(result);
		assertEquals(saved, result);
		verify(vegetableMgmtRepository).save(vegetable);
		verify(vegetableMgmtServiceImplservice).validateVegetable(vegetable);
		
	}

	/*
	 * scenario : updation of vegetable
	 * 			  but vegetable not found
	 * 
	 */
	@Test
	public void updateVegetable_2() {
		int vegId=100;
		Vegetable vegetable = Mockito.mock(Vegetable.class);
		doThrow(VegetableNotFoundException.class).when(vegetableMgmtServiceImplservice).viewVegetable(vegId);
		Executable executable = () -> vegetableMgmtServiceImplservice.updateVegetable(vegetable);
		assertThrows(VegetableNotFoundException.class, executable);
		verify(vegetableMgmtRepository, never()).save(vegetable);
	}
	
	/*
	 * 
	 * scenario : to remove the vegetable
	 * 			  removed successfully
	 * 
	 */
	@Test
	public void removeVegetable_1() {
		int vegId=1;
		Vegetable vegetable = Mockito.mock(Vegetable.class);
		Optional<Vegetable>optional=Optional.of(vegetable);
		when(vegetableMgmtRepository.findById(vegId)).thenReturn(optional);
		doNothing().when(vegetableMgmtRepository).deleteById(vegId);
		Vegetable removed = vegetableMgmtServiceImplservice.removeVegetable(vegetable);
	}
	
	/*
	 * scenario : to remove the vegetable
	 * 			  but vegetable not found
	 * 
	 * 
	 */
	
	@Test
	public void removeVegetable_2() {
		Vegetable vegetable = Mockito.mock(Vegetable.class);
		Optional<Vegetable>optional=Optional.empty();
		when(vegetableMgmtRepository.findById(vegetable.getVegId())).thenReturn(optional);
		Executable executable = () -> vegetableMgmtServiceImplservice.removeVegetable(vegetable);
		Assertions.assertThrows(VegetableNotFoundException.class, executable);
	}
}

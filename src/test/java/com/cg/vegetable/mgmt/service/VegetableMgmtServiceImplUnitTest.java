package com.cg.vegetable.mgmt.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.Optional;

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
	IVegetableMgmtRepository vegetableRepository;
	

	@Spy
	@InjectMocks
	VegetableMgmtServiceImpl vegetableService;
	

	/*
	 * scenario : success scenario , vegetable is saved
	 *
	 * input : mock vegetable object passed , stubbed validateVegatble
	 *
	 *  expectation : vegetable should be added, vegetableRepository#save(vegetable);
	 */
	@Test
	public void test_Add1() {
		Vegetable vegetable =Mockito.mock(Vegetable.class);
		Vegetable saved =Mockito.mock(Vegetable.class);
		doNothing().when(vegetableService).validateVegetable(vegetable);
		when(vegetableRepository.save(vegetable)).thenReturn(saved);
		Vegetable result= vegetableService.addVegetable(vegetable);
		Assertions.assertNotNull(result);
		Assertions.assertSame(saved,result);
		verify(vegetableRepository).save(vegetable);
		verify(vegetableService).validateVegetable(vegetable);
	}
	
	
	/*
	 *  scenario : name is empty
	 * 	expectation : InvalidVegetableNameException is thrown
	 */
	@Test
	public void test_Add2() {
		Vegetable vegetable = mock(Vegetable.class);
		doThrow(InvalidVegetableNameException.class).when(vegetableService).validateVegetable(vegetable);
		Executable executable = () -> vegetableService.addVegetable(vegetable);
		Assertions.assertThrows(InvalidVegetableNameException.class, executable);
	}

	
	/*
	 *  scenario : name is empty
	 * 				add test case
	 */
	@Test
	public void test_ValidateName() {
		String name="";
		doThrow(InvalidVegetableNameException.class).when(vegetableService).validateName(name);
		Executable executable = () -> vegetableService.validateName(name);
		Assertions.assertThrows(InvalidVegetableNameException.class, executable);
	}
	
	/*
	 *  scenario : type is empty
	 * 				validate test case
	 */
	@Test
	public void test_ValidateType() {
		String type="";
		doThrow(InvalidVegetableTypeException.class).when(vegetableService).validateType(type);
		Executable executable = () -> vegetableService.validateType(type);
		Assertions.assertThrows(InvalidVegetableTypeException.class, executable);
	}
	
	/*
	 *  scenario : category is empty
	 * 
	 */
	@Test
	public void test_ValidateCategory() {
		String category="";
		doThrow(InvalidVegetableCategoryException.class).when(vegetableService).validateCategory(category);
		Executable executable = () -> vegetableService.validateCategory(category);
		Assertions.assertThrows(InvalidVegetableCategoryException.class, executable);
	}
	
	/*
	 *  scenario : price is negative
	 * 
	 */
	@Test
	public void testValidatePrice() {
		double price=-20.0;
		doThrow(InvalidVegetablePriceException.class).when(vegetableService).validatePrice(price);
		Executable executable = () -> vegetableService.validatePrice(price);
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
		doThrow(InvalidVegetableQuantityException.class).when(vegetableService).validateQuantity(quantity);
		Executable executable = () -> vegetableService.validateQuantity(quantity);
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
		when(vegetableRepository.findById(vegId)).thenReturn(optional);
		Vegetable result = vegetableService.viewVegetable(vegId);
		Assertions.assertEquals(vegetable,result);
		verify(vegetableService).validateId(vegId);
		
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
		when(vegetableRepository.findById(vegId)).thenReturn(optional);
		Executable executable = () -> vegetableService.viewVegetable(vegId);
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
		doThrow(InvalidVegetableIdException.class).when(vegetableService).validateId(vegId);
		Executable executable = () -> vegetableService.viewVegetable(vegId);
		Assertions.assertThrows(InvalidVegetableIdException.class, executable);
	}
	
	/*
	 * updation of vegetable
	 * scenario : vegetable is fetched then updated successfully
	 * 
	 */
	@Test
	public void updateVegetable_1() {
		int vegId = 1;
		Vegetable vegetable = Mockito.mock(Vegetable.class);
		Vegetable saved = Mockito.mock(Vegetable.class);
		when(vegetable.getVegId()).thenReturn(vegId);
		when(vegetableRepository.existsById(vegId)).thenReturn(true);
		doNothing().when(vegetableService).validateVegetable(vegetable);
		when(vegetableRepository.save(vegetable)).thenReturn(saved);
		Vegetable result = vegetableService.updateVegetable(vegetable);
		assertNotNull(result);
		assertEquals(saved, result);
		verify(vegetableRepository).save(vegetable);
		
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
		when(vegetable.getVegId()).thenReturn(vegId);
		when(vegetableRepository.existsById(vegId)).thenReturn(false);
		doNothing().when(vegetableService).validateVegetable(vegetable);
		Executable executable = () -> vegetableService.updateVegetable(vegetable);
		assertThrows(VegetableNotFoundException.class, executable);
		verify(vegetableRepository, never()).save(vegetable);
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
		Vegetable vegetable = mock(Vegetable.class);
		when(vegetable.getVegId()).thenReturn(vegId);
		when(vegetableRepository.existsById(vegId)).thenReturn(true);
		Vegetable result= vegetableService.removeVegetable(vegetable);
		Assertions.assertNotNull(result);
		verify(vegetableRepository).deleteById(vegId);
	}
	
	/*
	 * scenario : to remove the vegetable
	 * 			  but vegetable not found
	 * 
	 * 
	 */
	
	@Test
	public void removeVegetable_2() {
		int vegId=100;
		Vegetable vegetable = mock(Vegetable.class);
		when(vegetable.getVegId()).thenReturn(vegId);
		when(vegetableRepository.existsById(vegId)).thenReturn(false);
		Executable executable = () -> vegetableService.removeVegetable(vegetable);
		Assertions.assertThrows(VegetableNotFoundException.class, executable);
		verify(vegetableRepository, never()).delete(vegetable);
	}
}
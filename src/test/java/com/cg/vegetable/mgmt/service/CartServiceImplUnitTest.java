package com.cg.vegetable.mgmt.service;

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

import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.exceptions.CartException;
import com.cg.vegetable.mgmt.exceptions.CartIsEmptyException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableQuantityException;
import com.cg.vegetable.mgmt.exceptions.VegValAboveZeroException;
import com.cg.vegetable.mgmt.exceptions.VegValAtleastOneException;
import com.cg.vegetable.mgmt.exceptions.VegetableIsNullException;
import com.cg.vegetable.mgmt.exceptions.VegetableMustHaveValueException;
import com.cg.vegetable.mgmt.exceptions.VegetableNotFoundException;
import com.cg.vegetable.mgmt.repository.ICartRepository;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;
import com.cg.vegetable.mgmt.repository.IVegetableMgmtRepository;
//import com.cg.vegetable.mgmt.entities.CartDTO;
import com.cg.vegetable.mgmt.service.CartServiceImpl;



@ExtendWith(MockitoExtension.class)
public class CartServiceImplUnitTest {
	
	@Mock
	ICartRepository cartRepository;
	
	@Mock
	IVegetableMgmtRepository vegRepository;
	
	@Mock
	ICustomerRepository custRepository;
	
	
	@Spy
	@InjectMocks
	CartServiceImpl cartService;
	
	
	/**
	 * Scenario: Cart Is Null
	 */
	
	
	@Test
	void addToCartTest_2() {
		Vegetable veg = Mockito.mock(Vegetable.class);
		Mockito.doThrow(VegetableIsNullException.class).when(cartService).validateVegetable(veg);
		Executable executable = () -> cartService.validateVegetable(veg);
		Assertions.assertThrows(VegetableIsNullException.class, executable);

	}
		

	

}
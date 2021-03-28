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
	
	
	/*
	 * scenario : success scenario
	 * 
	 */
	@Test
	public void testAddToCart_1() {
		int customerId =1;
	    Vegetable vegetable = Mockito.mock(Vegetable.class);
	    Customer customer = Mockito.mock(Customer.class);
		Cart cart =Mockito.mock(Cart.class);
		Cart saved =Mockito.mock(Cart.class);
		Vegetable save = Mockito.mock(Vegetable.class);
		when(cartRepository.save(cart)).thenReturn(saved);//saved
		when(vegRepository.save(vegetable)).thenReturn(save);//save
		Optional <Customer> opt = Optional.of(customer);
		when(custRepository.findById(customer.getCustomerId())).thenReturn(opt);
		Optional <Vegetable> optional = Optional.of(vegetable);
		when(vegRepository.findById(vegetable.getVegId())).thenReturn(optional);
		Vegetable result = cartService.addToCart(customerId,vegetable);
		Assertions.assertNotNull(result);
		Assertions.assertSame(vegetable,result);
	}
	
	

	/*
	 * scenario : to view the vegetable if id is given and 
	 * 			  vegetable is not found 
	 * 
	 */
	@Test
	public void testAddToCart_2() {
		int vegId=50;
		int customerId=10;
		Optional<Vegetable>optional=Optional.empty();
		Vegetable save = Mockito.mock(Vegetable.class);
		when(vegRepository.findById(vegId)).thenReturn(optional);
		Executable executable = () -> cartService.addToCart(customerId,save);
		Assertions.assertThrows(VegetableNotFoundException.class, executable);
		
	}
	
	
//	/*
//	 * scenario : Vegetable is null
//	 * 
//	 */
//	
//	@Test
//	public void testaddToCart_2() {
//		VegetableDTO vegetable = null;
//		Customer customer = Mockito.mock(Customer.class);
//		doThrow(VegetableIsNullException.class).when(cartService).validateVegetable(vegetable);
//		Executable executable = () -> cartService.addToCart(1,vegetable);
//		Assertions.assertThrows(VegetableIsNullException.class, executable);
//	}
//	
	/*
	 *  scenario : quantity is zero
	 * 
//	 */
//	@Test
//	public void testupdateItemQuantity() {
//		int quantity=0;
//		VegetableDTO vegetable = mock(VegetableDTO.class);
//		doThrow(InvalidVegetableQuantityException.class).when(cartService).validateQuantity(quantity);
//		Executable executable = () -> cartService.addToCart(vegetable);
//		Assertions.assertThrows(InvalidVegetableQuantityException.class, executable);
//	}
//	
	/*
	 *  scenario : cart is Empty
	 * 
	 */
//	@Test
//	public void testviewAllItems() {
//		CartDTO cart = null;
//		doThrow(CartIsEmptyException.class).when(cartService).validateCart(cart);
//		Executable executable = () -> cartService.addToCart(cart);
//		Assertions.assertThrows(CartIsEmptyException.class, executable);
//		
//	}
//	
	
	/*
	 *  scenario : quantity must be atleast 1
	 * 
	 */
	@Test
	public void testincreaseVegQuantity() {
		int quantity=1;
		int customerId =1;
		Vegetable vegval = mock(Vegetable.class);
		Customer customer = Mockito.mock(Customer.class);
		doThrow(VegetableMustHaveValueException.class).when(cartService).validateInc(quantity);
		Executable executable = () -> cartService.increaseVegQuantity(customerId,vegval.getVegId());
		Assertions.assertThrows(VegetableMustHaveValueException.class, executable);
	}
	
	/*
	 *  scenario : quantity must be greater than 1
	 * 
	 */
	@Test
	public void testdecreaseVegQuantity() {
		int value=0;
		int customerId =1;
		Vegetable vegval = mock(Vegetable.class);
		Customer customer = Mockito.mock(Customer.class);
		doThrow(VegValAboveZeroException.class).when(cartService).validateDec(value);
		Executable executable = () -> cartService.decreaseVegQuantity(customerId,vegval.getVegId());
		Assertions.assertThrows(VegValAboveZeroException.class, executable);
	}
	
	/*
	 *  scenario : Atleast one vegetable
	 * 
	 */
//	@Test
//	public void removeVegetable() {
//		int val = 1;
//		VegetableDTO cartval = mock(VegetableDTO.class);
//		doThrow(VegValAtleastOneException.class).when(cartService).validateId(val);
//		Executable executable = () -> cartService.addToCart(cartval);
//		Assertions.assertThrows(VegValAtleastOneException.class, executable);
//	}
	
	/*
	 *  scenario : id must be positive
	 * 
	 */
	@Test
	public void removeVegetable() {
		int val = 1;
		Cart cartval = mock(Cart.class);
		doThrow(VegValAtleastOneException.class).when(cartService).validateId(val);
		Executable executable = () -> cartService.removeAllVegetables(cartval);
		Assertions.assertThrows(VegValAtleastOneException.class, executable);
		
	}
	
	
	

}

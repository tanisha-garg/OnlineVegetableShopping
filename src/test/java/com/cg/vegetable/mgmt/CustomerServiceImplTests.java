package com.cg.vegetable.mgmt;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

//import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;
import com.cg.vegetable.mgmt.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTests {
	
	@Spy
	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Mock
	ICustomerRepository repository;
	
	
//	@BeforeEach
//	public void setUp() {
//		customerService = new CustomerServiceImp();
//	}
	
	
	/*
	 * Scenario: When customer is added successfully.
	 */
	
	@Test
	public void test_AddCustomer_1() {
		Customer customer = Mockito.mock(Customer.class);
		Customer customerSaved = Mockito.mock(Customer.class);
		Mockito.when(repository.save(customer)).thenReturn(customerSaved);
		Customer result = customerService.addCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerSaved, result);

	}
	
	
	/*	
	 * 	 * Scenario: When customer details are null
	 *    Result: Customer not added exception
	 */
	 
	@Test
	public void test_AddCustomer_2() {
		Customer customer= null;
		Executable executable=()->customerService.addCustomer(customer);
		Assertions.assertThrows(CustomerNotAddedException.class,executable);		
	}
	
	/*	
	 * 	 * Scenario: When customer details are updated successfully
	 */
	
	@Test
	public void updateCustomerTest_1() {
		Customer customer = Mockito.mock(Customer.class);
		Customer customerSaved = Mockito.mock(Customer.class);
		Mockito.when(repository.save(customer)).thenReturn(customerSaved);
		Customer result = customerService.updateCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerSaved, result);

	}
	
	/*	
	 * 	 * Scenario: When customer details are not updated successfully
	 */
	@Test
	public void updateCustomerTest_2() {
		Customer customer = null;
		Executable executable = () -> customerService.addCustomer(customer);
		Assertions.assertThrows(CustomerNotUpdatedException.class, executable);
	}
	
	/*	
	 * 	 * Scenario: When customer is removed successfully
	  */
	
	@Test
	public void removeCustomerTest_1() {
		Customer customer = Mockito.mock(Customer.class);
		Customer customerSaved = Mockito.mock(Customer.class);
		Mockito.when(repository.removeCustomer(customer)).thenReturn(customerSaved);
		Customer result = customerService.removeCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerSaved, result);

	}
	

	/*	
	 * 	 * Scenario: When customer is not removed successfully
	  */
	@Test
	public void removeCustomerTest_2() {
		Customer customer = null;
		Executable executable = () -> customerService.addCustomer(customer);
		Assertions.assertThrows(CustomerNotRemovedException.class, executable);
	}
	
	/*	
	 * 	 * Scenario: When customer is successfully viewed using customerId
	  */

//	@Test
//	public void viewCustomerTest_1() {
//		int customerId = 1;
//		Customer customer = Mockito.mock(Customer.class);
//		Optional<Customer> optionalSaved =Optional.of(customer);
//		//Mockito.when(repository.save(customerId)).thenReturn(optionalSaved);
//		Customer result = customerService.viewCustomer(customerId);
//		Assertions.assertNotNull(result);
//		Assertions.assertEquals(customer, result);
//
//	}
//	
	/*	
	 * 	 * Scenario: When customer is not successfully viewed using customerId
	  */

//	@Test
//	public void test_ViewCustomer_2() {
//		int customerId = 590;
//		Executable executable = () -> customerService.viewCustomer(customerId);
//		Assertions.assertThrows(CustomerIdNotFoundException.class, executable);
//	}
//	
	
	/*
	 *   scenario :When all customers are viewed successfully
	 */
//	@Test
//	public void test_ViewAllCustomers_1() {
		String location="Mumbai";
//		List<String> fetchedList=Mockito.mock(List.class);	
//		Mockito.when(repository.viewCustomerList(location)).thenReturn(fetchedList);
//		Mockito.when(fetchedList).thenReturn(fetchedList);
//	}	

	
	/*
	 *  scenario : Customer name is empty
	 */
	
	@Test
	public void test_ValidateName1() {
		String name="";
		Customer customer = new Customer(1,name,"9863527891","agc@ghb.com");
		Executable executable = () -> customerService.addCustomer(customer);
		Assertions.assertThrows(InvalidCustomerNameException .class, executable);
	}
	
	
	}
	

	

package com.cg.vegetable.mgmt.service;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

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

import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.ICartRepository;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplUnitTests {

	@Mock
	private ICustomerRepository customerRepository;

	@Mock
	private ICartRepository cartRepository;

	@Spy
	@InjectMocks
	CustomerServiceImpl customerService;

	/*
	 * scenario : Customer is saved successfully input: mock Customer object,
	 * stubbing validate Customer method expectation: verifying
	 * ICustomerRepository#save(customer) is called
	 */

	@Test
	public void test_AddCustomer_1() {
		Customer customer = mock(Customer.class);

		Customer customerSaved = Mockito.mock(Customer.class);
		doNothing().when(customerService).validateCustomer(customer);
		when(customerRepository.save(customer)).thenReturn(customerSaved);

		Customer result = customerService.addCustomer(customer);
		Assertions.assertNotNull(result);

		verify(customerRepository).save(customer);
		verify(customerService).validateCustomer(customer);
	}

	/*
	 * * Scenario: When customer details are null input: mock Customer object,
	 * stubbing validate customer method Result: Customer not added exception
	 */

	@Test
	public void test_AddCustomer_2() {
		Customer customer = mock(Customer.class);
		doThrow(CustomerNotAddedException.class).when(customerService).validateCustomer(customer);
		Executable executable = () -> customerService.addCustomer(customer);
		Assertions.assertThrows(CustomerNotAddedException.class, executable);
		verify(customerRepository, never()).save(customer);
	}

	/*
	 * * Scenario: customer details are updated successfully input: mock Customer
	 * object, stubbing validate customer method result: verifying
	 * ICustomerRepository#save(customer) is called
	 */

	@Test
	public void updateCustomerTest_1() {
		Integer customerId = 2;
		Customer customer = mock(Customer.class);
		when(customer.getCustomerId()).thenReturn(customerId);
		doNothing().when(customerService).validateCustomer(customer);
		when(customerRepository.existsById(customerId)).thenReturn(true);
		when(customerRepository.save(customer)).thenReturn(customer);
		Customer result = customerService.updateCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customer, result);
		verify(customerRepository).save(customer);
		verify(customerService).validateCustomer(customer);

	}

	/*
	 * * Scenario: customer details are null input: mock Customer object, stubbing
	 * validate customer method Result: Customer not updated exception
	 */
	@Test
	public void updateCustomerTest_2() {
		Customer customer = null;
		doThrow(CustomerNotUpdatedException.class).when(customerService).validateCustomer(customer);
		Executable executable = () -> customerService.addCustomer(customer);
		Assertions.assertThrows(CustomerNotUpdatedException.class, executable);
		verify(customerRepository, never()).save(customer);
	}

	/*
	 * * Scenario: customer is removed successfully input: mock Customer object
	 * Result: verifying ICustomerRepository#deleteById(customerId) is called
	 */

	@Test
	public void removeCustomerTest_1() {
		Integer customerId = 2;
		Customer customer = mock(Customer.class);
		when(customer.getCustomerId()).thenReturn(customerId);
		when(customerRepository.existsById(customerId)).thenReturn(true);
		Customer result = customerService.removeCustomer(customer);
		Assertions.assertNotNull(result);
		verify(customerRepository).deleteById(customerId);

	}

	/*
	 * * Scenario: When customerId does not exist input: mock Customer object
	 * Result: Customer not removed exception
	 */
	@Test
	public void removeCustomerTest_2() {
		Integer customerId = 6;
		Customer customer = mock(Customer.class);
		when(customer.getCustomerId()).thenReturn(customerId);
		when(customerRepository.existsById(customerId)).thenReturn(false);
		Executable executable = () -> customerService.removeCustomer(customer);
		Assertions.assertThrows(CustomerNotRemovedException.class, executable);
		verify(customerRepository, never()).delete(customer);
	}
	/*
	 * * Scenario: customer is successfully viewed using customerId input: mock
	 * Customer object, stubbing validate customer method Result: verifying
	 * CustomerServiceImpl#validateCustomer(customer) is called
	 */

	@Test
	public void viewCustomerTest_1() {
		int customerId = 4;
		Customer customer = mock(Customer.class);
		doNothing().when(customerService).validateCustomer(customer);
		Optional<Customer> optional = Optional.of(customer);
		when(customerRepository.findById(customerId)).thenReturn(optional);
		Customer result = customerService.viewCustomer(customerId);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customer, result);
		verify(customerService).validateCustomer(customer);

	}

	/*
	 * * Scenario: customer not viewed successfully using customerId input: mock
	 * Customer object Result: Customer not found exception
	 */

	@Test
	public void test_ViewCustomer_2() {
		int customerId = 2;
		Customer customer = mock(Customer.class);
		Executable executable = () -> customerService.viewCustomer(customerId);
		Assertions.assertThrows(CustomerNotFoundException.class, executable);
		verify(customerRepository, never()).save(customer);
	}

	/*
	 * scenario : customer name is empty validateName test case expectation :
	 * InvalidCustomerNameException is thrown
	 * 
	 */

//	@Test
//	public void test_ValidateName1() {
//		String name = "";
//		Customer customer = new Customer(name, "9863527891", "agc@ghb.com");
//		Executable executable = () -> customerService.addCustomer(customer);
//		Assertions.assertThrows(InvalidCustomerNameException.class, executable);
//	}

	/*
	 * scenario : customer name is null validateName test case expectation :
	 * InvalidCustomerNameException is thrown
	 * 
	 */
	@Test
	void test_validateCustomer_2() {
		Customer customer = Mockito.mock(Customer.class);
		when(customer.getName()).thenReturn("");
		Executable executable = () -> customerService.validateCustomer(customer);
		Assertions.assertThrows(InvalidCustomerNameException.class, executable);

	}

	/*
	 * scenario : customer emailId is empty validateName test case expectation :
	 * InvalidEmailIdException is thrown
	 * 
	 */
	@Test
	void test_validateCustomer_3() {
		Customer customer = Mockito.mock(Customer.class);
		when(customer.getName()).thenReturn("valid");
		when(customer.getEmailId()).thenReturn("");
		Executable executable = () -> customerService.validateCustomer(customer);
		Assertions.assertThrows(InvalidEmailIdException.class, executable);

	}

}
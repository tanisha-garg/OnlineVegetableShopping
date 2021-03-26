package com.cg.vegetable.mgmt;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

//import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.ICustomerRepository;
import com.cg.vegetable.mgmt.service.CustomerServiceImpl;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CustomerServiceImplUnitTests {

	@Mock
	ICustomerRepository customerRepository;
	
	
	@Spy
	@InjectMocks
	CustomerServiceImpl customerService;

	

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
		doNothing().when(customerService).validateCustomer(customer);
		Mockito.when(customerRepository.save(customer)).thenReturn(customerSaved);
		Customer result = customerService.addCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerSaved, result);
		verify(customerRepository).save(customer);
		verify(customerService).validateCustomer(customer);

	}

	/*
	 * * Scenario: When customer details are null Result: Customer not added
	 * exception
	 */

	@Test
	public void test_AddCustomer_2() {
		Customer customer = null;
		doThrow(CustomerNotAddedException.class).when(customerService).validateCustomer(customer);
		Executable executable = () -> customerService.addCustomer(customer);
		Assertions.assertThrows(CustomerNotAddedException.class, executable);
		verify(customerRepository, never()).save(customer);
	}

	/*
	 * * Scenario: When customer details are updated successfully
	 */

	@Test
	public void updateCustomerTest_1() {
		Integer customerId=1;
		Customer customer = Mockito.mock(Customer.class);
		doNothing().when(customerService).validateCustomer(customer);
		Mockito.when(customerRepository.existsById(customerId)).thenReturn(true);
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		Customer result = customerService.updateCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customer, result);
		verify(customerRepository).save(customer);
		verify(customerService).validateCustomer(customer);

	}

	/*
	 * * Scenario: When customer details are not updated successfully
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
	 * * Scenario: When customer is removed successfully
	 */

	@Test
	public void removeCustomerTest_1() {
		int customerId = 1;
		Customer customer = Mockito.mock(Customer.class);
		Optional<Customer> optional = Optional.of(customer);
		when(customerRepository.findById(customerId)).thenReturn(optional);
		doNothing().when(customerRepository).deleteById(customerId);
		Customer removed = customerService.removeCustomer(customer);
		verify(customerRepository).save(customer);
		verify(customerService).validateCustomer(customer);
        
	}

	/*
	 * * Scenario: When customer is not removed successfully
	 */
	@Test
	public void removeCustomerTest_2() {
		Customer customer = null;
		Executable executable = () -> customerService.addCustomer(customer);
		Assertions.assertThrows(CustomerNotRemovedException.class, executable);
		verify(customerRepository, never()).save(customer);
	}

	/*
	 * * Scenario: When customer is successfully viewed using customerId
	 */

	@Test
	public void viewCustomerTest_1() {
		
		Customer customer = Mockito.mock(Customer.class);
		Customer customerSaved = Mockito.mock(Customer.class);		
		Mockito.when(customerRepository.save(customer)).thenReturn(customerSaved);
		Customer result = customerService.viewCustomer(customer);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(customerSaved, result);
		verify(customerRepository).save(customerSaved);
		verify(customerService).validateCustomer(customer);

 }

	/*
	 * * Scenario: When customer is not successfully viewed using customerId
	 */

	@Test
	public void test_ViewCustomer_2() {
		Customer customer = Mockito.mock(Customer.class);
		doThrow(CustomerNotFoundException.class).when(customerService).validateCustomer(customer);
		Executable executable = () -> customerService.viewCustomer(customer);
		Assertions.assertThrows(CustomerIdNotFoundException.class, executable);
		verify(customerRepository, never()).save(customer);
	}

	/*
	 * scenario :When all customers are viewed successfully
	 */
//	@Test
//	public void test_ViewAllCustomers_1() {
//		String location="Mumbai";
//		List<String> fetchedList=Mockito.mock(List.class);	
//		Mockito.when(repository.viewCustomerList(location)).thenReturn(fetchedList);
//		Mockito.when(fetchedList).thenReturn(fetchedList);
//	}	

	/*
	 * scenario : Customer name is empty
	 */

	@Test
	public void test_ValidateName1() {
		String name = "";
		Customer customer = new Customer(name, "9863527891", "agc@ghb.com", "asdfghjkl", "asdfghjkl");
		Executable executable = () -> customerService.addCustomer(customer);
		Assertions.assertThrows(InvalidCustomerNameException.class, executable);
	}
}

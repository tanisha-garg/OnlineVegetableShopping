package com.cg.vegetable.mgmt.service;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.List;
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


//	@BeforeEach
//	public void setUp() {
//		customerService = new CustomerServiceImp();
//	}

    
    /*
     * Scenario: When customer is added successfully.
     */
    @Test
    public void test_AddCustomer_1() {
        Customer customer = mock(Customer.class);
       // Cart cart = mock(Cart.class);
        Customer customerSaved = Mockito.mock(Customer.class);
        doNothing().when(customerService).validateCustomer(customer);
        when(customerRepository.save(customer)).thenReturn(customerSaved);
      //  when(cartRepository.notify()).thenReturn();
        Customer result = customerService.addCustomer(customer);
        Assertions.assertNotNull(result);
        //	Assertions.assertEquals(customerSaved, result);
        verify(customerRepository).save(customer);
        verify(customerService).validateCustomer(customer);
    }

    /*
     * * Scenario:  When customer details are null Result:
     *   Result:   Customer not added exception
     */

    @Test
    public void test_AddCustomer_2() {
        Customer customer = Mockito.mock(Customer.class);
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
        Integer customerId = 2;
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(customer.getCustomerId()).thenReturn(customerId);
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
        Integer customerId = 2;
        Customer customer = mock(Customer.class);
        when(customer.getCustomerId()).thenReturn(customerId);
        when(customerRepository.existsById(customerId)).thenReturn(true);
        Customer result = customerService.removeCustomer(customer);
        Assertions.assertNotNull(result);
        verify(customerRepository).deleteById(customerId);

    }

    /*
     * * Scenario: When customer is not removed successfully,
     */
    @Test
    public void removeCustomerTest_2() {
        Integer customerId = 1;
        Customer customer = mock(Customer.class);
        when(customer.getCustomerId()).thenReturn(customerId);
        when(customerRepository.existsById(customerId)).thenReturn(false);
        Executable executable = () -> customerService.removeCustomer(customer);
        Assertions.assertThrows(CustomerNotRemovedException.class, executable);
        verify(customerRepository, never()).delete(customer);
    }
    /*
     * * Scenario: When customer is successfully viewed using customerId
     */

    @Test
    public void viewCustomerTest_1() {
        int customerId = 2;
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
     * * Scenario: When customer is not successfully viewed using customerId
     */

    @Test
    public void test_ViewCustomer_2() { 
    	int customerId=2;
        Customer customer = mock(Customer.class);
    //    doThrow(CustomerNotFoundException.class).when(customerService).validateCustomer(customer);
        Executable executable = () -> customerService.viewCustomer(customerId);
        Assertions.assertThrows(CustomerNotFoundException.class, executable);
        verify(customerRepository, never()).save(customer);
    }

    /*
     * scenario :When all customers are viewed successfully
     */
	
	/*
	@Test
	public void test_ViewAllCustomers_1() {
		String location="Mumbai";
		List<Customer> fetchedList=Mockito.mock(List.class);	
		Mockito.when(customerRepository.findAll(location)).thenReturn(fetchedList);
		Mockito.when(fetchedList.isEmpty()).thenReturn(false);	
		List<Customer>result = customerService.viewCustomerList(fetchedList);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(list, result);
	}	
	
	/*
	 * Scenario: when list is empty
	 * */
	/*
	@Test
	void test_ViewAllCustomers_2(){
		List<Customer>list=Mockito.mock(List.class);
		when (customerRepository.findAll()).thenReturn(list);
		when(list.isEmpty()).thenReturn(true);
		Executable executable=()->customerService.viewCustomerList();
		Assertions.assertThrows(CustomerNotFoundException.class,executable);
	}
	 
	*/
    /*
     * scenario : Customer name is empty
     */

    @Test
    public void test_ValidateName1() {
        String name = "";
        Customer customer = new Customer(name, "9863527891", "agc@ghb.com");
        Executable executable = () -> customerService.addCustomer(customer);
        Assertions.assertThrows(InvalidCustomerNameException.class, executable);
    }

    /*
     * when InvalidCustomerNameException is thrown
     * */
    @Test
    void test_validateCustomer_2() {
        Customer customer = Mockito.mock(Customer.class);
        when(customer.getName()).thenReturn("");
        Executable executable = () -> customerService.validateCustomer(customer);
        Assertions.assertThrows(InvalidCustomerNameException.class, executable);

    }


    /*
     * when InvalidrEmailIdException is thrown
     * */
    @Test
    void test_validateCustomer_3() {
        Customer customer = Mockito.mock(Customer.class);
        when(customer.getName()).thenReturn("valid");
        when(customer.getEmailId()).thenReturn("");
        Executable executable = () -> customerService.validateCustomer(customer);
        Assertions.assertThrows(InvalidEmailIdException.class, executable);

    }


}
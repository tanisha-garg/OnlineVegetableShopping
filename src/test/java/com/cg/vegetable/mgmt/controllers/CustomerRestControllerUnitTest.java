package com.cg.vegetable.mgmt.controllers;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.service.ICustomerService;
import com.cg.vegetable.mgmt.util.CustomerUtil;

import com.cg.vegetable.mgmt.dto.CustomerDetails;
import com.cg.vegetable.mgmt.dto.UpdatePasswordRequest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerRestControllerUnitTest {

	@Mock
	ICustomerService customerService;

	@Mock
	CustomerUtil util;

	@Spy
	@InjectMocks
	CustomerRestController controller;

	/*
	 * scenario : success scenario , customer is fetched
	 *
	 * input : mock customer object and stubbed viewCustomer
	 *
	 * expectation : customer should be fetched and displayed in postman
	 */

	@Test
	void test_fetchCustomerDetails_1() {
		Integer id = 7;
		Customer customer = mock(Customer.class);
		CustomerDetails customerDetails = mock(CustomerDetails.class);
		when(customerService.viewCustomer(id)).thenReturn(customer);
		when(util.toDetail(customer)).thenReturn(customerDetails);
		CustomerDetails result = controller.fetchCustomerDetails(id);
		assertSame(customerDetails, result); // CustomerDetails==result
		verify(customerService).viewCustomer(id);
		verify(util).toDetail(customer);

	}

	/*
	 * scenario : success scenario , customer is removed
	 *
	 * input : mock customer object
	 *
	 * expectation :customer with given id should be removed and string should be
	 * displayed in postman
	 */

	@Test
	void testRemoveCustomer_1() {
		Integer id = 2;
		Customer customer = mock(Customer.class);
		when(customerService.viewCustomer(id)).thenReturn(customer);
		controller.removeCustomer(id);
		verify(customerService).removeCustomer(customer);
	}


	/*
	 * scenario : success scenario , customer password is changed successfully
	 *
	 * input : mock UpdateCustomerRequest and Customer , stubbed updateCustomer
	 *
	 * expectation : customer password updated and displayed in postman
	 */
	@Test
	public void test_changeCustomerPasswordRequest() {
		Integer id = 1;
		UpdatePasswordRequest request = mock(UpdatePasswordRequest.class);
		Customer customer = mock(Customer.class);
		when(request.getPassword()).thenReturn("");
		when(customerService.viewCustomer(id)).thenReturn(customer);
		when(customerService.updateCustomer(customer)).thenReturn(customer);
		CustomerDetails details = mock(CustomerDetails.class);
		when(util.toDetail(customer)).thenReturn(details);
		CustomerDetails result = controller.updatePassword(request, id);
		Assertions.assertNotNull(result);
		Assertions.assertSame(details, result);
		verify(customerService).viewCustomer(id);
		verify(customerService).updateCustomer(customer);
		verify(util).toDetail(customer);

	}
	
//	/*
//	 * scenario : success scenario , customer list of same city returned successfully
//	 *
//	 * input : mock List class , stubbed list 
//	 *
//	 * expectation : customer list having same city will be x displayed in postman
//	 */
//

	@Test
	public void test_allCustomerByLocation() {
		String city="Panji";
		List<Customer> customerList = mock(List.class);
		when(customerService.viewCustomerList(city)).thenReturn(customerList);
		List<CustomerDetails> desiredList = mock(List.class);
		when(util.toDetailList(customerList)).thenReturn(desiredList);
		List<CustomerDetails> resultList = controller.allCustomerDetails(city);
		Assertions.assertNotNull(resultList);
		Assertions.assertSame(desiredList, resultList);
		verify(customerService).viewCustomerList(city);
		verify(util).toDetailList(customerList);
	}
}
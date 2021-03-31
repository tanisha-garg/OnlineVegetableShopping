package com.cg.vegetable.mgmt.service;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.List;
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

import com.cg.vegetable.mgmt.controllers.CustomerRestController;
import com.cg.vegetable.mgmt.entities.Address;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.util.CustomerUtil;

import com.cg.vegetable.mgmt.dto.CustomerDetails;
import com.cg.vegetable.mgmt.dto.UpdateCustomerAddressRequest;
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
	
	@Test
	 void test_fetchCustomerDetails_1() {
		Integer id=7;
		Customer customer = mock(Customer.class);
		CustomerDetails customerDetails=mock(CustomerDetails.class);
		when(customerService.viewCustomer(id)).thenReturn(customer);
		when(util.toDetail(customer)).thenReturn(customerDetails);
		CustomerDetails result=controller.fetchCustomerDetails(id);
		assertSame(customerDetails,result);     //CustomerDetails==result
		verify(customerService).viewCustomer(id);
		verify(util).toDetail(customer);
		
	}
	
	
	@Test
	void testRemoveCustomer_1() {
		Integer id=2;
		CustomerDetails request= mock(CustomerDetails.class);
		Customer customer=mock(Customer.class);
		when(customerService.viewCustomer(id)).thenReturn(customer);
		controller.removeCustomer(id);
		verify(customerService).removeCustomer(customer);
	}
	
	
	@Test
	public void test_AddCustomerRequest() {
		CustomerDetails request = mock(CustomerDetails.class);
		Customer saved = mock(Customer.class);
		when(customerService.addCustomer(any(Customer.class))).thenReturn(saved);
		CustomerDetails details = mock(CustomerDetails.class);
		when(util.toDetail(any(Customer.class))).thenReturn(details);
		CustomerDetails result = controller.addCustomer(request);
		Assertions.assertNotNull(result);
		Assertions.assertSame(details, result);
		verify(customerService).addCustomer(any(Customer.class));
		verify(util).toDetail(any(Customer.class));
	}

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
	
	@Test
	public void testUpdateCustomerDetailsRequest() {
		Integer id = 7;
		UpdateCustomerAddressRequest request = mock(UpdateCustomerAddressRequest.class);
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		when(customer.getAddress()).thenReturn(address);
		when(customerService.viewCustomer(id)).thenReturn(customer);
		CustomerDetails details = mock(CustomerDetails.class);
		when(util.toDetail(customer)).thenReturn(details);
		CustomerDetails result = controller.updateAddress(request,id);
		Assertions.assertNotNull(result);
		Assertions.assertSame(details, result);
		verify(customerService).viewCustomer(id);
		verify(customerService).updateCustomer(customer);
		verify(util).toDetail(customer);

	}

	@Test
	public void testUpdateCustomerAddressRequest() {
		Integer id = 4;
		UpdateCustomerAddressRequest request = mock(UpdateCustomerAddressRequest.class);
		Customer customer = mock(Customer.class);
		Address address = mock(Address.class);
		when(customer.getAddress()).thenReturn(address);
		when(customerService.viewCustomer(id)).thenReturn(customer);
		CustomerDetails details = mock(CustomerDetails.class);
		when(util.toDetail(customer)).thenReturn(details);
		CustomerDetails result = controller.updateAddress(request,id);
		Assertions.assertNotNull(result);
		Assertions.assertSame(details, result);
		verify(customerService).viewCustomer(id);
		verify(customerService).updateCustomer(customer);
		verify(util).toDetail(customer);

	}

//	@Test
//	public void test_allCustomerDetails() {
//		List<Customer> customerList = mock(List.class);
//		when(customerService.viewAllCustomers()).thenReturn(customerList);
//		List<CustomerDetails> desiredList = mock(List.class);
//		when(util.toDetailList(customerList)).thenReturn(desiredList);
//		List<CustomerDetails> resultList = controller.allCustomerDetails();
//		Assertions.assertNotNull(resultList);
//		Assertions.assertSame(desiredList, resultList);
//		verify(customerService).viewAllCustomers();
//		verify(util).toDetailList(customerList);
//	}
//	
	
}
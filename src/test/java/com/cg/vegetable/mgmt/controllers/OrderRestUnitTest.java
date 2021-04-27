package com.cg.vegetable.mgmt.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.vegetable.mgmt.dto.OrderDetailsResponse;
import com.cg.vegetable.mgmt.dto.PlaceOrderRequest;
import com.cg.vegetable.mgmt.entities.Cart;
import com.cg.vegetable.mgmt.entities.Order;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.service.ICartService;
import com.cg.vegetable.mgmt.service.IOrderService;
import com.cg.vegetable.mgmt.util.DateUtil;
import com.cg.vegetable.mgmt.util.OrderUtil;

@ExtendWith(MockitoExtension.class)
public class OrderRestUnitTest {
	
	@Mock
	IOrderService orderService;
	
	@Mock
	OrderUtil orderUtil;
	
	@Mock
	DateUtil dateUtil;
	
	@Mock
	ICartService cartService;
	
	@Spy
	@InjectMocks
	OrderRestController orderController;
	
	/*
	 * 
	 * Scenario: Fetching all orders placed by a customer
	 * Input: Customer id as path variable
	 * Expectation: OrderDetailsReponse sent to UI successfully
	 * 
	 * */
	
	@Test
	public void testFetchOrderByCustomerId() {
		int customerId = 1;
		List<Order> orderedList = mock(List.class);
		List<OrderDetailsResponse> responseList= mock(List.class);
		when(orderService.viewAllOrders(customerId)).thenReturn(orderedList);
		when(orderUtil.toOrderDetails(orderedList)).thenReturn(responseList);
		List<OrderDetailsResponse> result = orderController.fetchOrderDetailsByCustomerId(customerId);
		assertEquals(responseList, result);
		verify(orderService).viewAllOrders(customerId);
		verify(orderUtil).toOrderDetails(orderedList);
	}
	
	/*
	 * 
	 * Scenario: Fetching all orders placed, by passing date 
	 * Input: Customer id as path variable
	 * Expectation: OrderDetailsReponse sent to UI successfully
	 * 
	 * */
	
	@Test
	public void testFetchOrderByDate() {
		String textDate = "31 March 2021";
		LocalDate date = LocalDate.parse(textDate);
		when(dateUtil.toLocalDate(textDate)).thenReturn(date);
		List<Order> orderedList = mock(List.class);
		List<OrderDetailsResponse> responseList= mock(List.class);
		when(orderService.viewOrderList(date)).thenReturn(orderedList);
		when(orderUtil.toOrderDetails(orderedList)).thenReturn(responseList);
		List<OrderDetailsResponse> result = orderController.fetchOrderDetailsByDate(textDate);
		assertEquals(responseList, result);
		verify(orderService).viewOrderList(date);
		verify(orderUtil).toOrderDetails(orderedList);
	}
	
	/*
	 * 
	 * Scenario: Add Order
	 * Input: Data from request body
	 * Expectation: OrderDetailsResponse sent of added order
	 * 
	 * */
	
	@Test
	public void testAddOrderDetails() {
		int customerId = 1;
		Order order = mock(Order.class);
		Cart cart = mock(Cart.class);
		OrderDetailsResponse response = mock(OrderDetailsResponse.class);
		List<Vegetable> vegetableList = mock(List.class);
		PlaceOrderRequest request = mock(PlaceOrderRequest.class);
		when(request.getCustomerId()).thenReturn(customerId);
		when(cartService.viewAllItems(cart)).thenReturn(vegetableList);
		when(orderService.addOrder(order)).thenReturn(order);
		when(orderUtil.toDetails(order)).thenReturn(response);
		OrderDetailsResponse result = orderController.addOrderDetails(customerId);
		assertEquals(response, result);
		verify(orderService).addOrder(order);
		verify(orderUtil).toDetails(order);
	}

}

package com.cg.vegetable.mgmt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.vegetable.mgmt.entities.*;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.*;


@ExtendWith(MockitoExtension.class)
public class OrderServiceImplUnitTest {
	
	@Mock
	IOrderRepository orderRepository;
	
	@Mock
	ICartRepository cartRepository;
	
	@Mock
	ICartVegetableRepository cartVegetableRepository;
	
	@Mock
	IBillingRepository billingRepository;
	
	@Mock
	IBillingService billingService;

	@Spy
	@InjectMocks
	OrderServiceImpl orderService;

	/*
	 * Scenario: Order is saved successfully
	 * Input: Mock Order object, stub validate method
	 * Expectation: Verifying orderRepository#save(order) is called only once
	 */
	@Test
	public void test_AddOrder_1() {
		 int customerId = 1;
	        int orderNumber = 87778;
	        Order order = mock(Order.class);
	        Order savedOrder = mock(Order.class);
	        when(savedOrder.getOrderNo()).thenReturn(orderNumber);
	        double cost = 100.0;
	        doNothing().when(orderService).validateOrder(order);
	        LocalDate now = LocalDate.now();
	        doReturn(now).when(orderService).currentTime();
	        Cart cart = mock(Cart.class);
	        List<CartVegetable> cartVegetables = mock(List.class);
	        List<Vegetable> vegetables = mock(List.class);
	        when(order.getCustomerId()).thenReturn(customerId);
	        when(cartRepository.findCartByCustId(customerId)).thenReturn(cart);
	        when(cartVegetableRepository.findByCart(cart)).thenReturn(cartVegetables);
	        when(cartVegetables.isEmpty()).thenReturn(false);
	        when(orderRepository.save(order)).thenReturn(savedOrder);
	        doReturn(cost).when(orderService).calculateCost(cartVegetables);
	        doReturn(vegetables).when(orderService).toVegetables(cartVegetables);
	        doNothing().when(orderService).reduceVegetableStockAfterOrder(cartVegetables);
	        BillingDetails bill = mock(BillingDetails.class);
	        doReturn(bill).when(orderService).newBill();
	        Order result = orderService.addOrder(order);
	        assertNotNull(result);
	        assertSame(savedOrder, result);
	        verify(bill).setOrderId(orderNumber);
	        verify(orderRepository).save(order);
	        verify(billingService).addBill(bill);
	        verify(orderService).reduceVegetableStockAfterOrder(cartVegetables);
	        verify(orderService).toVegetables(cartVegetables);

	}
	
	/*
	 * 
	 * Scenario: Validation Fails
	 * Input: Mock Order object, stubbing validation method so that it throws exception
	 * Expectation: Throws InvalidOrderException
	 * 
	 */	

	@Test
	public void test_AddOrder_2() {
		Order order = mock(Order.class);
		doThrow(InvalidOrderException.class).when(orderService).validateOrder(order);
		Executable executable = () -> orderService.addOrder(order);
		assertThrows(InvalidOrderException.class, executable);
	}
	
	/*
	 * 
	 * Scenario: List of orders based on customer id retrieved successfully
	 * Input: Mock list and store the result in it
	 * Expectation: Verifying IOrderRepository#findByCustId(customerId) is called once
	 * 
	 * */
	
	@Test
	public void test_viewAllOrdersByCustomerId_1() {
		int customerId = 1;
		List<Order> orderList = mock(List.class);
		when(orderRepository.findByCustomerId(customerId)).thenReturn(orderList);
		when(orderList.isEmpty()).thenReturn(false);
		List<Order> resultList = orderService.viewAllOrders(customerId);
		assertNotNull(resultList);
		assertSame(orderList, resultList);
		verify(orderRepository).findByCustomerId(customerId);
	}
	
	/*
	 * 
	 * Scenario: Order not found
	 * Input: Mock list and store the result in it
	 * Expectation: Throw OrderNotFoundException
	 * 
	 * */
	
	@Test
	public void test_viewAllOrdersByCustomerId_2() {
		int customerId = 1;
		List<Order> orderList = mock(List.class);
		when(orderRepository.findByCustomerId(customerId)).thenReturn(orderList);
		when(orderList.isEmpty()).thenReturn(true);
		Executable executable = () -> orderService.viewAllOrders(customerId);
		assertThrows(OrderNotFoundException.class, executable);
		
	}
	
	/*
	 * 
	 * Scenario: List of orders based on order date, retrieved successfully
	 * Input: Mock list and store the result in it
	 * Expectation: Verifying IOrderRepository#findByOrderDate(date) is called once
	 * 
	 * */
	
	@Test
	public void test_viewOrdersListByDate_1() {
		LocalDate date = LocalDate.parse("2021-03-30");
		List<Order> orderList = mock(List.class);
		when(orderRepository.findByOrderDate(date)).thenReturn(orderList);
		when(orderList.isEmpty()).thenReturn(false);
		List<Order> resultList = orderService.viewOrderList(date);
		assertNotNull(resultList);
		assertSame(orderList, resultList);
		verify(orderRepository).findByOrderDate(date);
	}
	
	/*
	 * 
	 * Scenario: Order not found
	 * Input: Mock list and store the result in it
	 * Expectation: Throw OrderNotFoundException
	 * 
	 * */
	
	@Test
	public void test_viewOrdersListByDate_2() {
		LocalDate date = LocalDate.parse("2021-03-30");
		List<Order> orderList = mock(List.class);
		when(orderRepository.findByOrderDate(date)).thenReturn(orderList);
		when(orderList.isEmpty()).thenReturn(true);
		Executable executable = () -> orderService.viewOrderList(date);
		assertThrows(OrderNotFoundException.class, executable);
		
	}
	

	
	
	/*
	 * Scenario: Order Updated successfully
	 */

	@Test
	public void updateOrderDetailsTest_1() {
		Order order = Mockito.mock(Order.class);
		Order orderSaved = Mockito.mock(Order.class);
		Mockito.when(orderRepository.save(order)).thenReturn(orderSaved);
		Order result = orderService.updateOrderDetails(order);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderSaved, result);

	}
	/*
	 * Scenario: When there are no orders 
	 * Result : Order not updated exception
	 * 
	 */

	@Test
	public void updateOrderDetailsTest_2() {
		Order order = null;
		Executable executable = () -> orderService.updateOrderDetails(order);
		Assertions.assertThrows(OrderNotUpdatedException.class, executable);

	}
	@Test
	public void cancelOrderTest_1() {
		int orderId=1;
		Order order=Mockito.mock(Order.class);
		doNothing().when(orderService).validateOrder(order);
		Mockito.when(order.getOrderNo()).thenReturn(orderId);
		Mockito.when(orderRepository.existsById(orderId)).thenReturn(true);
		Order result=orderService.cancelOrder(orderId);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(order,result);	
		Mockito.verify(orderRepository).existsById(1);
	}
	
	@Test
	public void cancelOrderTest_2() {
		int orderId=1;
		Order order=Mockito.mock(Order.class);
		Mockito.doNothing().when(orderService).validateOrder(order);
		Mockito.when(order.getOrderNo()).thenReturn(orderId);
		Mockito.when(orderRepository.existsById(orderId)).thenReturn(false);
		Executable executable = () -> orderService.cancelOrder(orderId);
		Assertions.assertThrows(CancelOrderException.class, executable);
			
	}
	
		
	}
	



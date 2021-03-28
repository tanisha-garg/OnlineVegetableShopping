package com.cg.vegetable.mgmt.service;

import static org.mockito.Mockito.*;

import java.util.List;

import javax.persistence.EntityManager;

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
import com.cg.vegetable.mgmt.entities.Order;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.exceptions.*;
import com.cg.vegetable.mgmt.repository.IOrderRepository;
import com.cg.vegetable.mgmt.service.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplUnitTest {
	@Mock
	IOrderRepository repository;

	@Spy
	@InjectMocks
	OrderServiceImpl orderService;

	/*
	 * Scenario: Order is added successfully
	 */
	@Test
	public void test_AddOrder_1() {
		Order order = Mockito.mock(Order.class);
		Order orderSaved = Mockito.mock(Order.class);
		Mockito.when(repository.save(order)).thenReturn(orderSaved);
		Order result = orderService.addOrder(order);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(orderSaved, result);

	}
	/*
	 * Scenario: When order details are null 
	 * Result : Order not added Exception
	 */

	@Test
	public void test_AddOrder_2() {
		Order order = null;
		Executable executable = () -> orderService.addOrder(order);
		Assertions.assertThrows(OrderNotAddedException.class, executable);
	}
	/*
	 * Scenario: Order Updated successfully
	 */

	@Test
	public void updateOrderDetailsTest_1() {
		Order order = Mockito.mock(Order.class);
		Order orderSaved = Mockito.mock(Order.class);
		Mockito.when(repository.save(order)).thenReturn(orderSaved);
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
	
		
	}
	



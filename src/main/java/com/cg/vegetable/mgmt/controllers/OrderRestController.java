package com.cg.vegetable.mgmt.controllers;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vegetable.mgmt.dto.CustomerDetails;
import com.cg.vegetable.mgmt.dto.OrderDetails;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.entities.Order;
import com.cg.vegetable.mgmt.repository.IOrderRepository;
import com.cg.vegetable.mgmt.service.*;
import com.cg.vegetable.mgmt.util.OrderUtil;

@RequestMapping("/orders")
@RestController
public class OrderRestController {

	@Autowired
	private IOrderService orderService;
	@Autowired
	private OrderUtil util;
	@Autowired
	private IOrderRepository repository;

	@GetMapping("/byid/{id}")
	public OrderDetails fetchOrder(@PathVariable("id") Integer orderId) {
		Order order = orderService.findById(orderId);
		OrderDetails details = util.toDetail(order);
		return details;
	}

	
}
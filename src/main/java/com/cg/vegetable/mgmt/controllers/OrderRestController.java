package com.cg.vegetable.mgmt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vegetable.mgmt.service.IOrderService;

@RequestMapping("/orders")
@RestController
public class OrderRestController {
	
	@Autowired
	IOrderService orderService;

}

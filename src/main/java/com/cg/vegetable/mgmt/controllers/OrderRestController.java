package com.cg.vegetable.mgmt.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vegetable.mgmt.dto.OrderDetailsResponse;
import com.cg.vegetable.mgmt.dto.UpdateOrderStatusRequest;
import com.cg.vegetable.mgmt.dto.VegetablesOrderedByCustomer;
import com.cg.vegetable.mgmt.entities.Order;
import com.cg.vegetable.mgmt.service.*;
import com.cg.vegetable.mgmt.util.DateUtil;
import com.cg.vegetable.mgmt.util.OrderUtil;

@RequestMapping("/orders")
@RestController
public class OrderRestController {

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private OrderUtil orderUtil;
	
	@Autowired
	private DateUtil dateUtil;

	@GetMapping("/get/{id}")
	public OrderDetailsResponse fetchOrderDetails(@PathVariable("id") int orderId) {
		Order order = orderService.viewOrder(orderId);
		return orderUtil.toDetails(order);
	}
	
	
	@PutMapping("/updateStatus/{id}")
	public OrderDetailsResponse updateOrderStatus(@RequestBody UpdateOrderStatusRequest requestData, 
											@PathVariable("id") int id) {
		Order order = orderService.viewOrder(id);
		order.setStatus(requestData.getOrderStatus());
		order = orderService.updateOrderDetails(order);
		return orderUtil.toDetails(order);
	}
	
	@DeleteMapping("/cancel/{id}")
	public String cancelOrder(@PathVariable("id") int id) {
		orderService.cancelOrder(id);
		return "Order Cancelled Sucessfully";
		
	}
	
	@GetMapping("/getVegetableList/{id}")
	public List<VegetablesOrderedByCustomer> getVegetablesOrderedByCustomer(@PathVariable("id") int customerId){
		List<Order> orderedList = orderService.viewAllOrders(customerId);
		List<VegetablesOrderedByCustomer> vegetableList = orderUtil.toVegetableDetails(orderedList);
		return vegetableList;
	}
	
	@GetMapping("/getByDate/{date}")
	public List<OrderDetailsResponse> fetchOrderDetailsByDate(@PathVariable("date") String date){
		LocalDate dateTime = dateUtil.toLocalDate(date);
		List<Order> orderListByDate = orderService.viewOrderList(dateTime);
		List<OrderDetailsResponse> orderDetailsList = orderUtil.toOrderDetails(orderListByDate);
		return orderDetailsList;
	}

	
}
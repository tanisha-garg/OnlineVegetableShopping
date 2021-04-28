package com.cg.vegetable.mgmt.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vegetable.mgmt.dto.PlaceOrderRequest;
import com.cg.vegetable.mgmt.dto.OrderDetailsResponse;
import com.cg.vegetable.mgmt.dto.UpdateOrderStatusRequest;
import com.cg.vegetable.mgmt.entities.*;
import com.cg.vegetable.mgmt.service.*;
import com.cg.vegetable.mgmt.util.DateUtil;
import com.cg.vegetable.mgmt.util.OrderUtil;

@Validated
@RequestMapping("/orders")
@RestController
public class OrderRestController {

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private OrderUtil orderUtil;
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private ICartService cartService;
	
	@Autowired
	private DateUtil dateUtil;
	
    /*
     * 
     * Rest Controller for fetching order details by orderId
     * Path: /orders/get/29
     * @param: orderId
     * @return: OrderDetailsResponse received from orderUtil.toDetails()
     * 
     * */

	@GetMapping("/get/{orderId}")
	public OrderDetailsResponse fetchOrderDetails(@PathVariable("orderId") int orderId) {
		Order order = orderService.viewOrder(orderId);
		return orderUtil.toDetails(order);
	}
	
    /*
     * 
     * Rest Controller for updating the order status in the database
     * Path: /orders/update/status/29
     * @param: UpdateOrderStatusRequest as requestBody and orderId
     * @return: OrderDetailsResponse received from orderUtil.toDetails()
     * 
     * */
	
	
	@PutMapping("/update/status/{orderId}")
	public OrderDetailsResponse updateOrderStatus(@RequestBody UpdateOrderStatusRequest requestData, 
											@PathVariable("orderId") int orderId) {
		Order order = orderService.viewOrder(orderId);
		order.setStatus(requestData.getOrderStatus());
		order = orderService.updateOrderDetails(order);
		return orderUtil.toDetails(order);
	}
	
    /*
     * 
     * Rest Controller for deleting/canceling an order
     * Path: /orders/cancel/29
     * @param:orderId
     * @return: A string saying, "Order Cancelled Successfully"
     * 
     * */
	
	@DeleteMapping("/cancel/{orderId}")
	public String cancelOrder(@PathVariable("orderId") int orderId) {
		orderService.cancelOrder(orderId);
		return "Order Cancelled Sucessfully";
		
	}
	
    /*
     * 
     * Rest Controller for fetching all the orders placed
     * Path: /orders/getAll
     * @return: List of OrderDetailsResponse received from orderUtil.toOrderDetails()
     * 
     * */
	
	@GetMapping("/getAll")
	public List<OrderDetailsResponse> fetchAllOrders(){
		List<Order> orderList = orderService.viewOrderList();
		return orderUtil.toOrderDetails(orderList);
	}
	
    /*
     * 
     * Rest Controller for fetching list of orders placed by a customer
     * Path: /orders/get/customer/16
     * @param: id is customerId
     * @return: List of OrderDetailsResponse received from orderUtil.toOrderDetails()
     * 
     * */
	
	@GetMapping("/get/customer/{id}")
	public List<OrderDetailsResponse> fetchOrderDetailsByCustomerId(@PathVariable("id") @Min(1) int customerId){
		List<Order> orderedList = orderService.viewAllOrders(customerId);
		return orderUtil.toOrderDetails(orderedList);
	}
	
	 /*
     * 
     * Rest Controller for fetching list of orders placed by a date
     * Path: /orders/get/date/20April2021
     * @param: date 
     * @return: List of OrderDetailsResponse received from orderUtil.toOrderDetails()
     * 
     * */
	
	@GetMapping("/get/date/{date}")
	public List<OrderDetailsResponse> fetchOrderDetailsByDate(@PathVariable("date") @NotBlank String date){
		LocalDate dateTime = dateUtil.toLocalDate(date);
		List<Order> orderListByDate = orderService.viewOrderList(dateTime);
		return orderUtil.toOrderDetails(orderListByDate);
	}
	
	 /*
     * 
     * Rest Controller for adding order to the database
     * Path: /orders/add/16
     * @param: customerId
     * @return: OrderDetailsResponse received from orderUtil.toDetails()
     * 
     * Note: Here, with the help of customerId, cart is fetched and the items present in the cart are accessible,
     * 		 which helps in placing an order.
     * 
     * */	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add/{id}")
	public OrderDetailsResponse addOrderDetails(@PathVariable("id") @Min(1) int customerId) {
		Order order = new Order();
		Customer customer = customerService.viewCustomer(customerId);
		Cart cart = customer.getCart();
		order.setCustomerId(customerId);
		List<Vegetable> vegetableList = cartService.viewAllItems(cart);
		order.setVegetableList(vegetableList);
		orderService.addOrder(order);
 		return orderUtil.toDetails(order);
	}

	
}
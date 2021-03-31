package com.cg.vegetable.mgmt.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.vegetable.mgmt.dto.OrderDetailsResponse;
import com.cg.vegetable.mgmt.dto.VegetablesOrderedByCustomer;
import com.cg.vegetable.mgmt.entities.Customer;
import com.cg.vegetable.mgmt.entities.Order;
import com.cg.vegetable.mgmt.entities.Vegetable;
import com.cg.vegetable.mgmt.service.ICustomerService;

@Component
public class OrderUtil {

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private DateUtil dateUtil;

	public OrderDetailsResponse toDetails(Order order) {

		OrderDetailsResponse orderDetails = new OrderDetailsResponse();
		Customer customer = customerService.viewCustomer(order.getCustomerId());
		String dateText = dateUtil.toText(order.getOrderDate());		
		orderDetails.setCustomerName(customer.getName());
		orderDetails.setDate(dateText);
		orderDetails.setOrderId(order.getOrderNo());
		orderDetails.setStatus(order.getStatus());
		orderDetails.setTotalAmount(order.getTotalAmount());		
		List<Vegetable> vegetablesList = order.getVegetableList();
		List<VegetablesOrderedByCustomer> desiredList = orderedVegetableDetails(vegetablesList);		
		orderDetails.setVegetableList(desiredList);
		return orderDetails;
	}
	
//	public List<VegetablesOrderedByCustomer> toVegetableDetails(List<Order> orderList){
//		List<VegetablesOrderedByCustomer> desiredList = new ArrayList<>();
//		for(Order order : orderList) {
//			List<Vegetable> vegetableList = order.getVegetableList();
//			desiredList = orderedVegetableDetails(vegetableList);			
//		}				
//		return desiredList;
//	}
	
	public List<OrderDetailsResponse> toOrderDetails(List<Order> orderList) {
		List<OrderDetailsResponse> orderDetailsList = new ArrayList<>();
		for(Order order : orderList) {
			OrderDetailsResponse orderDetails = toDetails(order);
			orderDetailsList.add(orderDetails);
		}
		return orderDetailsList;
	}
	
	public List<VegetablesOrderedByCustomer> orderedVegetableDetails(List<Vegetable> vegetableList){
		List<VegetablesOrderedByCustomer> orderedVegetableList = new ArrayList<>();
		for(Vegetable vegetable : vegetableList) {
			VegetablesOrderedByCustomer vegetableDetails = new VegetablesOrderedByCustomer();
			vegetableDetails.setVegetableName(vegetable.getName());
			vegetableDetails.setVegetableCategory(vegetable.getCategory());
			vegetableDetails.setVegetableType(vegetable.getType());
			orderedVegetableList.add(vegetableDetails);
		}
		return orderedVegetableList;
	}

}

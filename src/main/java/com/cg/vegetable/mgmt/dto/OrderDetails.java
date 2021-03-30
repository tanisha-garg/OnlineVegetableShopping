package com.cg.vegetable.mgmt.dto;

import java.util.List;

import com.cg.vegetable.mgmt.entities.Vegetable;

public class OrderDetails {
	
	private int orderId;
	private String customerName;
	private double totalAmount;
	private String date;
	private String status;
	
	private List<VegetablesOrderedByCustomer> vegetableList;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<VegetablesOrderedByCustomer> getVegetableList() {
		return vegetableList;
	}

	public void setVegetableList(List<VegetablesOrderedByCustomer> vegetableList) {
		this.vegetableList = vegetableList;
	}

	
	

}

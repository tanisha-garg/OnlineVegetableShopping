package com.cg.vegetable.mgmt.dto;

import java.util.List;

public class PlaceOrderRequest {
	
	private int customerId;
	
	private List<VegetablesOrderedByCustomer> vegetableList;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<VegetablesOrderedByCustomer> getVegetableList() {
		return vegetableList;
	}

	public void setVegetableList(List<VegetablesOrderedByCustomer> vegetableList) {
		this.vegetableList = vegetableList;
	}
	
	

}

package com.cg.vegetable.mgmt.dto;

import javax.validation.constraints.Min;

public class PlaceOrderRequest {
	
	@Min(1)
	private int customerId;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	

}

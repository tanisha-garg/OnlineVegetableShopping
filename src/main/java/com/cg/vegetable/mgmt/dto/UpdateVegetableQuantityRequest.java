package com.cg.vegetable.mgmt.dto;

import javax.validation.constraints.NotBlank;

public class UpdateVegetableQuantityRequest {
	private int vegId;
	
	private int quantity;
	public int getVegId() {
		return vegId;
	}
	public void setVegId(int vegId) {
		this.vegId = vegId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}

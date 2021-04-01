package com.cg.vegetable.mgmt.dto;

public class RemoveVegetableFromCart {
	int custId;
	int vegId;
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getVegId() {
		return vegId;
	}
	public void setVegId(int vegId) {
		this.vegId = vegId;
	}
	
}

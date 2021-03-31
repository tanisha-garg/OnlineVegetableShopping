package com.cg.vegetable.mgmt.dto;

public class IncreaseQuantityOfVegetable {
	
	int custId;
	int vegId;
	int quantity;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}

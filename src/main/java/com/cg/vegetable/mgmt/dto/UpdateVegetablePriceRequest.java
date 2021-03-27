package com.cg.vegetable.mgmt.dto;

public class UpdateVegetablePriceRequest {

	int vegId;
	double price;
	public int getVegId() {
		return vegId;
	}
	public void setVegId(int vegId) {
		this.vegId = vegId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}

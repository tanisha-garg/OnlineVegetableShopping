package com.cg.vegetable.mgmt.dto;

import javax.validation.constraints.NotBlank;

public class AddVegetableRequest {
	
	@NotBlank
	private String name;
	@NotBlank
	private String type;
	@NotBlank
	private String category;
	@NotBlank
	private double price;
	@NotBlank
	private int quantity;
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}

package com.cg.vegetable.mgmt.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vegetable {
	
	@Id
	@GeneratedValue
	private int vegId;
	private String name;
	private String type;
	private String category;
	private double price;
	private int quantity;
	
	public Vegetable() {
		
	}
	public Vegetable(String name, String type, String category, double price, int quantity) {
		this.name = name;
		this.type = type;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}
	public int getVegId() {
		return vegId;
	}
	public void setVegId(int vegId) {
		this.vegId = vegId;
	}
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

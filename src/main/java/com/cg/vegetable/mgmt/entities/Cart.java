package com.cg.vegetable.mgmt.entities;

import java.util.List;

public class Cart {
private int cartId;
private int custId;
private List<Vegetable> vegetables;
public int getCartId() {
	return cartId;
}
public void setCartId(int cartId) {
	this.cartId = cartId;
}
public int getCustId() {
	return custId;
}
public void setCustId(int custId) {
	this.custId = custId;
}
public List<Vegetable> getVegetables() {
	return vegetables;
}
public void setVegetables(List<Vegetable> vegetables) {
	this.vegetables = vegetables;
}

}

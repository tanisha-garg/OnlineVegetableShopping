package com.cg.vegetable.mgmt.entities;

import java.util.List;

public class CartDTO {
private int cartId;
private int custId;
private List<VegetableDTO> vegetables;
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
public List<VegetableDTO> getVegetables() {
	return vegetables;
}
public void setVegetables(List<VegetableDTO> vegetables) {
	this.vegetables = vegetables;
}

}

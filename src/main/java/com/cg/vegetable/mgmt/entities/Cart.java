package com.cg.vegetable.mgmt.entities;

import java.util.List;
import com.cg.vegetable.mgmt.entities.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cart {
	@GeneratedValue
	@Id
	private int cartId;
	private int custId;
	@OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
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

	@Override
	public int hashCode() {
		return cartId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (cartId != other.cartId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", custId=" + custId + ", vegetables=" + vegetables + "]";
	}
	
	

}

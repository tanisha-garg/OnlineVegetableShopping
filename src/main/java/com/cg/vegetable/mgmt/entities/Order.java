package com.cg.vegetable.mgmt.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
public class Order {

	@GeneratedValue
	@Id
	private int orderNo;
	private int customerId;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Vegetable> vegetableList;

	private double totalAmount;
	private LocalDate orderDate;
	private String status;

	public Order() {

	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int custId) {
		this.customerId = custId;
	}

	public List<Vegetable> getVegetableList() {
		return vegetableList;
	}

	public void setVegetableList(List<Vegetable> updateList) {
		this.vegetableList = updateList;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return orderNo;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderNo != other.orderNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", custId=" + customerId + ", totalAmount="
				+ totalAmount + ", orderDate=" + orderDate + ", status=" + status + "]";
	}

}

package com.cg.vegetable.mgmt.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="order_table")
public class Order {
	
	@GeneratedValue
	@Id
	private int orderNo;
	private int custId;
	
	@Column(unique = false)
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Vegetable> vegList;
	
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

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public List<Vegetable> getVegList() {
		return vegList;
	}

	public void setVegList(List<Vegetable> vegList) {
		this.vegList = vegList;
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

}

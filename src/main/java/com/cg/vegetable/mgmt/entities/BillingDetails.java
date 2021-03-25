package com.cg.vegetable.mgmt.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BillingDetails {

	@GeneratedValue
	@Id
	private int billingId;
	private int orderId;
	private String transactionMode;
	private LocalDateTime transactionDate;
	private String transactionStatus;
	
	public BillingDetails() {
		
	}
	
	public BillingDetails(int orderId, String transactionMode, String transactionStatus) {
		this.orderId = orderId;
		this.transactionMode = transactionMode;
		this.transactionStatus = transactionStatus;
	}

	public int getBillingId() {
		return billingId;
	}

	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

}

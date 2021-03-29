package com.cg.vegetable.mgmt.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class BillingDetails {

	@GeneratedValue
	@Id
	private int billingId;
	private int orderId;
	private String transactionMode;
	private LocalDateTime transactionDate;
	private String transactionStatus;

	@OneToOne
	private Address address;
	
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return billingId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillingDetails other = (BillingDetails) obj;
		if (billingId != other.billingId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BillingDetails [billingId=" + billingId + ", orderId=" + orderId + ", transactionMode="
				+ transactionMode + ", transactionDate=" + transactionDate + ", transactionStatus=" + transactionStatus
				+ ", address=" + address + "]";
	}
	
	

}

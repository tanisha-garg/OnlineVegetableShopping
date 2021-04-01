package com.cg.vegetable.mgmt.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class AddBillDetailsRequest {
	
	private int billingId;
	@Min(1)
	private int orderId;
	@NotBlank
	private String transactionMode;
	@NotBlank
	private String transactionStatus;

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

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	

}

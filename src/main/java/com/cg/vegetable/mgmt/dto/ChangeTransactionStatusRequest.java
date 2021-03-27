package com.cg.vegetable.mgmt.dto;

public class ChangeTransactionStatusRequest {
	
	private int billingId;
	private String transactionStatus;
	
	public ChangeTransactionStatusRequest() {
		
	}

	public int getBillingId() {
		return billingId;
	}

	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	

}

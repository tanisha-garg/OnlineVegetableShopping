package com.cg.vegetable.mgmt.dto;

import javax.validation.constraints.NotBlank;

public class UpdateTransactionStatusRequest {
	
	@NotBlank
	private String transactionStatus;

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
	

}

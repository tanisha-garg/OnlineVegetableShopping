package com.cg.vegetable.mgmt.dto;

import javax.validation.constraints.NotBlank;

public class UpdateTransactionModeRequest {
	
	@NotBlank
	private String transactionMode;

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	
	

}

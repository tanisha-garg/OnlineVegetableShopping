package com.cg.vegetable.mgmt.exceptions;

public class InvalidTransactionStatusException extends RuntimeException{
	
	public InvalidTransactionStatusException(String msg) {
		super(msg);
	}

}

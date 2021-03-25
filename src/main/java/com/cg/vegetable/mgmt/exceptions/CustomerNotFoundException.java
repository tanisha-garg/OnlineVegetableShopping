package com.cg.vegetable.mgmt.exceptions;

public class CustomerNotFoundException extends RuntimeException{
	
	public CustomerNotFoundException(String msg) {
		super(msg);
	}

}

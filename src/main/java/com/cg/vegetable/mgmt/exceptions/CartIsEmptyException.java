package com.cg.vegetable.mgmt.exceptions;

public class CartIsEmptyException extends RuntimeException{
	public CartIsEmptyException(String msg) {
		super(msg);
	}
}

package com.cg.vegetable.mgmt.exceptions;

public class OrderNotAddedException extends RuntimeException {
	public OrderNotAddedException(String msg) {
		super(msg);
	}
}
package com.cg.vegetable.mgmt.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.vegetable.mgmt.exceptions.BillNotFoundException;
import com.cg.vegetable.mgmt.exceptions.InvalidBillException;
import com.cg.vegetable.mgmt.exceptions.InvalidTransactionModeException;
import com.cg.vegetable.mgmt.exceptions.InvalidTransactionStatusException;

@RestControllerAdvice
public class CentralizedExceptionHandler {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(BillNotFoundException.class)
	public String handleBillNotFound(BillNotFoundException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidBillException.class)
	public String handleInvalidBill(InvalidBillException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidTransactionStatusException.class)
	public String handleInvalidTransactionStatus(InvalidTransactionStatusException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidTransactionModeException.class)
	public String handleInvalidTransactionMode(InvalidTransactionModeException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String handleCatchAllException(Exception e) {
		return e.getMessage();
	}
	
	
	

}

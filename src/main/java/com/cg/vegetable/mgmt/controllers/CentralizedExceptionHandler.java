package com.cg.vegetable.mgmt.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.vegetable.mgmt.exceptions.BillNotFoundException;
import com.cg.vegetable.mgmt.exceptions.InvalidBillException;
import com.cg.vegetable.mgmt.exceptions.InvalidTransactionModeException;
import com.cg.vegetable.mgmt.exceptions.InvalidTransactionStatusException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableCategoryException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableIdException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableNameException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetablePriceException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableQuantityException;
import com.cg.vegetable.mgmt.exceptions.InvalidVegetableTypeException;
import com.cg.vegetable.mgmt.exceptions.VegetableNotFoundException;

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
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(VegetableNotFoundException.class)
	public String handleVegetableNotFound(VegetableNotFoundException e) {
		return e.getMessage();
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidVegetableIdException.class)
	public String handleInvalidVegetable(InvalidVegetableIdException e) {
		return e.getMessage();
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidVegetableNameException.class)
	public String handleInvalidNameVegetable(InvalidVegetableNameException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidVegetableCategoryException.class)
	public String handleInvalidCategoryVegetable(InvalidVegetableCategoryException e) {
		return e.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidVegetableTypeException.class)
	public String handleInvalidTypeVegetable(InvalidVegetableTypeException e) {
		return e.getMessage();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidVegetablePriceException.class)
	public String handleInvalidPriceVegetable(InvalidVegetablePriceException e) {
		return e.getMessage();
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidVegetableQuantityException.class)
	public String handleInvalidQuantityVegetable(InvalidVegetableQuantityException e) {
		return e.getMessage();
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String handleCatchAllException(Exception e) {
		return e.getMessage();
	}
}

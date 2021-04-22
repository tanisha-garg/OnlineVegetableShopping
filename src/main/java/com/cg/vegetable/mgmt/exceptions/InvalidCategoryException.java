package com.cg.vegetable.mgmt.exceptions;

public class InvalidCategoryException extends RuntimeException{

    public InvalidCategoryException() {

    }

    public InvalidCategoryException(String msg) {
        super(msg);
    }

}

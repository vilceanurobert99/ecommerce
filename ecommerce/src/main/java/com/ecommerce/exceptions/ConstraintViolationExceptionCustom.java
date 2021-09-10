package com.ecommerce.exceptions;

public class ConstraintViolationExceptionCustom extends RuntimeException{

    public ConstraintViolationExceptionCustom() {
        super("Bad Object Request");
    }

}

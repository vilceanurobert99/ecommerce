package com.ecommerce.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Object not found");
    }
}
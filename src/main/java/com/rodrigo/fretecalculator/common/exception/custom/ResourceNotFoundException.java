package com.rodrigo.fretecalculator.common.exception.custom;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ShippingBaseException{
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

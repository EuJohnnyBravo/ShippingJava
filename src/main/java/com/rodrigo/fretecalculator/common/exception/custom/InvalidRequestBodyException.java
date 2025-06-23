package com.rodrigo.fretecalculator.common.exception.custom;

import org.springframework.http.HttpStatus;

import java.util.List;

public class InvalidRequestBodyException extends ShippingBaseException{
    public InvalidRequestBodyException(List<String> errors) {
        super(errors, HttpStatus.BAD_REQUEST);
    }

    public InvalidRequestBodyException(String error) {
        super(error, HttpStatus.BAD_REQUEST);
    }
}

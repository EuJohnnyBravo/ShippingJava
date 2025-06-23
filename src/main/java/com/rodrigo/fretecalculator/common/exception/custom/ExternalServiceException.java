package com.rodrigo.fretecalculator.common.exception.custom;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends ShippingBaseException{
    public ExternalServiceException(String errors) {
        super(errors, HttpStatus.BAD_REQUEST);
    }
}

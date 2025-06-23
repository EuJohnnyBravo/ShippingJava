package com.rodrigo.fretecalculator.common.exception;

import com.rodrigo.fretecalculator.common.exception.custom.ShippingBaseException;
import com.rodrigo.fretecalculator.contract.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ShippingBaseException.class)
    public ResponseEntity<ExceptionResponse> handleShippingBaseException(ShippingBaseException ex){
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ExceptionResponse(ex.getHttpStatus().value(), ex.getErrors()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(new ExceptionResponse(400, errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneric(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(500, List.of("Erro interno do servidor", ex.getMessage())));
    }
}

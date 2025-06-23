package com.rodrigo.fretecalculator.contract.exception.response;

import java.time.LocalDateTime;
import java.util.List;

public record ExceptionResponse(
        LocalDateTime timeStamp,
        int status,
        List<String> errors
) {
    public ExceptionResponse(int status, List<String> errors) {
        this(LocalDateTime.now(), status, errors);
    }
}


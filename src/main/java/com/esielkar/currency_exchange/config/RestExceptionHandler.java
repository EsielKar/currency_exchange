package com.esielkar.currency_exchange.config;

import com.esielkar.currency_exchange.exception.CurrencyNotFoundException;
import com.esielkar.currency_exchange.exception.NegativeValueException;
import com.esielkar.currency_exchange.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Controlador de errores a nivel global
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Currency not found"));
    }

    @ExceptionHandler(NegativeValueException.class)
    public ResponseEntity<ErrorResponse> handleNegativeValue() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("It's not possible to use negative values."));
    }
}

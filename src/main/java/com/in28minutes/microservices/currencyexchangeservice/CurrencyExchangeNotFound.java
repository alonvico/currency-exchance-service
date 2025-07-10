package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CurrencyExchangeNotFound extends RuntimeException{

    public CurrencyExchangeNotFound(String message) {
        super(message);
    }

}

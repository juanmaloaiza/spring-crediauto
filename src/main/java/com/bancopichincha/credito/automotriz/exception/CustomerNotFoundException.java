package com.bancopichincha.credito.automotriz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends Exception {

    private static final String DESCRIPTION = "Cliente no existe";
    private static final long serialVersionUID = 6830756676887746370L;

    public CustomerNotFoundException() {
        super(DESCRIPTION);
    }
}

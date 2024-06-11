package dev.mayankg.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistingException extends RuntimeException {

    public CustomerAlreadyExistingException(String message) {
        super(message);
    }
}
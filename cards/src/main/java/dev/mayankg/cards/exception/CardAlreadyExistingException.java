package dev.mayankg.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CardAlreadyExistingException extends RuntimeException {

    /**
     * Constructs a new LoanAlreadyExistingException exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CardAlreadyExistingException(String message) {
        super(message);
    }
}
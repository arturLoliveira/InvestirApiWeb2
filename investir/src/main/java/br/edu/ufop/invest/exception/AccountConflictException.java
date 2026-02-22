package br.edu.ufop.invest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT)
public class AccountConflictException extends RuntimeException {
    public AccountConflictException(String detail) {
        super(detail);
    }
}
package br.com.gianvittorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import java.io.Serializable;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticationException extends AuthenticationException implements Serializable {
    private static final long serialVersionUID = -1334081625664317708L;

    public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }
}

package br.com.gianvittorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyUnsupportedMathOperationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -1334081625664317708L;

    public MyUnsupportedMathOperationException(String ex) {
        super(ex);
    }
}

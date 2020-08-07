package br.com.gianvittorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -1334081625664317708L;

    public MyFileNotFoundException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public MyFileNotFoundException(String ex) {
        super(ex);
    }
}

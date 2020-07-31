package br.com.gianvittorio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -1334081625664317708L;

    public FileStorageException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public FileStorageException(String ex) {
        super(ex);
    }
}

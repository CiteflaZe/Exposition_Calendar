package com.project.exception;

public class PaginationRuntimeException extends RuntimeException {
    public PaginationRuntimeException(String message) {
        super(message);
    }

    public PaginationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaginationRuntimeException(Throwable cause) {
        super(cause);
    }
}

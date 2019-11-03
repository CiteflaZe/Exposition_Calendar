package com.project.Exception;

public class DataBaseRuntimeException extends RuntimeException {
    public DataBaseRuntimeException(String message) {
        super(message);
    }

    public DataBaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseRuntimeException(Throwable cause) {
        super(cause);
    }
}

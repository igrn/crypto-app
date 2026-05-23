package com.javarush.zelenin.exception;

/**
 * Throws to indicate that the application has failed to execute a task.
 */
public class AppException extends RuntimeException {

    public AppException() {}

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}

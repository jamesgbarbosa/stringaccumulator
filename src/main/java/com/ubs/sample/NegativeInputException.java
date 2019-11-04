package com.ubs.sample;

public class NegativeInputException extends Exception {
    public NegativeInputException() {
        super();
    }

    public NegativeInputException(String message) {
        super(message);
    }

    public NegativeInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeInputException(Throwable cause) {
        super(cause);
    }

    protected NegativeInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

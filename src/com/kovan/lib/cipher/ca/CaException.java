package com.kovan.lib.cipher.ca;

public class CaException extends RuntimeException {
    private static final long serialVersionUID = 1975826359940095990L;

    public CaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaException(String message) {
        super(message);
    }

    public CaException(Throwable cause) {
        super(cause);
    }
}

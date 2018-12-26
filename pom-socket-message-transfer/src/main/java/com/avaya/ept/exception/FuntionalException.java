package com.avaya.ept.exception;

public class FuntionalException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7644934351698894152L;
    
    public FuntionalException() {
        super();
    }
    
    public FuntionalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public FuntionalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FuntionalException(String message) {
        super(message);
    }

    public FuntionalException(Throwable cause) {
        super(cause);
    }
}

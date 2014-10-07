package com.zaizi.automation.exceptions;

/**
 * @author kvaratharaja@zaizi.com
 *
 */
public class IterableException extends Exception {
    
    /**
     * Define serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public IterableException () {

    }

    /**
     * @param message
     */
    public IterableException (String message) {
        super (message);
    }

    /**
     * @param cause
     */
    public IterableException (Throwable cause) {
        super (cause);
    }

    /**
     * @param message
     * @param cause
     */
    public IterableException (String message, Throwable cause) {
        super (message, cause);
    }
}

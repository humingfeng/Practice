package com.practice.exception;

/**
 * @author Xushd  2017/12/24 23:57
 */
public class PermissonException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public PermissonException(String message) {
        super(message);
    }


}

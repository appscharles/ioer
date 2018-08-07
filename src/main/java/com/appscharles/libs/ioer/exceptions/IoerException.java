package com.appscharles.libs.ioer.exceptions;

/**
 * The type Updater exception.
 */
public class IoerException extends Exception {
    /**
     * The Serial version uid.
     */
    static final long serialVersionUID = 7813455828146020432L;

    /**
     * Instantiates a new Updater exception.
     */
    public IoerException() {
        super();
    }

    /**
     * Instantiates a new Updater exception.
     *
     * @param message the message
     */
    public IoerException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Updater exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public IoerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Updater exception.
     *
     * @param cause the cause
     */
    public IoerException(Throwable cause) {
        super(cause);
    }
}


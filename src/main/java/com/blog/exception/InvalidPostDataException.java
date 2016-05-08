package com.blog.exception;

/**
 * @author Timur Berezhnoi
 */
public class InvalidPostDataException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidPostDataException(String message) {
        super(message);
    }
}
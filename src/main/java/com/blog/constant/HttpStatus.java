package com.blog.constant;

/**
 * @author Timur Berezhnoi
 */
public enum HttpStatus {

    OK(200),
    CREATED(201),
    BAD_REQUEST(400);

    HttpStatus(int code) {
        this.code = code;
    }

    private int code;

    /**
     * Gets the HTTP status code
     * @return the status code number
     */
    public int getCode() {
        return code;
    }
}

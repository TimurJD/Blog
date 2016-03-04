package com.blog.constant;

/**
 * @author Timur Berezhnoi
 */
public enum ResponseMessage {

    // Errors whensignUp
    INVALID_USER_EMAIL("Invalid user email."),
    INVALID_USER_NAME("Invalid user name."),
    INVALID_USER_PASSWORD("Invalid user password."),
    EMAIL_IN_USE("Invalid email or user with the email is alredy in use!"),
    EMPTY_BODY("Body cannot be empty."),

    // Successful when signUp
    SIGNED_UP("Succesfully signedUp");

    ResponseMessage(String message) {
        this.message = message;
    }

    private String message;

    /**
     * Gets the response message
     * @return the response message
     */
    public String getMessage() {
        return message;
    }
}
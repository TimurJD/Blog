package com.blog.constant;

/**
 * @author Timur Berezhnoi
 */
public enum ResponseMessage {

    // Errors when signUp/signIn
    INVALID_USER_EMAIL("Invalid user email."),
    INVALID_USER_NAME("Invalid user name."),
    INVALID_USER_PASSWORD("Invalid user password."),
    EMAIL_IN_USE("Invalid email or user with the email is alredy in use!"),
    EMPTY_BODY("Body cannot be empty."),
    LOGIN_FAIL("Email or password is wrong."),

    // Successful when signUp
    SIGNED_UP("Successfully signedUp"),

    // Successful when login
    LOGGED_IN("Successful logged in."),

    // Errors when create new post
    INVALID_POST_TITLE("Invalid post title."),
    INVALID_POST_BODY("Invalid post body.");

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
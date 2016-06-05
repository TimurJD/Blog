package com.blog.constant;

/**
 * @author Timur Berezhnoi
 */
public enum ResponseMessage {

    // Errors when signUp/signIn
    INVALID_EMAIL_NULL("Email can't be null."),
    INVALID_EMAIL_LENGTH("Email can't be more than 25 characters"),
    INVALID_EMAIL_PATTERN("Invalid email pattern"),
    INVALID_PASSWORD_NULL("Password can't be null."),
    INVALID_PASSWORD_LENGTH("Password should be from 6 to 15 length"),
    INVALID_FIRST_NAME_NULL("User first name can't be null."),
    INVALID_FIRST_NAME_PATTERN("Invalid user first name."),
    INVALID_LAST_NAME_NULL("User last name can't be null."),
    INVALID_LAST_NAME_PATTERN("Invalid user last name."),
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
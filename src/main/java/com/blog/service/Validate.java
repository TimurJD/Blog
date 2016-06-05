package com.blog.service;

import static com.blog.constant.ResponseMessage.*;

/**
 * @author Timur Berezhnoi
 */
final class Validate {

    private static final String NAME_PATTERN = "^[a-zA-Z0-9_-]{3,20}$";

    private Validate() {
        throw new UnsupportedOperationException();
    }

    static void validateEmail(String email, Notification notification) {
        String emailPattern = "^[a-z0-9-\\+]+(\\.[a-z0-9-]+)*@"
                + "[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$";

        if(email == null) {
            notification.addError(INVALID_EMAIL_NULL.getMessage());
        } else if(email.length() > 25) {
            notification.addError(INVALID_EMAIL_LENGTH.getMessage());
        } else if(!email.matches(emailPattern)) {
            notification.addError(INVALID_EMAIL_PATTERN.getMessage());
        }
    }

    static void validatePassword(String password, Notification notification) {
        String passwordPattern = "^.{6,15}$";
        if (password == null) {
            notification.addError(INVALID_PASSWORD_NULL.getMessage());
        } else if(!password.matches(passwordPattern)) {
            notification.addError(INVALID_PASSWORD_LENGTH.getMessage());
        }
    }

    static void validateFirstName(String firstName, Notification notification) {
        if(firstName == null) {
            notification.addError(INVALID_FIRST_NAME_NULL.getMessage());
        } else if(!firstName.matches(NAME_PATTERN)) {
            notification.addError(INVALID_FIRST_NAME_NULL.getMessage());
        }
    }

    static void validateLastName(String lastName, Notification notification) {
        if(lastName == null) {
            notification.addError(INVALID_LAST_NAME_NULL.getMessage());
        } else if(!lastName.matches(NAME_PATTERN)) {
            notification.addError(INVALID_LAST_NAME_PATTERN.getMessage());
        }
    }
}
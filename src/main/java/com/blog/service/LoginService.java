package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.entity.User;
import com.blog.exception.InvalidUserDataException;
import com.blog.util.PasswordUtil;

import static com.blog.constant.ResponseMessage.*;

/**
 * @author Timur Berezhnoi
 */
public class LoginService {

    private final UserDAO userDAO;

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String email, String password) throws InvalidUserDataException {
        if(email == null) {
            throw new InvalidUserDataException(INVALID_USER_EMAIL.getMessage());
        }

        String emailLowerCase = email.toLowerCase();

        validateLoginData(emailLowerCase, password);

        return verifyUserPassword(emailLowerCase, password);
    }

    private void validateLoginData(String email, String password) throws InvalidUserDataException {
        String emailPattern = "^[a-z0-9-\\+]+(\\.[a-z0-9-]+)*@"
                + "[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$";

        String passwordPattern = "^.{6,15}$";

        if(email == null || email.length() > 25 || !email.matches(emailPattern)) {
            throw new InvalidUserDataException(INVALID_USER_EMAIL.getMessage());
        }

        if (password == null || !password.matches(passwordPattern)) {
            throw new InvalidUserDataException(INVALID_USER_PASSWORD.getMessage());
        }
    }

    private User verifyUserPassword(String email, String password) throws InvalidUserDataException {
        User user = userDAO.getUserByEmail(email);
        if(user == null || !PasswordUtil.verifyPassword(password, user.getPassword())) {
            throw new InvalidUserDataException(LOGIN_FAIL.getMessage());
        } else {
            return user;
        }
    }
}
package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.entity.User;

/**
 * @author Timur Berezhnoi
 */
public class LoginService {

    private final UserDAO userDAO;

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Notification validateLoginData(String email, String password) {
        Notification notification = new Notification();

        Validate.validateEmail(email, notification);
        Validate.validatePassword(password, notification);

        return notification;
    }

    public User getUser(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if(user == null || !PasswordHasher.verifyPassword(password, user.getPassword())) {
            return null;
        } else {
            return user;
        }
    }
}
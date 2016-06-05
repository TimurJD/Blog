package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.entity.User;

/**
 * @author Timur Berezhnoi
 */
public class SignUpService {

	private final UserDAO userDAO;

    public SignUpService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

	public Notification validate(User user) {
        Notification notification = new Notification();

        Validate.validateEmail(user.getEmail(), notification);
        Validate.validatePassword(user.getPassword(), notification);
        Validate.validateFirstName(user.getFirstName(), notification);
        Validate.validateLastName(user.getLastName(), notification);

        return notification;
    }

	public boolean isUserExists(String email) {
		return userDAO.getUserByEmail(email) != null;
	}

    public void signUp(User user) {
        user.setPassword(PasswordHasher.hashPassword(user.getPassword()));
        userDAO.addUser(user);
    }
}
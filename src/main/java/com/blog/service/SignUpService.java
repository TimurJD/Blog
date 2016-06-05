package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.entity.User;

import static com.blog.constant.ResponseMessage.EMAIL_IN_USE;

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
        checkUser(user.getEmail(), notification);

        return notification;
    }

	private void checkUser(String email, Notification notification) {
        if(userDAO.getUserByEmail(email) != null) {
            notification.addError(EMAIL_IN_USE.getMessage());
        }
	}

    public void signUp(User user) {
        user.setPassword(PasswordHasher.hashPassword(user.getPassword()));
        userDAO.addUser(user);
    }
}
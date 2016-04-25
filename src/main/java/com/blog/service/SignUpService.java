package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.entity.User;
import com.blog.exception.InvalidUserDataException;
import com.blog.util.PasswordUtil;

import static com.blog.constant.ResponseMessage.*;

/**
 * @author Timur Berezhnoi
 */
public class SignUpService {

	private final UserDAO userDAO;

    public SignUpService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

	public void signUp(User user) throws InvalidUserDataException {
		validateUser(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword());
		
		user.setEmail(user.getEmail().toLowerCase());
		
		if(isUserExists(user.getEmail())) {
			throw new InvalidUserDataException(EMAIL_IN_USE.getMessage());
		}
		
		user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
		userDAO.addUser(user);
	}

	private boolean isUserExists(String email) {
		return userDAO.getUserByEmail(email) != null;
	}

	private void validateUser(String email, String firstName, String lastName, String password) throws InvalidUserDataException {
        String emailPattern = "^[a-z0-9-\\+]+(\\.[a-z0-9-]+)*@"
                + "[a-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,})$";

        String userNamePattern = "^[a-zA-Z0-9_-]{3,20}$";
        String passwordPattern = "^.{6,15}$";

        if(email == null || email.length() > 25 || !email.matches(emailPattern)) {
            throw new InvalidUserDataException(INVALID_USER_EMAIL.getMessage());
        }

        if(firstName == null || !firstName.matches(userNamePattern)) {
            throw new InvalidUserDataException(INVALID_USER_NAME.getMessage());
        }

        if(lastName == null || !lastName.matches(userNamePattern)) {
            throw new InvalidUserDataException(INVALID_USER_NAME.getMessage());
        }

        if (password == null || !password.matches(passwordPattern)) {
            throw new InvalidUserDataException(INVALID_USER_PASSWORD.getMessage());
        }
	}
}
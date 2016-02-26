package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.dao.mongoimpl.UserDAOMongo;
import com.blog.db.DBManager;
import com.blog.entity.User;
import com.blog.exception.InvalidUserData;
import com.blog.util.PasswordUtil;

import static com.blog.constant.ResponseMessage.*;

/**
 * @author Timur Berezhnoi
 */
public class SignUpService {

	private UserDAO userDAO = new UserDAOMongo(DBManager.INSTANCE.getDataBase());

	public void signUp(User user) throws InvalidUserData {		
		validateUser(user.getEmail(), user.getName(), user.getPassword());
		
		user.setEmail(user.getEmail().toLowerCase());
		
		if(isUserExists(user.getEmail())) {
			throw new InvalidUserData(EMIL_IN_USE.getMessage());
		}
		
		user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
		userDAO.addUser(user);
	}

	public boolean isUserExists(String email) {
		return userDAO.getUserByEmail(email) != null;
	}

	private void validateUser(String email, String name, String password) throws InvalidUserData {
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		String userNamePattern = "^[a-zA-Z0-9_-]{3,}$";
		String passwordPattern = "^.{6,15}$";

		if(email == null || email.length() > 25 || !email.matches(emailPattern)) {
			throw new InvalidUserData(INVALID_USER_EMAIL.getMessage());
		}

		if(name == null || name.length() > 20 || !name.matches(userNamePattern)) {
			throw new InvalidUserData(INVALID_USER_NAME.getMessage());
		}
		
		if (password == null || !password.matches(passwordPattern)) {
			throw new InvalidUserData(INVALID_USER_PASSWORD.getMessage());
        }
	}
}
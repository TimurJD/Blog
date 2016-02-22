package com.blog.service;

import com.blog.dao.UserDAO;
import com.blog.dao.mongoimpl.UserDAOMongo;
import com.blog.db.DBManager;
import com.blog.entity.User;
import com.blog.exception.InvalidUserData;
import com.blog.util.PasswordUtil;

/**
 * @author Timur Berezhnoi
 */
public class SignUpService {

	private UserDAO userDAO = new UserDAOMongo(DBManager.INSTANCE.getDataBase());

	public void signUp(User user) throws InvalidUserData {		
		validateUser(user.getEmail(), user.getName(), user.getPassword());
		
		user.setEmail(user.getEmail().toLowerCase());
		
		if(isUserExists(user.getEmail())) {
			throw new InvalidUserData("Invalid email or user with the email alredy in use!");
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
			throw new InvalidUserData("Invalid user email.");
		}

		if(name == null || name.length() > 20 || !name.matches(userNamePattern)) {
			throw new InvalidUserData("Invalid user name.");
		}
		
		if (password == null || !password.matches(passwordPattern)) {
			throw new InvalidUserData("Invalid user password.");
        }
	}
}
package com.blog.dao;

import com.blog.entity.User;

/**
 * @author Timur Berezhnoi
 */
public interface UserDAO {
	
	boolean addUser(User user);
}
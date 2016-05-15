package com.blog.service;

import org.mindrot.jbcrypt.BCrypt;

class PasswordHasher {

    static String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	static boolean verifyPassword(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
}
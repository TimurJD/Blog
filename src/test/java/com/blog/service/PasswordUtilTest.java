package com.blog.service;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Timur Berezhnoi
 */
public class PasswordUtilTest {
	
	@Test
	public void passwordShouldMatch() {
		String expected = "WelcomeToTheJungle_123";
		String hashed = PasswordHasher.hashPassword(expected);
		assertTrue(PasswordHasher.verifyPassword(expected, hashed));
	}
}
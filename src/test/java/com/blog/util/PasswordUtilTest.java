package com.blog.util;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * @author Timur Berezhnoi
 */
public class PasswordUtilTest {
	
	@Test
	public void passwordShouldMatch() {
		String expected = "WelcomeToTheJungle_123";
		String hashed = PasswordUtil.hashPassword(expected);
		assertTrue(PasswordUtil.verifyPassword(expected, hashed));
	}
}
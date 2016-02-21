package com.blog.exception;

/**
 * @author Timur Berezhnoi
 */
public class InvalidUserData extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidUserData(String mesaage) {
		super(mesaage);
	}
}

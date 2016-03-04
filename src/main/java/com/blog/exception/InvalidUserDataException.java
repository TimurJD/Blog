package com.blog.exception;

/**
 * @author Timur Berezhnoi
 */
public class InvalidUserDataException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidUserDataException(String message) {
		super(message);
	}
}

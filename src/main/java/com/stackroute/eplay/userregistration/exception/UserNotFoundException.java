package com.stackroute.eplay.userregistration.exception;

/*
 * This exception if user is not found.
 */

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
}

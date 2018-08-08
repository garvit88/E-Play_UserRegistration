package com.stackroute.eplay.userregistration.exception;

/*
 * This exception is used when the user already exists
 */

public class UserAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}

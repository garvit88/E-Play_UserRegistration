package com.stackroute.eplay.userregistration.service;

import java.util.Optional;

import com.stackroute.eplay.userregistration.domain.User;
import com.stackroute.eplay.userregistration.exception.UserAlreadyExistsException;
import com.stackroute.eplay.userregistration.exception.UserNotFoundException;

public interface UserService {

	public User saveUser(User user) throws UserAlreadyExistsException;

	public Iterable<User> getAllUsers();

	public Optional<User> getUserByUsername(String username) throws UserNotFoundException;
	
	public User updateUser(User user, String username);

	public boolean deleteUser(String username);

}

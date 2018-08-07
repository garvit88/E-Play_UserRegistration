package com.stackroute.eplay.userregistration.service;

import java.util.Optional;

import com.stackroute.eplay.userregistration.domain.User;
import com.stackroute.eplay.userregistration.exception.UserAlreadyExistsException;

public interface UserService {

	public User saveUser(User user) throws UserAlreadyExistsException;

	public Iterable<User> getAllUsers();

	public Optional<User> getUserByUsername(String username);
	
	public User updateUser(User user, String username);

	public boolean deleteUser(String username);

	// public List<Movie> getMovieByTitle(String title);

	//public void userExists

}

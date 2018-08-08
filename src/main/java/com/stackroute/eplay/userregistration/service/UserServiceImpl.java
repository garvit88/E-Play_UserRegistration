package com.stackroute.eplay.userregistration.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.userregistration.domain.User;
import com.stackroute.eplay.userregistration.exception.UserAlreadyExistsException;
import com.stackroute.eplay.userregistration.exception.UserNotFoundException;
import com.stackroute.eplay.userregistration.repository.UserRepository;

/*
 * It is a service class for user registration.
 */

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	/*
	 * saveUser() method is used to save user into database. It first get all the users for our existing 
	 * database and check if the incoming user details already exists. If the username already exists then it 
	 * throws UsernameAlreadyExistsException and if the user is not present then it save its details.
	 */

	@Override
	public User saveUser(User user) throws UserAlreadyExistsException {
		Iterable<User> users = getAllUsers();
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User alreadyUser = iterator.next();
			if (user.getUsername().equals(alreadyUser.getUsername())) {
				throw new UserAlreadyExistsException("User already exists");
			}
		}

		return userRepository.save(user);
	}
	
	/*
	 * getAllUsers() is used to get details of all the users already registered.
	 */

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	/*
	 * getUserByUsername() method is used to get detail of the specific user of which username is provided.
	 * For this first get all the users already registered and check if the user is there or not. If the 
	 * user is not there then it throws the UserNotFoundException and if user is there then it return the user
	 * for which username is provided. 
	 */
	
	@Override
	public Optional<User> getUserByUsername(String username) throws UserNotFoundException {
		boolean userExists = false;
		Iterable<User> users = getAllUsers();
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User alreadyUser = iterator.next();
			if (username.equals(alreadyUser.getUsername())) {
				userExists = true;
			}
		}

		if (!userExists) {
			throw new UserNotFoundException("User Not Found");
		}

		return userRepository.findById(username);
	}
	
	/*
	 * updateUser() method is used to update the existing user.
	 */

	@Override
	public User updateUser(User user, String username) {
		user.setUsername(username);
		return userRepository.save(user);
	}

	/*
	 *	deleteUser() method is use to delete user for which username is provided.
	 */
	
	@Override
	public boolean deleteUser(String username) {
		try {
			userRepository.deleteById(username);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

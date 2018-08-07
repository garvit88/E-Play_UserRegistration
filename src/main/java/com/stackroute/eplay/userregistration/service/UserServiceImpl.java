package com.stackroute.eplay.userregistration.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.eplay.userregistration.domain.User;
import com.stackroute.eplay.userregistration.exception.UserAlreadyExistsException;
import com.stackroute.eplay.userregistration.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

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

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		return userRepository.findById(username);
	}

	@Override
	public User updateUser(User user, String username) {
		user.setUsername(username);
		return userRepository.save(user);
	}

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

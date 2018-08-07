package com.stackroute.eplay.userregistration.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.eplay.userregistration.domain.User;
import com.stackroute.eplay.userregistration.exception.UserAlreadyExistsException;
import com.stackroute.eplay.userregistration.service.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	//@Value("${spring.data.mongodb.uri}")
	//private String mongo;

	@PostMapping("/user")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		try {
			return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>("User Already Exists!", HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<Iterable<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/user/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
		return new ResponseEntity<Optional<User>>(userService.getUserByUsername(username), HttpStatus.OK);
	}

	@DeleteMapping("/user/{username}")
	public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
		userService.deleteUser(username);
		return new ResponseEntity<String>("User Deleted", HttpStatus.OK);
	}

	@PutMapping("/user/{username}")
	public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(user, username), HttpStatus.OK);
	}

}

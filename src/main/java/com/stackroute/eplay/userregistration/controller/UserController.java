package com.stackroute.eplay.userregistration.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.stackroute.eplay.userregistration.exception.UserNotFoundException;
import com.stackroute.eplay.userregistration.service.UserService;

/*
 * It is a controller class for User Registration
 */

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/*
	 * saveUser() method is used to save user in database. Rest end point for this method will be "api/v1/user"
	 * If the user is successfully saved then it will the HTPP status code 201 that is Created and if the 
	 * user already registered then it will return the HTTP status code of 409 that is Conflict.
	 */
	
	@PostMapping("/user")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		try {
			return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>("User Already Exists!", HttpStatus.CONFLICT);
		}
	}
	
	/*
	 * getAllUsers() method is used to get all the users registered. Rest end point for this method will be
	 * "/api/v1/users". If it is successfully return all the users then it will return HTTP status code of 200
	 * that us OK.
	 */

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<Iterable<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	/*
	 * getUserByUsername() method is used to get details of a specific user of which username is provided
	 * Rest end point for this method will be "/api/v1/user/username". If the Get request is successful then 
	 * it will return the HTTP status code 0f 200 that is OK.
	 */

	@GetMapping("/user/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
		try {
			return new ResponseEntity<Optional<User>>(userService.getUserByUsername(username), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("User Not Found", HttpStatus.UNAUTHORIZED);
		}
	}
	
	/*
	 * deleteUserByUsername() method is used to delete specific user of which username is provided
	 * Rest end point for this method will be "/api/v1/user/username". If the Delete request is successful then 
	 * it will return the HTTP status code 0f 200 that is OK.
	 */

	@DeleteMapping("/user/{username}")
	public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
		userService.deleteUser(username);
		return new ResponseEntity<String>("User Deleted", HttpStatus.OK);
	}

	/*
	 * updateUser() method is used to update details of a specific user of which username is provided
	 * Rest end point for this method will be "/api/v1/user/username". If the put request is successful then 
	 * it will return the HTTP status code 0f 200 that is OK.
	 */
	
	@PutMapping("/user/{username}")
	public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(user, username), HttpStatus.OK);
	}

}

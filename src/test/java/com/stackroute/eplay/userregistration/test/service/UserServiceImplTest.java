package com.stackroute.eplay.userregistration.test.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.eplay.userregistration.domain.User;
import com.stackroute.eplay.userregistration.repository.UserRepository;
import com.stackroute.eplay.userregistration.service.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

	@MockBean
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	private User user;
	ArrayList<User> users;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		user = new User("garvit88", "pass", "Garvit Garg", "12345", "email@gmail.com", "male", "blr");
		users = new ArrayList<User>();
	}

	@After
	public void tearDown() throws Exception {

	}

	/*
	 * Testing saveuser() method
	 */
	
	@Test
	public void testSaveUser() throws Exception {
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userServiceImpl.saveUser(user));
	}

	/*
	 *	testing getAllUsers() method 
	 */
	
	@Test
	public void testGetAllUsers() {

		users.add(user);
		user = new User("garvit", "pass", "Garvit Garg", "12345", "email@gmail.com", "male", "blr");
		users.add(user);
		user = new User("garvit8", "pass", "Garvit Garg", "12345", "email@gmail.com", "male", "blr");
		users.add(user);
		when(userRepository.findAll()).thenReturn(users);
		Iterable<User> allUsers = userServiceImpl.getAllUsers();
		assertEquals(users, allUsers);
		ArrayList<User> actualUsers = new ArrayList<User>();
		for (User s : allUsers) {
			actualUsers.add(s);
		}
		assertEquals(3, actualUsers.size());
		assertEquals("garvit8", actualUsers.get(2).getUsername());
	}
	
	/*
	 *	testing getGetByUsername() method 
	 */

	@Test
	public void testGetUserByUsername() throws Exception {
		Optional<User> userOp = Optional.of(user);
		when(userRepository.findById("garvit88")).thenReturn(userOp);
		assertEquals(userOp, userServiceImpl.getUserByUsername("garvit88"));
	}
	
	/*
	 *	testing getUpdateUser() method 
	 */

	@Test
	public void testUpdateUser() throws Exception {
		when(userRepository.save(user)).thenReturn(user);
		User actual = userServiceImpl.updateUser(user, user.getUsername());
		assertEquals(user, actual);
	}
	
	/*
	 *	testing getDeleteUser() method 
	 */
	

	@Test
	public void testDeleteUser() throws Exception {
		doNothing().when(userRepository).deleteById(user.getUsername());
		boolean status = userServiceImpl.deleteUser(user.getUsername());
		assertEquals(true, status);
	}

}

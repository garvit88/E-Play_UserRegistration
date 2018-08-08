package com.stackroute.eplay.userregistration.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.eplay.userregistration.controller.UserController;
import com.stackroute.eplay.userregistration.domain.User;
import com.stackroute.eplay.userregistration.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService userService;

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
	 * Testing save user controller method
	 */

	@Test
	public void testSaveUser() throws Exception {
		when(userService.saveUser(user)).thenReturn(user);
		mockMvc.perform(post("/api/v1/user").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isCreated());
	}
	
	/*
	 * Testing get all user controller method
	 */

	@Test
	public void testGetAllUsers() throws Exception {
		users.add(user);
		user = new User("garvit", "pass", "Garvit Garg", "12345", "email@gmail.com", "male", "blr");
		users.add(user);
		user = new User("garvit8", "pass", "Garvit Garg", "12345", "email@gmail.com", "male", "blr");
		users.add(user);
		when(userService.getAllUsers()).thenReturn(users);
		mockMvc.perform(get("/api/v1/users")).andExpect(status().isOk());
	}
	
	/*
	 * Testing update user controller method
	 */

	@Test
	public void testUpdateUser() throws Exception {
		user.setCity("Delhi");
		when(userService.updateUser(any(), eq("garvit88"))).thenReturn(user);
		mockMvc.perform(put("/api/v1/user/{username}", user.getUsername()).contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user))).andExpect(status().isOk());
	}
	
	/*
	 * Testing get user by username controller method
	 */

	@Test
	public void testGetUserByUsername() throws Exception {
		Optional<User> userOp = Optional.of(user);
		when(userService.getUserByUsername(user.getUsername())).thenReturn(userOp);
		mockMvc.perform(get("/api/v1/user/{username}", user.getUsername())).andExpect(status().isOk());
	}
	
	/*
	 * Testing delete user controller method
	 */
	
	@Test
	public void testDeleteUser() throws Exception {
		when(userService.deleteUser(user.getUsername())).thenReturn(true);
		mockMvc.perform(delete("/api/v1/user/{username}", user.getUsername())).andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

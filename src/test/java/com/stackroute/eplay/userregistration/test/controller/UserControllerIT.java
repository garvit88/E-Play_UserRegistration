package com.stackroute.eplay.userregistration.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.eplay.userregistration.UserRegistrationApplication;
import com.stackroute.eplay.userregistration.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRegistrationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

	@LocalServerPort
	private int port;

	User user;

	private TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	HttpEntity<User> entity;

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Before
	public void setUp() throws Exception {

		user = new User("garvit88", "pass", "Garvit Garg", "12345", "email@gmail.com", "male", "blr");
		entity = new HttpEntity<User>(user, headers);

	}

	@After
	public void tearDown() throws Exception {
		restTemplate.exchange(createURLWithPort("/api/v1/user/garvit88"), HttpMethod.DELETE, entity, String.class);

	}

	/*
	 * Testing save user method
	 */
	
	@Test
	public void testSaveUser() throws Exception {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/user"), HttpMethod.POST,
				entity, String.class);

		assertNotNull(response);
		String actual = response.getBody();
		assertNotNull(actual);
		assertEquals(201, response.getStatusCodeValue());

	}
	
	/*
	 * Testing delete user method
	 */
	
	@Test
	public void testDeleteUser() throws Exception {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/user/garvit88"), HttpMethod.DELETE,
				entity, String.class);

		assertNotNull(response);
		String actual = response.getBody();
		assertNotNull(actual);
		assertEquals(200, response.getStatusCodeValue());

	}
	
	/*
	 * Testing update user method
	 */
	
	@Test
	public void testUpdateUser() throws Exception {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/user/garvit88"), HttpMethod.PUT,
				entity, String.class);

		assertNotNull(response);
		String actual = response.getBody();
		assertNotNull(actual);
		assertEquals(200, response.getStatusCodeValue());

	}
	
	/*
	 * Testing get user method
	 */
	
	@Test
	public void testGetUser() throws Exception {
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/users"), HttpMethod.GET,
				entity, String.class);
		System.out.println(response);
		assertNotNull(response);
		String actual = response.getBody();
		assertNotNull(actual);
		assertEquals(200, response.getStatusCodeValue());

	}
}

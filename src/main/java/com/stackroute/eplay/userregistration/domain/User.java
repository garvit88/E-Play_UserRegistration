package com.stackroute.eplay.userregistration.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * This is a model class for the User Registration
 */

@Document 
public class User {
	@Id
	private String username;
	private String password;
	private String fullName;
	private String contactNo;
	private String email;
	private String gender;
	private String city;

	public User() {}
	
	public User(String username, String password, String fullName, String contactNo, String email, String gender,
			String city) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.contactNo = contactNo;
		this.email = email;
		this.gender = gender;
		this.city = city;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", fullName=" + fullName + ", contactNo="
				+ contactNo + ", email=" + email + ", gender=" + gender + ", city=" + city + "]";
	}

}

package com.luv2code.springdemo.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.luv2code.springdemo.validation.FieldMatch;
import com.luv2code.springdemo.validation.ValidEmail;

@FieldMatch.List({
	@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")})
public class CrmUser {

	@NotNull(message="reqired field")
	@Size(min=1, message="reqired field")
	private String userName;

	@NotNull(message="reqired field")
	@Size(min=1, message="reqired field")
	private String password;
	
	@NotNull(message="reqired field")
	@Size(min=1, message="reqired field")
	private String matchingPassword;
	
	@NotNull(message="reqired field")
	@Size(min=1, message="reqired field")
	private String firstName;
	
	@NotNull(message="reqired field")
	@Size(min=1, message="reqired field")
	private String lastName;
	
	@ValidEmail
	@NotNull(message="reqired field")
	@Size(min=1, message="reqired field")
	private String email;

	public CrmUser() {
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

package com.example.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailDetails {

	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String query;
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getQuery() {
		return query;
	}
	
	
	
}

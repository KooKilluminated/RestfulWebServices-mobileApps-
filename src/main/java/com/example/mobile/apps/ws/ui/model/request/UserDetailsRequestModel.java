package com.example.mobile.apps.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {
@NotNull(message="First name cannot be null") //for string value, not be empty
@Size(min=2,message="min 2 characters")
private String firstName;

@NotNull(message="Last name cannot be null")
@Size(min=2,message="min 2 characters")
private String lastName;

@NotNull(message="Email cannot be null")
@Email//specify this is email address
private String email;

@NotNull(message="Password cannot be null")
@Size(min=8,max=16,message="password must be equal to or greater than 8 characters and les than 12 characters")
//define password length(say 8 char)
private String password;

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
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
}

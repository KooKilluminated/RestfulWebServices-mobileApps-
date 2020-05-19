package com.example.mobile.apps.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mobile.apps.ws.exceptions.UserCustomException;
import com.example.mobile.apps.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.mobile.apps.ws.ui.model.request.UserDetailsRequestModel;
import com.example.mobile.apps.ws.ui.model.response.UserRest;
import com.example.mobile.apps.ws.userservice.UserService;
import com.example.mobile.apps.ws.userservice.impl.UserServiceImpl;

@Controller
@RestController
@RequestMapping("/users") // http://localhost:8080/users

public class UserController {

	Map<String, UserRest> users;

	// autoWire implementation to the class as below
	@Autowired // spring framework creates instance of userService Impl and inject it to the
				// userController object
	UserService userService;

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "get users was called with page= " + page + " & limit " + limit + " & sort " + sort;
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

		/*
		 * UserRest returnValue=new UserRest(); returnValue.setEmail("sg@gmail.com");
		 * returnValue.setFirstName("shreya"); //remove hardcoded values to return
		 * values from users map created via put returnValue.setLastName("gurappa");
		 * returnValue.setPassword("123345678");
		 */

		// exception handling

		/*
		 * String firstName = null; int firstNameLength = firstName.length();
		 */

		// throw custom exception

		if (true)
			throw new UserCustomException("A user custom exception is thrown");

		if (users.containsKey(userId)) {

			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

		/*
		 * UserRest returnValue=new UserRest();
		 * returnValue.setEmail(userDetails.getEmail());
		 * returnValue.setFirstName(userDetails.getFirstName());
		 * returnValue.setLastName(userDetails.getLastName());
		 * returnValue.setPassword(userDetails.getPassword());
		 * 
		 * String userId=UUID.randomUUID().toString();//generate random uuid
		 * returnValue.setUserId(userId);
		 * 
		 * if(users == null)users = new HashMap<>(); users.put(userId, returnValue);
		 */ // dependency injection
		UserRest returnValue = userService.createUser(userDetails);
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}

	@PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@PathVariable String userId,
			@Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());

		users.put(userId, storedUserDetails);// update map
		return storedUserDetails;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		users.remove(id);
		return ResponseEntity.noContent().build(); // build the response
	}

}

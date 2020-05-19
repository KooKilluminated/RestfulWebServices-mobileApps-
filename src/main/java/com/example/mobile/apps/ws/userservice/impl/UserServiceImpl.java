package com.example.mobile.apps.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mobile.apps.ws.shared.Utils;
import com.example.mobile.apps.ws.ui.model.request.UserDetailsRequestModel;
import com.example.mobile.apps.ws.ui.model.response.UserRest;
import com.example.mobile.apps.ws.userservice.UserService;

//an impl of interface of UserService
@Service 
public class UserServiceImpl implements UserService {
	
	Map<String, UserRest>users;
	Utils utils;
	
	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils) {//constructor based dependency injection
		
		this.utils=utils;
		
	}

	public UserRest createUser(UserDetailsRequestModel userDetails) {
		
		UserRest returnValue=new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		returnValue.setPassword(userDetails.getPassword());
		
		String userId=utils.generateUserId(); //UUID.randomUUID().toString(); replaced by utils class i.e injected utility class to impl
		//generate random uuid
		returnValue.setUserId(userId);
		
		if(users == null)users = new HashMap<>();
		users.put(userId, returnValue);
		
		return returnValue;
	}
}


package com.example.mobile.apps.ws.userservice;

import javax.validation.Valid;

import com.example.mobile.apps.ws.ui.model.request.UserDetailsRequestModel;
import com.example.mobile.apps.ws.ui.model.response.UserRest;

public interface UserService {
	//contains business logic with user details
	//UserRest createUser(UserDetailsRequestModel userDetails);

	UserRest createUser(@Valid UserDetailsRequestModel userDetails);

}

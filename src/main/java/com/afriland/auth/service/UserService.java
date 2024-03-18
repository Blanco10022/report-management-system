package com.afriland.auth.service;

import com.afriland.auth.model.UserDtls;

public interface UserService {
	
	public UserDtls createUser(UserDtls user);
	
	public boolean checkEmail(String email);
	
	
	
	

}

package com.afriland.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.afriland.auth.model.UserDtls;
import com.afriland.auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncode;
    
    

    @Override
    public UserDtls createUser(UserDtls user) {
    	
    	user.setPassword(passwordEncode.encode(user.getPassword()));
    	user.setPoste("ROLE_USER");
    	
        return userRepo.save(user);
    }

	@Override
	public boolean checkEmail(String email) {
		
		return userRepo.existsByEmail(email);
	}

	

}

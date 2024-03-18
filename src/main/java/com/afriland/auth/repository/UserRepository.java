package com.afriland.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afriland.auth.model.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {
	
	public boolean existsByEmail(String email);
	
	public UserDtls findByEmail(String email);
	
	
	
}

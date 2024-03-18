package com.afriland.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.afriland.auth.model.UserDtls;

public class CustomUserDetails  implements UserDetails{
	
	private UserDtls user;
	
	
	public CustomUserDetails(UserDtls user) {
		super();
		this.user = user;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    String role = user.getPoste().equalsIgnoreCase("chef") ? "ROLE_ADMIN" : "ROLE_USER";
	    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
	    return Collections.singletonList(simpleGrantedAuthority);
	}



	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return  true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return  true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return  true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

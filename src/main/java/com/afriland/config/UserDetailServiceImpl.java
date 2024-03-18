/*
 * package com.afriland.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.afriland.auth.model.UserDtls; import
 * com.afriland.auth.repository.UserRepository;
 * 
 * @Service public class UserDetailServiceImpl implements UserDetailsService {
 * 
 * @Autowired private UserRepository userRepo;
 * 
 * @Override public UserDetails loadUserByUsername(String email) throws
 * UsernameNotFoundException {
 * 
 * UserDtls user = userRepo.findByEmail(email);
 * 
 * if (user != null) { return new CustomUserDetails(user); }
 * 
 * throw new UsernameNotFoundException("utilisateur introuvable"); }
 * 
 * }
 * 
 * 
 * 
 * 
 */


package com.afriland.config;

import com.afriland.auth.model.UserDtls;
import com.afriland.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired 
	private UserRepository userRepo;

	
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDtls user = userRepo.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable");
        }

        // Create a UserDetails object with username, password, and authorities
        UserDetails userDetails = User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getPoste())))
                .build();

        return userDetails;
    }
    
}

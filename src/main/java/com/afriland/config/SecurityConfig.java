package com.afriland.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableWebSecurity
public class SecurityConfig {
	
	 @Autowired
	    private UserDetailServiceImpl userDetailService;

	
	
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailServiceImpl();
	}
	

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * @Bean public DaoAuthenticationProvider getDaoAuthProvider() {
	 * DaoAuthenticationProvider daoAuthenticationProvider = new
	 * DaoAuthenticationProvider();
	 * daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
	 * daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
	 * 
	 * return daoAuthenticationProvider; }
	 */
	@Bean
    public DaoAuthenticationProvider getDaoAuthProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }
	
	
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getDaoAuthProvider());
    }

	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	            .requestMatchers("/user/list").hasAnyRole("USER", "ADMIN") // Allow access to the list page for users with "USER" or "ADMIN" roles
	            .requestMatchers("/admin/**").hasRole("ADMIN") // Allow access to any page under /admin for users with the "ADMIN" role
	            .anyRequest().permitAll() // Allow access to other pages
	            .and()
	        .formLogin()
	            .loginPage("/login")
	            .defaultSuccessUrl("/user/list")
	            .permitAll()
	            .and()
	        .logout()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login")
	            .permitAll()
	            .and()
	        .sessionManagement()
	            .invalidSessionUrl("/login") // Redirect to login page for invalid sessions
	            .maximumSessions(1) // Allow only one session per user
	            .maxSessionsPreventsLogin(false) // Allow new login when maximum sessions reached
	            .expiredUrl("/login"); // Redirect to login page when session expires
	}


	
	
	
	
	
	/*@Bean
	public UserDetailsService  userDtailsServices() {
		
		 UserDetails normalUser = User
				.withUsername("afriland")
				.password(passwordEncoder().encode("password"))
				.roles("NORMAL")
				.build();
		
		UserDetails adminUser = User
				.withUsername("loic")
				.password("loic")
				.roles("ADMIN")
				.build();
		

	     return new InMemoryUserDetailsManager();
	
	}
	*/
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
	     httpSecurity.csrf().disable()
	     .authorizeHttpRequests()
	     .requestMatchers("/admin/**")
	     .hasRole("ADMIN")
	     .requestMatchers("/user/**")
	     .hasRole("USER")
	     .requestMatchers("/**")
	     .permitAll()
	     .and()
	     .formLogin()
	     .loginPage("/login")//it was signin i changed to login
	     .loginProcessingUrl("/login")  
	     .defaultSuccessUrl("/user/");
	     
	  
	     
	     
	   
	     
	     return httpSecurity.build();
		
	}
//	 
   }


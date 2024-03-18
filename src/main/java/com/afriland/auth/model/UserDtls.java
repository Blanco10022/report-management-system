package com.afriland.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserDtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	private String fullname;
	private String email;
	private String password;
	private String poste;
	private String role;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	
	
	
	public String getPoste() {
		return poste;
	}
	
	public void setPoste(String poste) {
	    this.poste = poste;

	    // Check if the poste is "employer" and assign the role "USER"
	    if ("employer".equalsIgnoreCase(poste)) {
	        this.setRole("USER");
	    } else if ("chef".equalsIgnoreCase(poste)) {
	        this.setRole("ADMIN");
	    }
	}

	@Override
	public String toString() {
		return "UserDtls [id=" + id + ", fullname=" + fullname + ", email=" + email + ", password=" + password
				+ ", poste=" + poste + "]";
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}

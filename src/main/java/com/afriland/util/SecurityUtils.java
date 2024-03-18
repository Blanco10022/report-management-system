package com.afriland.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
    public static boolean hasRole(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            return userDetails.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals(role));
        }

        return false;
    }
}

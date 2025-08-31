package com.TradeShift.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.TradeShift.Repository.UserRepository;
import com.TradeShift.Entity.User;

@Service
public class JpaUserDetailsService implements UserDetailsService {
  
    @Autowired
    private UserRepository repo;

    public JpaUserDetailsService(UserRepository repo) { 
        this.repo = repo; 
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // 1️⃣ Get user from User Service DB
        User u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        // 2️⃣ Get role name from Roles entity
        String roleName = u.getRole() != null ? u.getRole().getRoleName(): "USER";
     
        // 👇 Convert Role entity → Spring Security Authority
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + u.getRole().getRoleName());

        // 3️⃣ Build Spring Security user with role
        return new org.springframework.security.core.userdetails.User(
        		u.getUsername(),
        		u.getPassword(),
        		Collections.singleton(authority)
        		)
                .withUsername(u.getUsername())
                .password(u.getPassword())
                .authorities(roleName.split(",")) // split if multiple roles
                .build();
    }
}

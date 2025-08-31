package com.TradeShift.Controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TradeShift.Entity.Roles;
import com.TradeShift.Entity.User;
import com.TradeShift.Repository.OrdersRepository;
import com.TradeShift.Repository.RolesRepository;
import com.TradeShift.Repository.UserRepository;
import com.TradeShift.dto.RegisterRequest;

import jakarta.transaction.Transactional;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public AuthService(UserRepository repo, PasswordEncoder encoder) {
	 this.repo = repo;
	 this.encoder = encoder;
	}
	
	public void register(RegisterRequest req) {
	 if (repo.existsByUsername(req.getUsername())) throw new RuntimeException("Username already taken");
	 if (repo.existsByEmail(req.getEmail())) throw new RuntimeException("Email already registered");
	
	 User u = new User();
	 u.setUsername(req.getUsername());
	 u.setEmail(req.getEmail());
	 u.setCreatedAt(new Timestamp(System.currentTimeMillis()));
	 u.setPassword(encoder.encode(req.getPassword()));
	 
	// Fetch role from DB
	 Roles roles = rolesRepository.findByRoleName("USER")
			 .orElseThrow(() -> new RuntimeException("Default role USER not found"));
	 
	 u.setRole(roles);
	 repo.save(u);
	 
	}
	
    @Transactional
    public void deleteAccount(String username) {
        // find user
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // delete all orders of the user
        ordersRepository.deleteByPlacedBy_Id(user.getId());

        // delete user itself
        repo.delete(user);
    }
}

package com.TradeShift.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TradeShift.Entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	User findByEmail(String email);
	
}

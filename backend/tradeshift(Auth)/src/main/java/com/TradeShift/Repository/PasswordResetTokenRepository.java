package com.TradeShift.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TradeShift.Entity.PasswordResetToken;

import jakarta.transaction.Transactional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	Optional<PasswordResetToken> findByEmailAndOtp(String email, String otp);
	
	@Transactional
	void deleteByEmail(String email);
}

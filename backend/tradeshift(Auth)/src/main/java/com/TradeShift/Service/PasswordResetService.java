package com.TradeShift.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TradeShift.Entity.PasswordResetToken;
import com.TradeShift.Entity.User;
import com.TradeShift.Repository.PasswordResetTokenRepository;
import com.TradeShift.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class PasswordResetService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordResetTokenRepository tokenRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
    // Step 1: Generate OTP and send via email
	@Transactional
    public void generateOtp(String email) {
        User user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new RuntimeException("Email not found"));

        // create OTP
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

        // save OTP with 5 minutes expiry
        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);
        token.setOtp(otp);
        token.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        tokenRepository.deleteByEmail(email); // remove old OTPs
        tokenRepository.save(token);

        // send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP is: " + otp + " (valid for 5 minutes)");
        mailSender.send(message);
    }

    // Step 2: Verify OTP and reset password
	@Transactional
    public void resetPassword(String email, String otp, String newPassword) {
        PasswordResetToken token = tokenRepository.findByEmailAndOtp(email, otp)
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        User user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.deleteByEmail(email); // OTP used, delete it
    }
}

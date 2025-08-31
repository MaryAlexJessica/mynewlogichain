package com.TradeShift.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.TradeShift.Entity.Roles;
import com.TradeShift.Entity.User;
import com.TradeShift.Jwt.JwtUtil;
import com.TradeShift.Repository.RolesRepository;
import com.TradeShift.Repository.UserRepository;
import com.TradeShift.Service.PasswordResetService;
import com.TradeShift.dto.AuthResponse;
import com.TradeShift.dto.LoginRequest;
import com.TradeShift.dto.RegisterRequest;
import com.TradeShift.dto.UpdateUserRequest;
import com.TradeShift.dto.UserResponse;
import com.TradeShift.dto.Admin.AdminChangePasswordRequest;
import com.TradeShift.dto.All.PasswordResetRequest;
import com.TradeShift.dto.All.ResetPasswordWithOtpRequest;

@RestController
@CrossOrigin(origins = "http://localhost:5173") 
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolesRepository rolesRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PasswordResetService passwordResetService;
	
	public AuthController(AuthenticationManager am, JwtUtil jwtUtil, AuthService authService) {
	 this.authManager = am;
	 this.jwtUtil = jwtUtil;
	 this.authService = authService;
	}
	
	// register api
	@PostMapping("/register")
	public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest req) {
	 authService.register(req);
	 return ResponseEntity.status(201).body("Registered");
	}
	
	// login api
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Validated @RequestBody LoginRequest req) {
	 Authentication auth = authManager.authenticate(
	     new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
	 );
	 UserDetails user = (UserDetails) auth.getPrincipal();
	 String token = jwtUtil.generateToken(user);
	 return ResponseEntity.ok(new AuthResponse(token));
	}
	
	// user data after login
	@GetMapping("/home")
	public ResponseEntity<?> me(Authentication auth) {
		
		// get username of logged-in user
	    String username = auth.getName();
	    
	    // fetch user from DB
	    User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	    
	    // return user details
	    return ResponseEntity.ok(user);
	}
	
	// update user, admin, staff data api
	@PutMapping("/home/update")
	public ResponseEntity<?> up(Authentication auth, @RequestBody UpdateUserRequest userRequest){

		// 1. Get logged in username
	    String username = auth.getName();
	    
	    // 2. Find user from DB
	    User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	    
	    // 3. Update allowed fields
	    user.setFullName(userRequest.getFullName());
	    user.setEmail(userRequest.getEmail());
	    
	    // 4. Fetch Role entity
	    if (userRequest.getRole() != null) {
	        Roles role = rolesRepository.findById(userRequest.getRole())
	                .orElseThrow(() -> new RuntimeException("Role not found"));
	        user.setRole(role);
	    }
	    
	    // 5. Save back to DB
	    User updatedUser = userRepository.save(user);
	    
	    // 6. Return response DTO (exclude password)
	    UserResponse response = new UserResponse(
	            updatedUser.getUsername(),
	            updatedUser.getFullName(),
	            updatedUser.getEmail(),
	            updatedUser.getRole().getRoleName()
	    );
	    
	    return ResponseEntity.ok(response);
	}

	// change password api for the admin purpose without using mail
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/admin/change-password")
	public ResponseEntity<?> adminChangePassword(Authentication auth,
	                                             @RequestBody AdminChangePasswordRequest prequest) {
	    // 1. Get logged-in user (the admin making the request)
	    String loggedInUsername = auth.getName();
	    User loggedInUser = userRepository.findByUsername(loggedInUsername)
	            .orElseThrow(() -> new RuntimeException("Logged-in user not found"));

	    // 2. Check if logged-in user has ADMIN role
	    if (!"ADMIN".equalsIgnoreCase(loggedInUser.getRole().getRoleName())) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                .body("Access denied: Only admins can reset passwords.");
	    }

	    // 3. Find target user
	    User targetUser = userRepository.findByUsername(prequest.getTargetUsername())
	            .orElseThrow(() -> new RuntimeException("Target user not found"));

	    // 4. Encode and set new password
	    targetUser.setPassword(passwordEncoder.encode(prequest.getNewPassword()));
	    userRepository.save(targetUser);

	    return ResponseEntity.ok("Password updated successfully for user: " + targetUser.getUsername());
	}


	
	// 1. password reset api using mail otp based
	@PostMapping("home/request-reset")
	public ResponseEntity<?> requestReset(@RequestBody PasswordResetRequest passwordResetRequest){
		passwordResetService.generateOtp(passwordResetRequest.getEmail());
		return ResponseEntity.ok("OTP sent to your email");
	}

	// 1. password get api using mail otp based
	@PostMapping("home/request-password")
	public ResponseEntity<?> requestPassword(@RequestBody ResetPasswordWithOtpRequest otpRequest){
		passwordResetService.resetPassword(otpRequest.getEmail(), otpRequest.getOtp(), otpRequest.getNewPassword());
		return ResponseEntity.ok("Password updated successfully");
	}
	
}










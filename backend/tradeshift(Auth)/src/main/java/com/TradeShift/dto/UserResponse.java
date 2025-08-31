package com.TradeShift.dto;

public class UserResponse {
	
	private String username;
    private String fullName;
    private String email;
    private String role;
    
    
	public UserResponse(String username, String fullName, String email, String role) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.role = role;
	}

	// getter & setter
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	// toString
	
	@Override
	public String toString() {
		return "UserResponse [username=" + username + ", fullName=" + fullName + ", email=" + email + ", role=" + role
				+ "]";
	}
    
}

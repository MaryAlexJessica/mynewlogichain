package com.TradeShift.dto;

public class UpdateUserRequest {

	private String username;
	
	private String password;
	
	private String email;
	
	private String fullName;
	
	private Integer role;

	// getter And setter
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	// toString 
	
	@Override
	public String toString() {
		return "UpdateUserRequest [username=" + username + ", password=" + password + ", email=" + email + ", fullName="
				+ fullName + ", role=" + role + "]";
	}
}

package com.TradeShift.dto;

public class AuthResponse {
	
	private String token;
	private String refreshToken;
	
	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthResponse(String token) { 
		this.token = token; 
	}
	
	public String getToken() { 
		return token; 
	}
}


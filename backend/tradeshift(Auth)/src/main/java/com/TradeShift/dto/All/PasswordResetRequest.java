package com.TradeShift.dto.All;

public class PasswordResetRequest {
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "PasswordResetRequest [email=" + email + "]";
	}
}

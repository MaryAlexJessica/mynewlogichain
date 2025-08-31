package com.TradeShift.dto.All;
// for user and staff only
public class ResetPasswordWithOtpRequest {
    private String email;
    private String otp;
    private String newPassword;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "ResetPasswordWithOtpRequest [email=" + email + ", otp=" + otp + ", newPassword=" + newPassword + "]";
	}
}

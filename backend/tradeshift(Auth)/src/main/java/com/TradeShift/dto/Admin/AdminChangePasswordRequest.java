package com.TradeShift.dto.Admin;
//for admin only
public class AdminChangePasswordRequest {

    private String targetUsername; // or Long targetUserId
    private String newPassword;
    
	@Override
	public String toString() {
		return "AdminChangePasswordRequest [targetUsername=" + targetUsername + ", newPassword=" + newPassword + "]";
	}
	
	public String getTargetUsername() {
		return targetUsername;
	}
	public void setTargetUsername(String targetUsername) {
		this.targetUsername = targetUsername;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
    
}

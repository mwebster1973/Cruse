package com.cruse.domain.system;



/**
* Parameter object encapsulating a new password request
*/
public class NewPasswordRequest {
	private String currentPassword;
	private String newPassword;
	private String verifyNewPassword;
	
	private String userId;

	public NewPasswordRequest(String newId){
		this.userId = newId;
	}
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getVerifyNewPassword() {
		return verifyNewPassword;
	}
	public void setVerifyNewPassword(String verifyNewPassword) {
		this.verifyNewPassword = verifyNewPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
	
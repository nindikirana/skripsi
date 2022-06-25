package com.nndkrnaf.acfix.admin.leveluser.model;

import com.google.gson.annotations.SerializedName;

public class UpdateAdminLevelUser {

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UpdateAdminLevelUser{" +
				"message='" + message + '\'' +
				", status=" + status +
				'}';
	}
}
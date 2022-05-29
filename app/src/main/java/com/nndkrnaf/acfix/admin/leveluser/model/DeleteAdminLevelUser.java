package com.nndkrnaf.acfix.admin.leveluser.model;

import com.google.gson.annotations.SerializedName;

public class DeleteAdminLevelUser {

	@SerializedName("id")
	private String id;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public String getId(){
		return id;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DeleteAdminLevelUser{" +
				"id='" + id + '\'' +
				", message='" + message + '\'' +
				", status=" + status +
				'}';
	}
}
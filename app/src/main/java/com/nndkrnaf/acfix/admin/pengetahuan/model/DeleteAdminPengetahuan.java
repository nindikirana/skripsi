package com.nndkrnaf.acfix.admin.pengetahuan.model;

import com.google.gson.annotations.SerializedName;

public class DeleteAdminPengetahuan{

	@SerializedName("id")
	private String id;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DeleteAdminPengetahuan{" + 
			"id = '" + id + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
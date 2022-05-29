package com.nndkrnaf.acfix.registration.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.nndkrnaf.acfix.login.model.Login;

public class Message {

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

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
				"Message{" +
						"message = '" + message + '\'' +
						",status = '" + status + '\'' +
						"}";
	}
}
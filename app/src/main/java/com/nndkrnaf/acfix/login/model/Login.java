package com.nndkrnaf.acfix.login.model;

import com.google.gson.annotations.SerializedName;

public class Login {

	@SerializedName("Id_User")
	private String idUser;

	@SerializedName("Email")
	private String email;

	@SerializedName("Username")
	private String username;

	@SerializedName("Id_Level")
	private String idLevel;

	@SerializedName("Password")
	private String password;

	@SerializedName("success")
	private int success;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public String getIdUser(){
		return idUser;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}

	public String getIdLevel(){
		return idLevel;
	}

	public String getPassword(){
		return password;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setIdLevel(String idLevel) {
		this.idLevel = idLevel;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login{" +
				"idUser='" + idUser + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				", idLevel='" + idLevel + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
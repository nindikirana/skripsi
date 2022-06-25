package com.nndkrnaf.acfix.admin.user.model;

import com.google.gson.annotations.SerializedName;

public class ListAdminUserData {

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

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setIdLevel(String idLevel){
		this.idLevel = idLevel;
	}

	public String getIdLevel(){
		return idLevel;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"id_User = '" + idUser + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			",id_Level = '" + idLevel + '\'' + 
			",password = '" + password + '\'' + 
			"}";
		}
}
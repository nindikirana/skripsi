package com.nndkrnaf.acfix.admin.leveluser.model;

import com.google.gson.annotations.SerializedName;

public class ListAdminLevelUserData {

	@SerializedName("Level")
	private String level;

	@SerializedName("Id_Level")
	private String idLevel;

	public String getLevel(){
		return level;
	}

	public String getIdLevel(){
		return idLevel;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setIdLevel(String idLevel) {
		this.idLevel = idLevel;
	}

	@Override
	public String toString() {
		return "LeverUserData{" +
				"level='" + level + '\'' +
				", idLevel='" + idLevel + '\'' +
				'}';
	}
}
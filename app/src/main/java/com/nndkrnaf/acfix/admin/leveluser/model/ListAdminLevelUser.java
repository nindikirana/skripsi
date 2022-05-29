package com.nndkrnaf.acfix.admin.leveluser.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListAdminLevelUser {

	@SerializedName("data")
	private List<ListAdminLevelUserData> data;

	@SerializedName("status")
	private boolean status;

	public List<ListAdminLevelUserData> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}

	public void setData(List<ListAdminLevelUserData> data) {
		this.data = data;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ListAdminLevelUser{" +
				"data=" + data +
				", status=" + status +
				'}';
	}
}
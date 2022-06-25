package com.nndkrnaf.acfix.admin.user.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListAdminUser {

	@SerializedName("data")
	private List<ListAdminUserData> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<ListAdminUserData> data){
		this.data = data;
	}

	public List<ListAdminUserData> getData(){
		return data;
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
			"Response{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
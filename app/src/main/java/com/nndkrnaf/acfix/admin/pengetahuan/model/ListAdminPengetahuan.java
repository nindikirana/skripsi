package com.nndkrnaf.acfix.admin.pengetahuan.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListAdminPengetahuan{

	@SerializedName("data")
	private List<ListAdminPengetahuanData> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<ListAdminPengetahuanData> data){
		this.data = data;
	}

	public List<ListAdminPengetahuanData> getData(){
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
			"ListAdminPengetahuan{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
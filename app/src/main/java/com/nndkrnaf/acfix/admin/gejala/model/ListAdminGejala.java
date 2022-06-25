package com.nndkrnaf.acfix.admin.gejala.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListAdminGejala {

	@SerializedName("data")
	private List<ListAdminGejalaData> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<ListAdminGejalaData> data){
		this.data = data;
	}

	public List<ListAdminGejalaData> getData(){
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
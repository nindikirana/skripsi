package com.nndkrnaf.acfix.admin.kerusakan.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListAdminKerusakan {

	@SerializedName("data")
	private List<ListAdminKerusakanData> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<ListAdminKerusakanData> data){
		this.data = data;
	}

	public List<ListAdminKerusakanData> getData(){
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
			"DeleteAdminGejala{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
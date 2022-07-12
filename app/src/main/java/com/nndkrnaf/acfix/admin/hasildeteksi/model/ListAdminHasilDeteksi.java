package com.nndkrnaf.acfix.admin.hasildeteksi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListAdminHasilDeteksi {

	@SerializedName("data")
	private List<ListAdminHasilDeteksiData> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<ListAdminHasilDeteksiData> data){
		this.data = data;
	}

	public List<ListAdminHasilDeteksiData> getData(){
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
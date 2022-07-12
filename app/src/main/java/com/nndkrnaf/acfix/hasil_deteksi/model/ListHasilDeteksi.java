package com.nndkrnaf.acfix.hasil_deteksi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListHasilDeteksi {

	@SerializedName("data")
	private List<ListHasilDeteksiData> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<ListHasilDeteksiData> data){
		this.data = data;
	}

	public List<ListHasilDeteksiData> getData(){
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
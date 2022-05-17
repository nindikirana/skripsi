package com.nndkrnaf.acfix.deteksi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListDeteksi {

	@SerializedName("data")
	private List<ListDeteksiData> data;

	@SerializedName("status")
	private boolean status;

	public List<ListDeteksiData> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}

	public void setData(List<ListDeteksiData> data) {
		this.data = data;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ListDeteksi{" +
				"data=" + data +
				", status=" + status +
				'}';
	}
}
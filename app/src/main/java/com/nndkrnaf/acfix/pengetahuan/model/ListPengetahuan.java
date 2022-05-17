package com.nndkrnaf.acfix.pengetahuan.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListPengetahuan {

	@SerializedName("data")
	private List<ListPengetahuanData> data;

	@SerializedName("status")
	private boolean status;

	public List<ListPengetahuanData> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}

	public void setData(List<ListPengetahuanData> data) {
		this.data = data;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ListPengetahuan{" +
				"data=" + data +
				", status=" + status +
				'}';
	}
}
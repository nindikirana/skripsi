package com.nndkrnaf.acfix.kerusakan.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListKerusakan {

	@SerializedName("data")
	private List<ListKerusakanData> data;

	@SerializedName("status")
	private boolean status;

	public List<ListKerusakanData> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}

	public void setData(List<ListKerusakanData> data) {
		this.data = data;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ListKerusakan{" +
				"data=" + data +
				", status=" + status +
				'}';
	}
}
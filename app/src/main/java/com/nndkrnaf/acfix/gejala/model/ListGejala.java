package com.nndkrnaf.acfix.gejala.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListGejala {

	@SerializedName("data")
	private List<ListGejalaData> data;

	@SerializedName("status")
	private boolean status;

	public List<ListGejalaData> getData(){
		return data;
	}

	public boolean isStatus(){
		return status;
	}

	public void setData(List<ListGejalaData> data) {
		this.data = data;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return
				"ListGejala{" +
						"data = '" + data + '\'' +
						",status = '" + status + '\'' +
						"}";
	}
}
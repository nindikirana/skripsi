package com.nndkrnaf.acfix.kerusakan.model;

import com.google.gson.annotations.SerializedName;

public class ListKerusakanData {

	@SerializedName("Nama_Kerusakan")
	private String namaKerusakan;

	@SerializedName("Id_Kerusakan")
	private String idKerusakan;

	@SerializedName("Solusi")
	private String solusi;

	public String getNamaKerusakan(){
		return namaKerusakan;
	}

	public String getIdKerusakan(){
		return idKerusakan;
	}

	public String getSolusi(){
		return solusi;
	}

	public void setNamaKerusakan(String namaKerusakan) {
		this.namaKerusakan = namaKerusakan;
	}

	public void setIdKerusakan(String idKerusakan) {
		this.idKerusakan = idKerusakan;
	}

	public void setSolusi(String solusi) {
		this.solusi = solusi;
	}

	@Override
	public String toString() {
		return "ListKerusakanData{" +
				"namaKerusakan='" + namaKerusakan + '\'' +
				", idKerusakan='" + idKerusakan + '\'' +
				", solusi='" + solusi + '\'' +
				'}';
	}
}
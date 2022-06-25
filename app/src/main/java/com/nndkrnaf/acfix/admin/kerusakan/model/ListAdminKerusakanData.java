package com.nndkrnaf.acfix.admin.kerusakan.model;

import com.google.gson.annotations.SerializedName;

public class ListAdminKerusakanData {

	@SerializedName("Nama_Kerusakan")
	private String namaKerusakan;

	@SerializedName("Id_Kerusakan")
	private String idKerusakan;

	@SerializedName("Solusi")
	private String solusi;

	public void setNamaKerusakan(String namaKerusakan){
		this.namaKerusakan = namaKerusakan;
	}

	public String getNamaKerusakan(){
		return namaKerusakan;
	}

	public void setIdKerusakan(String idKerusakan){
		this.idKerusakan = idKerusakan;
	}

	public String getIdKerusakan(){
		return idKerusakan;
	}

	public void setSolusi(String solusi){
		this.solusi = solusi;
	}

	public String getSolusi(){
		return solusi;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"nama_Kerusakan = '" + namaKerusakan + '\'' + 
			",id_Kerusakan = '" + idKerusakan + '\'' + 
			",solusi = '" + solusi + '\'' + 
			"}";
		}
}
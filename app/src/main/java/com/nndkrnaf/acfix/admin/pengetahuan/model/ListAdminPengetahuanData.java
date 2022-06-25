package com.nndkrnaf.acfix.admin.pengetahuan.model;

import com.google.gson.annotations.SerializedName;

public class ListAdminPengetahuanData {

	@SerializedName("Id_Pengetahuan")
	private String idPengetahuan;

	@SerializedName("Id_Kerusakan")
	private String idKerusakan;

	@SerializedName("Id_Gejala")
	private String idGejala;

	public void setIdPengetahuan(String idPengetahuan){
		this.idPengetahuan = idPengetahuan;
	}

	public String getIdPengetahuan(){
		return idPengetahuan;
	}

	public void setIdKerusakan(String idKerusakan){
		this.idKerusakan = idKerusakan;
	}

	public String getIdKerusakan(){
		return idKerusakan;
	}

	public void setIdGejala(String idGejala){
		this.idGejala = idGejala;
	}

	public String getIdGejala(){
		return idGejala;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"id_Pengetahuan = '" + idPengetahuan + '\'' + 
			",id_Kerusakan = '" + idKerusakan + '\'' + 
			",id_Gejala = '" + idGejala + '\'' + 
			"}";
		}
}
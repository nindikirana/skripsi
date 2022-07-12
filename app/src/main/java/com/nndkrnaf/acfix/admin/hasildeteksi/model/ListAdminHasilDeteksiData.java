package com.nndkrnaf.acfix.admin.hasildeteksi.model;

import com.google.gson.annotations.SerializedName;

public class ListAdminHasilDeteksiData {

	@SerializedName("Id_User")
	private String idUser;

	@SerializedName("Nama_Gejala")
	private String namaGejala;

	@SerializedName("Id_Deteksi")
	private String idDeteksi;

	@SerializedName("Id_Kerusakan")
	private String idKerusakan;

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setNamaGejala(String namaGejala){
		this.namaGejala = namaGejala;
	}

	public String getNamaGejala(){
		return namaGejala;
	}

	public void setIdDeteksi(String idDeteksi){
		this.idDeteksi = idDeteksi;
	}

	public String getIdDeteksi(){
		return idDeteksi;
	}

	public void setIdKerusakan(String idKerusakan){
		this.idKerusakan = idKerusakan;
	}

	public String getIdKerusakan(){
		return idKerusakan;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"id_User = '" + idUser + '\'' + 
			",nama_Gejala = '" + namaGejala + '\'' + 
			",id_Deteksi = '" + idDeteksi + '\'' + 
			",id_Kerusakan = '" + idKerusakan + '\'' + 
			"}";
		}
}
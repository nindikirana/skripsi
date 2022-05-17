package com.nndkrnaf.acfix.pengetahuan.model;

import com.google.gson.annotations.SerializedName;

public class ListPengetahuanData {

	@SerializedName("Id_Pengetahuan")
	private String idPengetahuan;

	@SerializedName("Nama_Gejala")
	private String namaGejala;

	@SerializedName("Nama_Kerusakan")
	private String namaKerusakan;

	@SerializedName("Id_Kerusakan")
	private String idKerusakan;

	@SerializedName("Solusi")
	private String solusi;

	@SerializedName("Id_Gejala")
	private String idGejala;

	public String getIdPengetahuan(){
		return idPengetahuan;
	}

	public String getNamaGejala(){
		return namaGejala;
	}

	public String getNamaKerusakan(){
		return namaKerusakan;
	}

	public String getIdKerusakan(){
		return idKerusakan;
	}

	public String getSolusi(){
		return solusi;
	}

	public String getIdGejala(){
		return idGejala;
	}

	public void setIdPengetahuan(String idPengetahuan) {
		this.idPengetahuan = idPengetahuan;
	}

	public void setNamaGejala(String namaGejala) {
		this.namaGejala = namaGejala;
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

	public void setIdGejala(String idGejala) {
		this.idGejala = idGejala;
	}

	@Override
	public String toString() {
		return "ListPengetahuanData{" +
				"idPengetahuan='" + idPengetahuan + '\'' +
				", namaGejala='" + namaGejala + '\'' +
				", namaKerusakan='" + namaKerusakan + '\'' +
				", idKerusakan='" + idKerusakan + '\'' +
				", solusi='" + solusi + '\'' +
				", idGejala='" + idGejala + '\'' +
				'}';
	}
}
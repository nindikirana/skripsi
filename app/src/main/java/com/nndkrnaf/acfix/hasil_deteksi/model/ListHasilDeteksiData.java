package com.nndkrnaf.acfix.hasil_deteksi.model;

import com.google.gson.annotations.SerializedName;

public class ListHasilDeteksiData {

	@SerializedName("Nama_Kerusakan")
	private String namaKerusakan;

	@SerializedName("Nama_Gejala")
	private String namaGejala;

	@SerializedName("solusi")
	private String solusi;

	public void setNamaKerusakan(String namaKerusakan){
		this.namaKerusakan = namaKerusakan;
	}

	public String getNamaKerusakan(){
		return namaKerusakan;
	}

	public void setNamaGejala(String namaGejala){
		this.namaGejala = namaGejala;
	}

	public String getNamaGejala(){
		return namaGejala;
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
			",nama_Gejala = '" + namaGejala + '\'' + 
			",solusi = '" + solusi + '\'' + 
			"}";
		}
}
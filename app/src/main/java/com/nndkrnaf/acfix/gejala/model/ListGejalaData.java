package com.nndkrnaf.acfix.gejala.model;

import com.google.gson.annotations.SerializedName;

public class ListGejalaData {

	@SerializedName("Nama_Gejala")
	private String namaGejala;

	@SerializedName("Id_Gejala")
	private String idGejala;

	public String getNamaGejala(){
		return namaGejala;
	}

	public String getIdGejala(){
		return idGejala;
	}

	public void setNamaGejala(String namaGejala) { this.namaGejala = namaGejala; }

	public void setIdGejala(String idGejala) { this.idGejala = idGejala; }

	@Override
	public String toString() {
		return
				"ListGejalaData{" +
						"id_Gejala = '" + idGejala + '\'' +
						",nama_Gejala = '" + namaGejala + '\'' +
						"}";
	}
}
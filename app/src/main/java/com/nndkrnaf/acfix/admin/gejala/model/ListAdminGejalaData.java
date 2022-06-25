package com.nndkrnaf.acfix.admin.gejala.model;

import com.google.gson.annotations.SerializedName;

public class ListAdminGejalaData {

	@SerializedName("Nama_Gejala")
	private String namaGejala;

	@SerializedName("Id_Gejala")
	private String idGejala;

	public void setNamaGejala(String namaGejala){
		this.namaGejala = namaGejala;
	}

	public String getNamaGejala(){
		return namaGejala;
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
			"nama_Gejala = '" + namaGejala + '\'' + 
			",id_Gejala = '" + idGejala + '\'' + 
			"}";
		}
}
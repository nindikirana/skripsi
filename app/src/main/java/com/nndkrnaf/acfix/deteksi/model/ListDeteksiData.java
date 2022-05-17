package com.nndkrnaf.acfix.deteksi.model;

import com.google.gson.annotations.SerializedName;

public class ListDeteksiData {

	@SerializedName("Pertanyaan")
	private String pertanyaan;

	@SerializedName("Id_Deteksi")
	private String idDeteksi;

	@SerializedName("Ya")
	private String ya;

	@SerializedName("Tidak")
	private String tidak;

	@SerializedName("Id_Gejala")
	private String idGejala;

	@SerializedName("Image")
	private String image;

	public String getPertanyaan(){
		return pertanyaan;
	}

	public String getIdDeteksi(){
		return idDeteksi;
	}

	public String getYa(){
		return ya;
	}

	public String getTidak(){
		return tidak;
	}

	public String getIdGejala(){
		return idGejala;
	}

	public String getImage(){
		return image;
	}

	public void setPertanyaan(String pertanyaan) {
		this.pertanyaan = pertanyaan;
	}

	public void setIdDeteksi(String idDeteksi) {
		this.idDeteksi = idDeteksi;
	}

	public void setYa(String ya) {
		this.ya = ya;
	}

	public void setTidak(String tidak) {
		this.tidak = tidak;
	}

	public void setIdGejala(String idGejala) {
		this.idGejala = idGejala;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ListDeteksiData{" +
				"pertanyaan='" + pertanyaan + '\'' +
				", idDeteksi='" + idDeteksi + '\'' +
				", ya='" + ya + '\'' +
				", tidak='" + tidak + '\'' +
				", idGejala='" + idGejala + '\'' +
				", image='" + image + '\'' +
				'}';
	}
}
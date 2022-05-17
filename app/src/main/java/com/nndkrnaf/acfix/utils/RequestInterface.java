package com.nndkrnaf.acfix.utils;

import com.nndkrnaf.acfix.deteksi.model.ListDeteksi;
import com.nndkrnaf.acfix.gejala.model.ListGejala;
import com.nndkrnaf.acfix.pengetahuan.model.ListPengetahuan;
import com.nndkrnaf.acfix.kerusakan.model.ListKerusakan;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {

    @GET("gejala")
    Call<ListGejala> getGejala(
            @Query("Nama_Gejala") String Nama_Gejala
    );

    @GET("kerusakan")
    Call<ListKerusakan> getKerusakan(
            @Query("Nama_Kerusakan") String Nama_Kerusakan
    );

    @GET("pengetahuan")
    Call<ListPengetahuan> getPengetahuanGejala(
            @Query("Id_Gejala") String Id_Gejala
    );

    @GET("pengetahuan")
    Call<ListPengetahuan> getPengetahuanKerusakan(
            @Query("Id_Kerusakan") String Id_Kerusakan
    );


    @GET("deteksi")
    Call<ListDeteksi> getDeteksi(
            @Query("Id_Gejala") String Id_Gejala
    );

}

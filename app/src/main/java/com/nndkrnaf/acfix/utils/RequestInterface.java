package com.nndkrnaf.acfix.utils;

import com.nndkrnaf.acfix.admin.leveluser.model.DeleteAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.UpdateAdminLevelUser;
import com.nndkrnaf.acfix.deteksi.model.ListDeteksi;
import com.nndkrnaf.acfix.gejala.model.ListGejala;
import com.nndkrnaf.acfix.login.model.Login;
import com.nndkrnaf.acfix.pengetahuan.model.ListPengetahuan;
import com.nndkrnaf.acfix.kerusakan.model.ListKerusakan;
import com.nndkrnaf.acfix.registration.model.Message;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestInterface {

    @FormUrlEncoded
    @POST("api/login/proses")
    Call<Login> login(@Field("Email") String email,
                      @Field("Password") String password
    );

    @FormUrlEncoded
    @POST("api/user")
    Call<Message> createuser(@Field("Username") String Username,
                             @Field("Email") String Email,
                             @Field("Password") String Password,
                             @Field("Id_Level") String Id_Level);

    @GET("level_user")
    Call<ListAdminLevelUser> getLevelUser();

    @FormUrlEncoded
    @POST("level_user")
    Call<UpdateAdminLevelUser> postLevelUser(@Field("Level") String Level);

    @FormUrlEncoded
    @POST("level_user")
    Call<UpdateAdminLevelUser> updateLevelUser(@Field("Id_Level") String Id_Level,
                                            @Field("Level") String Level);

    @GET("level_user")
    Call<DeleteAdminLevelUser> deleteAdminLevelUser(@Query("Id_Level") String Id_Level,
                                               @Query("Delete") boolean Delete);



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
    );

}

package com.nndkrnaf.acfix.utils;

import com.nndkrnaf.acfix.admin.gejala.model.DeleteAdminGejala;
import com.nndkrnaf.acfix.admin.gejala.model.ListAdminGejala;
import com.nndkrnaf.acfix.admin.gejala.model.UpdateAdminGejala;
import com.nndkrnaf.acfix.admin.kerusakan.model.DeleteAdminKerusakan;
import com.nndkrnaf.acfix.admin.kerusakan.model.ListAdminKerusakan;
import com.nndkrnaf.acfix.admin.kerusakan.model.UpdateAdminKerusakan;
import com.nndkrnaf.acfix.admin.leveluser.model.DeleteAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.UpdateAdminLevelUser;
import com.nndkrnaf.acfix.admin.pengetahuan.model.DeleteAdminPengetahuan;
import com.nndkrnaf.acfix.admin.pengetahuan.model.ListAdminPengetahuan;
import com.nndkrnaf.acfix.admin.pengetahuan.model.UpdateAdminPengetahuan;
import com.nndkrnaf.acfix.admin.user.model.DeleteAdminUser;
import com.nndkrnaf.acfix.admin.user.model.ListAdminUser;
import com.nndkrnaf.acfix.admin.user.model.UpdateAdminUser;
import com.nndkrnaf.acfix.deteksi.model.ListDeteksi;
import com.nndkrnaf.acfix.gejala.model.ListGejala;
import com.nndkrnaf.acfix.login.model.Login;
import com.nndkrnaf.acfix.pengetahuan.model.ListPengetahuan;
import com.nndkrnaf.acfix.kerusakan.model.ListKerusakan;
import com.nndkrnaf.acfix.registration.model.Message;
import com.nndkrnaf.acfix.rules.model.RuleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @GET("user")
    Call<ListAdminUser> getUser(@Query("Id_User") String Id_User);


    @FormUrlEncoded
    @POST("user")
    Call<UpdateAdminUser> postUser(@Field("Username") String Username,
                                   @Field("Email") String Email,
                                   @Field("Password") String Password,
                                   @Field("Id_Level") String Id_Level);

    @FormUrlEncoded
    @POST("user")
    Call<UpdateAdminUser> updateUser(@Field("Id_User") String Id_User,
                                     @Field("Username") String Username,
                                     @Field("Email") String Email,
                                     @Field("Password") String Password,
                                     @Field("Id_Level") String Id_Level);

    @GET("user")
    Call<DeleteAdminUser> deleteUser(@Query("Id_User") String Id_User,
                                     @Query("Delete") boolean Delete);


    @GET("gejala")
    Call<ListGejala> getGejala(
            @Query("Nama_Gejala") String Nama_Gejala
    );

    @GET("gejala")
    Call<ListAdminGejala> getAdminGejala();

    @FormUrlEncoded
    @POST("gejala")
    Call<UpdateAdminGejala> postGejala(@Field("Nama_Gejala") String Nama_Gejala);

    @FormUrlEncoded
    @POST("gejala")
    Call<UpdateAdminGejala> updateGejala(@Field("Id_Gejala") String Id_Gejala,
                                     @Field("Nama_Gejala") String Nama_Gejala);

    @GET("gejala")
    Call<DeleteAdminGejala> deleteAdminGejala(@Query("Id_Gejala") String Id_Gejala,
                                       @Query("Delete") boolean Delete);

    @GET("kerusakan")
    Call<ListKerusakan> getKerusakan(
            @Query("Nama_Kerusakan") String Nama_Kerusakan
    );

    @GET("kerusakan")
    Call<ListAdminKerusakan> getAdminKerusakan();

    @FormUrlEncoded
    @POST("kerusakan")
    Call<UpdateAdminKerusakan> postAdminKerusakan(@Field("Nama_Kerusakan") String Nama_Kerusakan,
                                                  @Field("Solusi") String Solusi);

    @FormUrlEncoded
    @POST("kerusakan")
    Call<UpdateAdminKerusakan> updateAdminKerusakan(@Field("Id_Kerusakan") String Id_Kerusakan,
                                                    @Field("Nama_Kerusakan") String Nama_Kerusakan,
                                                    @Field("Solusi") String Solusi);

    @GET("kerusakan")
    Call<DeleteAdminKerusakan> deleteAdminKerusakan(@Query("Id_Kerusakan") String Id_Kerusakan,
                                                 @Query("Delete") boolean Delete);


    @GET("pengetahuan")
    Call<ListAdminPengetahuan> getAdminPengetahuan();

    @FormUrlEncoded
    @POST("pengetahuan")
    Call<UpdateAdminPengetahuan> postAdminPengetahuan(@Field("Id_Kerusakan") String Id_Kerusakan,
                                                      @Field("Id_Gejala") String Id_Gejala);

    @FormUrlEncoded
    @POST("pengetahuan")
    Call<UpdateAdminPengetahuan> updateAdminPengetahuan(@Field("Id_Pengetahuan") String Id_Pengetahuan,
                                     @Field("Id_Kerusakan") String Id_Kerusakan,
                                     @Field("Id_Gejala") String Id_Gejala);

    @GET("pengetahuan")
    Call<DeleteAdminPengetahuan> deleteAdminPengetahuan(@Query("Id_Pengetahuan") String Id_Pengetahuan,
                                                         @Query("Delete") boolean Delete);


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

    @POST("rules")
    Call<RuleResponse> sendRules(
            @Body List<String> listIdGejala
    );
}

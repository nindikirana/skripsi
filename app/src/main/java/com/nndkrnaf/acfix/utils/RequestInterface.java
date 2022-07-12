package com.nndkrnaf.acfix.utils;

import com.nndkrnaf.acfix.admin.gejala.model.DeleteAdminGejala;
import com.nndkrnaf.acfix.admin.gejala.model.ListAdminGejala;
import com.nndkrnaf.acfix.admin.gejala.model.UpdateAdminGejala;
import com.nndkrnaf.acfix.admin.hasildeteksi.model.DeleteAdminHasilDeteksi;
import com.nndkrnaf.acfix.admin.hasildeteksi.model.ListAdminHasilDeteksi;
import com.nndkrnaf.acfix.admin.hasildeteksi.model.UpdateAdminHasilDeteksi;
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
import com.nndkrnaf.acfix.hasil_deteksi.model.AddHasilDeteksi;
import com.nndkrnaf.acfix.hasil_deteksi.model.ListHasilDeteksi;
import com.nndkrnaf.acfix.login.model.Login;
import com.nndkrnaf.acfix.pengetahuan.model.ListPengetahuan;
import com.nndkrnaf.acfix.kerusakan.model.ListKerusakan;
import com.nndkrnaf.acfix.registration.model.Message;
import com.nndkrnaf.acfix.rules.model.RuleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RequestInterface {

    //LOGIN USER
    @FormUrlEncoded
    @POST("api/login/proses")
    Call<Login> login(@Field("Email") String email,
                      @Field("Password") String password
    );

    //REGISTRASI USER
    @FormUrlEncoded
    @POST("api/user")
    Call<Message> createuser(@Field("Username") String Username,
                             @Field("Email") String Email,
                             @Field("Password") String Password,
                             @Field("Id_Level") String Id_Level);

    //ADMIN - GET LEVEL USER
    @GET("level_user")
    Call<ListAdminLevelUser> getLevelUser();

    //ADMIN - CREATE LEVEL USER
    @FormUrlEncoded
    @POST("level_user")
    Call<UpdateAdminLevelUser> postLevelUser(@Field("Level") String Level);

    //ADMIN - EDIT LEVEL USER
    @FormUrlEncoded
    @POST("level_user")
    Call<UpdateAdminLevelUser> updateLevelUser(@Field("Id_Level") String Id_Level,
                                            @Field("Level") String Level);

    //ADMIN - DELETE LEVEL USER
    @GET("level_user")
    Call<DeleteAdminLevelUser> deleteAdminLevelUser(@Query("Id_Level") String Id_Level,
                                               @Query("Delete") boolean Delete);


    //ADMIN - GET USER
    @GET("user")
    Call<ListAdminUser> getUser();

    //ADMIN - CREATE USER
    @FormUrlEncoded
    @POST("user")
    Call<UpdateAdminUser> postUser(@Field("Id_Level") String Id_Level,
                                   @Field("Username") String Username,
                                   @Field("Email") String Email,
                                   @Field("Password") String Password);

    //ADMIN - EDIT USER
    @FormUrlEncoded
    @PUT("user")
    Call<UpdateAdminUser> updateUser(@Field("Id_User") String Id_User,
                                     @Field("Id_Level") String Id_Level,
                                     @Field("Username") String Username,
                                     @Field("Email") String Email,
                                     @Field("Password") String Password);

    //ADMIN - DELETE USER
    @GET("user")
    Call<DeleteAdminUser> deleteUser(@Query("Id_User") String Id_User,
                                     @Query("Delete") boolean Delete);


    //USER - GET GEJALA
    @GET("gejala")
    Call<ListGejala> getGejala(
            @Query("Nama_Gejala") String Nama_Gejala
    );


    //ADMIN - GET GEJALA
    @GET("gejala")
    Call<ListAdminGejala> getAdminGejala();

    //ADMIN - CREATE GEJALA
    @FormUrlEncoded
    @POST("gejala")
    Call<UpdateAdminGejala> postGejala(@Field("Nama_Gejala") String Nama_Gejala);

    //ADMIN - EDIT GEJALA
    @FormUrlEncoded
    @POST("gejala")
    Call<UpdateAdminGejala> updateGejala(@Field("Id_Gejala") String Id_Gejala,
                                     @Field("Nama_Gejala") String Nama_Gejala);

    //ADMIN - DELETE GEJALA
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "gejala",hasBody = true)
    Call<DeleteAdminGejala> deleteAdminGejala(@Field("Id_Gejala") String Id_Gejala);


    //USER - GET KERUSAKAN
    @GET("kerusakan")
    Call<ListKerusakan> getKerusakan(
            @Query("Nama_Kerusakan") String Nama_Kerusakan
    );

    //ADMIN - GET KERUSAKAN
    @GET("kerusakan")
    Call<ListAdminKerusakan> getAdminKerusakan();

    //ADMIN - CREATE KERUSAKAN
    @FormUrlEncoded
    @POST("kerusakan")
    Call<UpdateAdminKerusakan> postAdminKerusakan(@Field("Nama_Kerusakan") String Nama_Kerusakan,
                                                  @Field("Solusi") String Solusi);

    //ADMIN - EDIT KERUSAKAN
    @FormUrlEncoded
    @POST("kerusakan")
    Call<UpdateAdminKerusakan> updateAdminKerusakan(@Field("Id_Kerusakan") String Id_Kerusakan,
                                                    @Field("Nama_Kerusakan") String Nama_Kerusakan,
                                                    @Field("Solusi") String Solusi);

    //ADMIN - DELETE KERUSAKAN
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kerusakan",hasBody = true)
    Call<DeleteAdminKerusakan> deleteAdminKerusakan(@Field("Id_Kerusakan") String Id_Kerusakan);

    //ADMIN - GET PENGETAHUAN
    @GET("pengetahuan")
    Call<ListAdminPengetahuan> getAdminPengetahuan();

    //ADMIN - CREATE PENGETAHUAN
    @FormUrlEncoded
    @POST("pengetahuan")
    Call<UpdateAdminPengetahuan> postAdminPengetahuan(@Field("Id_Kerusakan") String Id_Kerusakan,
                                                      @Field("Id_Gejala") String Id_Gejala);

    //ADMIN - EDIT PENGETAHUAN
    @FormUrlEncoded
    @PUT("pengetahuan")
    Call<UpdateAdminPengetahuan> updateAdminPengetahuan(@Field("Id_Pengetahuan") String Id_Pengetahuan,
                                     @Field("Id_Kerusakan") String Id_Kerusakan,
                                     @Field("Id_Gejala") String Id_Gejala);

    //ADMIN - DELETE PENGETAHUAN
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "pengetahuan",hasBody = true)
    Call<DeleteAdminPengetahuan> deleteAdminPengetahuan(@Field("Id_Pengetahuan") String Id_Pengetahuan);

    //ADMIN - GET HASIL DETEKSI
    @GET("hasil_deteksi")
    Call<ListAdminHasilDeteksi> getAdminHasilDeteksi();

    //ADMIN - CREATE HASIL DETEKSI
    @FormUrlEncoded
    @POST("hasil_deteksi")
    Call<UpdateAdminHasilDeteksi> postAdminHasilDeteksi(@Field("Id_User") String Id_User,
                                                       @Field("Id_Kerusakan") String Id_Kerusakan,
                                                       @Field("Nama_Gejala") String Nama_Gejala);

    //ADMIN - EDIT HASIL DETEKSI
    @FormUrlEncoded
    @PUT("hasil_deteksi")
    Call<UpdateAdminHasilDeteksi> updateAdminHasilDeteksi(@Field("Id_Deteksi") String Id_Deteksi,
                                                        @Field("Id_User") String Id_User,
                                                        @Field("Id_Kerusakan") String Id_Kerusakan,
                                                        @Field("Nama_Gejala") String Nama_Gejala);

    //ADMIN - DELETE HASIL DETEKSI
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "hasil_deteksi",hasBody = true)
    Call<DeleteAdminHasilDeteksi> deleteAdminHasilDetekasi(@Field("Id_Deteksi") String Id_Deteksi);



    //USER - GET PENGETAHUAN GEJALA
    @GET("pengetahuan")
    Call<ListPengetahuan> getPengetahuanGejala(
            @Query("Id_Gejala") String Id_Gejala
    );

    //USER - GET PENGETAHUAN KERUSAKAN
    @GET("pengetahuan")
    Call<ListPengetahuan> getPengetahuanKerusakan(
            @Query("Id_Kerusakan") String Id_Kerusakan
    );

    //TIDAK DIPAKAI
    @GET("deteksi")
    Call<ListDeteksi> getDeteksi(
    );

    //USER - MENGIRIM DETEKSI
    @POST("rules")
    Call<RuleResponse> sendRules(
            @Body List<String> listIdGejala
    );

    //USER - GET HASIL DETEKSI BERDASARKAN ID USER
    @GET("hasil_deteksi")
    Call<ListHasilDeteksi> getHasilDeteksi(
            @Query("Id_User") String Id_User
    );

    //USER - CREATE DETEKSI
    @FormUrlEncoded
    @POST("hasil_deteksi")
    Call<AddHasilDeteksi> createHasilDeteksi(@Field("Id_User") String Id_User,
                                             @Field("Id_Kerusakan") String Id_Kerusakan,
                                             @Field("Nama_Gejala") String Nama_Gejala);

}

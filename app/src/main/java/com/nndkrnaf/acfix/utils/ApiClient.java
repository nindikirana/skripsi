package com.nndkrnaf.acfix.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static String BASE_URL = "https://0452-2001-448a-404d-4599-f17b-9d6-efb9-a016.ap.ngrok.io/Acfixapi/api/";
    public static Retrofit retrofit;

    public static  Retrofit getApiClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}


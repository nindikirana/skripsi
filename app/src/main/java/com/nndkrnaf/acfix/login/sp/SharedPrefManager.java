package com.nndkrnaf.acfix.login.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static String Id_User="Id_User";
    public static final String SP_USERNAME = "spUsername";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_PASSWORD = "spPassword";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";
    public static final String SP_IS_ADMIN = "spIsAdmin";
    private static String CARTS="CARTS";

    public static String getCARTS() {
        return CARTS;
    }
    public static void setCARTS(String CARTS) {
        SharedPrefManager.CARTS = CARTS;
    }

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_USERNAME, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public static String getIdUser() {
        return Id_User;
    }
    public String getSpUsername(){
        return sp.getString(SP_USERNAME, "");
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSpPassword(){
        return sp.getString(SP_PASSWORD, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

    public Boolean getSPIsAdmin(){
        return sp.getBoolean(SP_IS_ADMIN, false);
    }
}


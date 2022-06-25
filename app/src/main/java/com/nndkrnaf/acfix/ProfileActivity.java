package com.nndkrnaf.acfix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nndkrnaf.acfix.admin.gejala.adapter.AdminGejalaAdapter;
import com.nndkrnaf.acfix.admin.gejala.model.ListAdminGejala;
import com.nndkrnaf.acfix.admin.gejala.model.ListAdminGejalaData;
import com.nndkrnaf.acfix.admin.user.model.ListAdminUser;
import com.nndkrnaf.acfix.admin.user.model.ListAdminUserData;
import com.nndkrnaf.acfix.login.activity.LoginActivity;
import com.nndkrnaf.acfix.login.sp.SharedPrefManager;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    Button btnLogout;
    RequestInterface requestInterface;
    SharedPrefManager sharedPrefManager;
    TextView tvUsername, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnLogout = findViewById(R.id.btnLogout);

        tvUsername = findViewById(R.id.tvProfUsername);
        tvEmail = findViewById(R.id.tvProfEmail);

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        sharedPrefManager = new SharedPrefManager(this);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);




        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

                SharedPrefManager sharedPrefManager = new SharedPrefManager(getApplicationContext());
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN,false);

            }

        });

        tvUsername.setText(sharedPrefManager.getSpUsername());
        tvEmail.setText(sharedPrefManager.getSPEmail());


        sharedPrefManager.getSpUsername();
        sharedPrefManager.getSPEmail();
       // refresh();
    }

//    public void refresh() {
//        Log.d("WOELAH", "onResponse: "+sharedPrefManager.getId_User());
//        Call<ListAdminUser> profileCall = requestInterface.getUser(sharedPrefManager.getId_User());
//        profileCall.enqueue(new Callback<ListAdminUser>() {
//            @Override
//            public void onResponse(Call<ListAdminUser> call, Response<ListAdminUser> response) {
//                if(response.isSuccessful()) {
//                    if(response.code()==200) {
//                        List<ListAdminUserData> adminUserList = response.body().getData();
//                        Log.d("bbbb", "Jumlah data User : " +
//                                String.valueOf(adminUserList.size()));
//                        tvUsername.setText(adminUserList.get(0).getUsername());
//                        tvEmail.setText(adminUserList.get(0).getEmail());
//
//                    } else {
//                        Log.e("bbbb", "onResponse success: " + response.body());
//                    }
//                } else {
//                    Log.e("bbbb", "onResponse error: " + response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListAdminUser> call, Throwable t) {
//                Log.e("bbbb", t.toString());
//            }
//        });
//    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;

    }
}
package com.nndkrnaf.acfix.login.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nndkrnaf.acfix.MainActivity;
import com.nndkrnaf.acfix.MainActivity2;
import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.registration.activity.RegistrationActivity;
import com.nndkrnaf.acfix.login.model.Login;
import com.nndkrnaf.acfix.login.sp.SharedPrefManager;
import com.nndkrnaf.acfix.utils.RequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button btn_login, btn_registration;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSPSudahLogin()) {
            if (sharedPrefManager.getSPIsAdmin()) {
                startActivity(new Intent(LoginActivity.this, MainActivity2.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            } else {
                startActivity(new Intent(LoginActivity.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }

            finish();
        }

        email = findViewById(R.id.edt_email);
        password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        btn_registration = findViewById(R.id.btn_registration);

        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://de5e-140-213-48-210.ap.ngrok.io/Acfixapi/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RequestInterface request = retrofit.create(RequestInterface.class);
                Call<Login> call = request.login(Email, Password);

                call.enqueue(new Callback<Login>() {

                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getSuccess() == 1) {
                                Log.e("cccc", "onResponse: " + response.body().getIdLevel());
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_IDUSER, response.body().getIdUser());
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME, response.body().getUsername());
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, response.body().getEmail());
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_PASSWORD, response.body().getPassword());
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_IS_ADMIN,
                                        response.body().getIdLevel().equals("L0001"));

                                // disini
                                if (response.body().getIdLevel().equals("L0001")) {
                                    //admin
                                    Intent i = new Intent(LoginActivity.this, MainActivity2.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    //user
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Email salah", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            Log.e("cccc", "onResponse fail: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Log.e("cccc", "onFailure: " + t);
                    }
                });

            }
        });
    }
}

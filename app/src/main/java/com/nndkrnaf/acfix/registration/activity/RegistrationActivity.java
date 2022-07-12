package com.nndkrnaf.acfix.registration.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.registration.model.Message;
import com.nndkrnaf.acfix.utils.RequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    EditText c_password;
    Button btn_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        username = (EditText) findViewById(R.id.edt_r_username);
        email = (EditText) findViewById(R.id.edt_r_email);
        password = (EditText) findViewById(R.id.edt_r_password);
        c_password = (EditText) findViewById(R.id.edt_r_Cpassword);
        btn_regist = (Button) findViewById(R.id.btn_register);


        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
                String Username = username.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                String Cpassword = c_password.getText().toString();
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://0452-2001-448a-404d-4599-f17b-9d6-efb9-a016.ap.ngrok.io/Acfixapi/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RequestInterface request = retrofit.create(RequestInterface.class);
                Call<Message> call = request.createuser(Username, Email, Password, "L0002");

                call.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Selamat " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            Log.e("cccc", "onResponse: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        Toast.makeText(RegistrationActivity.this, "Login Throwable", Toast.LENGTH_LONG).show();
                        Log.e("cccc", "Throwable: " + t);
                    }
                });

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;

    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(username)) {
            Toast t = Toast.makeText(this, "Masukkan username Anda", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(email)) {
            Toast t = Toast.makeText(this, "Masukkan email Anda", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(password)) {
            Toast t = Toast.makeText(this, "Masukkan password Anda", Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
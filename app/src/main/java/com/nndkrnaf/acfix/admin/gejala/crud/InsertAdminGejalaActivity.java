package com.nndkrnaf.acfix.admin.gejala.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.gejala.activity.AdminGejalaActivity;
import com.nndkrnaf.acfix.admin.gejala.model.UpdateAdminGejala;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertAdminGejalaActivity extends AppCompatActivity {

    EditText edtNamaGejala;
    Button btnInsert;
    RequestInterface requestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_admin_gejala);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtNamaGejala= findViewById(R.id.edtAdminGejalaNamaGejala);

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);

        btnInsert = findViewById(R.id.btnInsertAdminGejala);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<UpdateAdminGejala> postAdminGejalaCall =
                        requestInterface.postGejala(
                                edtNamaGejala.getText().toString());

                postAdminGejalaCall.enqueue(new Callback<UpdateAdminGejala>() {
                    @Override
                    public void onResponse(Call<UpdateAdminGejala> call, Response<UpdateAdminGejala> response) {
                        AdminGejalaActivity.AdminGejalaActivity.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UpdateAdminGejala> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
}
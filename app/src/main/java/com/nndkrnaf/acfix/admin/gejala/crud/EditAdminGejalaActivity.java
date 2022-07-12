package com.nndkrnaf.acfix.admin.gejala.crud;

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

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.gejala.activity.AdminGejalaActivity;
import com.nndkrnaf.acfix.admin.gejala.model.DeleteAdminGejala;
import com.nndkrnaf.acfix.admin.gejala.model.UpdateAdminGejala;
import com.nndkrnaf.acfix.admin.leveluser.activity.AdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.leveluser.model.DeleteAdminLevelUser;
import com.nndkrnaf.acfix.admin.user.activity.AdminUserActivity;
import com.nndkrnaf.acfix.admin.user.model.DeleteAdminUser;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAdminGejalaActivity extends AppCompatActivity {

    EditText edtIdGejala;
    EditText edtNamaGejala;
    Button btnUpdate, btnDelete;
    RequestInterface requestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_gejala);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtIdGejala = findViewById(R.id.edtAdminGejalaIdGejala);
        edtNamaGejala = findViewById(R.id.edtAdminGejalaNamaGejala);

        Intent mIntent = getIntent();

        edtIdGejala.setText(mIntent.getStringExtra("Id_Gejala"));
        edtIdGejala.setTag(edtIdGejala.getKeyListener());
        edtIdGejala.setKeyListener(null);

        edtNamaGejala.setText(mIntent.getStringExtra("Nama_Gejala"));

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);

        btnUpdate = findViewById(R.id.btUpdate2);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<UpdateAdminGejala> updateAdminGejalaCall = requestInterface.updateGejala(
                        edtIdGejala.getText().toString(),
                        edtNamaGejala.getText().toString());

                updateAdminGejalaCall.enqueue(new Callback<UpdateAdminGejala>() {
                    @Override
                    public void onResponse(Call<UpdateAdminGejala> call, Response<UpdateAdminGejala> response) {
                        AdminGejalaActivity.AdminGejalaActivity.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UpdateAdminGejala> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnDelete = findViewById(R.id.btDeleteAdminGejala);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtIdGejala.getText().toString().trim().isEmpty() == false) {
                    Call<DeleteAdminGejala> deleteAdminGejalaCall = requestInterface.deleteAdminGejala(edtIdGejala.getText().toString());
                    deleteAdminGejalaCall.enqueue(new Callback<DeleteAdminGejala>() {
                        @Override
                        public void onResponse(Call<DeleteAdminGejala> call, Response<DeleteAdminGejala> response) {
                            if (response.isSuccessful()){
                                if(response.code()==200){
                                    AdminGejalaActivity.AdminGejalaActivity.refresh();
                                    Log.d("DELETE GEJALA WOI", "onResponse: " + String.valueOf(response.message()));
                                    finish();
                                }else{
                                    Log.d("DELETE GEJALA WOI", "onResponse: " + String.valueOf(response.message()));
                                }
                            }else{
                                Log.d("DELETE GEJALA WOI", "onResponse: " + String.valueOf(response.message()));

                            }
                        }

                        @Override
                        public void onFailure(Call<DeleteAdminGejala> call, Throwable t) {
                            Log.d("DELETE GEJALA WOI", "onFailure: " +  t.getMessage());
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
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
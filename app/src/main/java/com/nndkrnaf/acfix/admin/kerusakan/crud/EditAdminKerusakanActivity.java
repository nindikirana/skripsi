package com.nndkrnaf.acfix.admin.kerusakan.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.kerusakan.activity.AdminKerusakanActivity;
import com.nndkrnaf.acfix.admin.kerusakan.model.DeleteAdminKerusakan;
import com.nndkrnaf.acfix.admin.kerusakan.model.UpdateAdminKerusakan;
import com.nndkrnaf.acfix.admin.leveluser.activity.AdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.leveluser.model.DeleteAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.UpdateAdminLevelUser;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAdminKerusakanActivity extends AppCompatActivity {

    EditText edtIdKerusakan;
    EditText edtNamaKerusakan;
    EditText edtSolusi;
    Button btnUpdate, btnDelete;
    RequestInterface requestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_kerusakan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtIdKerusakan = findViewById(R.id.edtAdminKerusakanIdKerusakan);
        edtNamaKerusakan = findViewById(R.id.edtAdminKerusakanNamaKerusakan);
        edtSolusi = findViewById(R.id.edtAdminKerusakanSolusi);

        Intent mIntent = getIntent();

        edtIdKerusakan.setText(mIntent.getStringExtra("Id_Kerusakan"));
        edtIdKerusakan.setTag(edtIdKerusakan.getKeyListener());
        edtIdKerusakan.setKeyListener(null);

        edtNamaKerusakan.setText(mIntent.getStringExtra("Nama_Kerusakan"));
        edtSolusi.setText(mIntent.getStringExtra("Solusi"));

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);

        btnUpdate = findViewById(R.id.btUpdate2);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<UpdateAdminKerusakan> updateAdminKerusakanCall = requestInterface.updateAdminKerusakan(
                        edtIdKerusakan.getText().toString(),
                        edtNamaKerusakan.getText().toString(),
                        edtSolusi.getText().toString());

                updateAdminKerusakanCall.enqueue(new Callback<UpdateAdminKerusakan>() {
                    @Override
                    public void onResponse(Call<UpdateAdminKerusakan> call, Response<UpdateAdminKerusakan> response) {
                        AdminKerusakanActivity.AdminKerusakanActivity.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UpdateAdminKerusakan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnDelete = findViewById(R.id.btDelete2);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtIdKerusakan.getText().toString().trim().isEmpty() == false) {
                    Call<DeleteAdminKerusakan> deleteAdminKerusakanCall = requestInterface.deleteAdminKerusakan(edtIdKerusakan.getText().toString(), true);
                    deleteAdminKerusakanCall.enqueue(new Callback<DeleteAdminKerusakan>() {
                        @Override
                        public void onResponse(Call<DeleteAdminKerusakan> call, Response<DeleteAdminKerusakan> response) {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                            AdminKerusakanActivity.AdminKerusakanActivity.refresh();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<DeleteAdminKerusakan> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
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
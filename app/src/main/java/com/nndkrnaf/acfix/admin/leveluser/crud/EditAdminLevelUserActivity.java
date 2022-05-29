package com.nndkrnaf.acfix.admin.leveluser.crud;

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
import com.nndkrnaf.acfix.admin.leveluser.activity.AdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.leveluser.model.DeleteAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.UpdateAdminLevelUser;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAdminLevelUserActivity extends AppCompatActivity {

    EditText edtIdLevel;
    EditText edtLevel;
    Button btnUpdate, btnDelete;
    RequestInterface requestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_level_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtIdLevel = findViewById(R.id.edtIdLevel);
        edtLevel = findViewById(R.id.edtLevel);

        Intent mIntent = getIntent();

        edtIdLevel.setText(mIntent.getStringExtra("Id_Level"));
        edtIdLevel.setTag(edtIdLevel.getKeyListener());
        edtIdLevel.setKeyListener(null);

        edtLevel.setText(mIntent.getStringExtra("Level"));

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);

        btnUpdate = findViewById(R.id.btUpdate2);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<UpdateAdminLevelUser> updateLevelUserCall = requestInterface.updateLevelUser(
                        edtIdLevel.getText().toString(),
                        edtLevel.getText().toString());

                updateLevelUserCall.enqueue(new Callback<UpdateAdminLevelUser>() {
                    @Override
                    public void onResponse(Call<UpdateAdminLevelUser> call, Response<UpdateAdminLevelUser> response) {
                        AdminLevelUserActivity.AdminlevelUserActivity.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UpdateAdminLevelUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnDelete = findViewById(R.id.btDelete2);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtIdLevel.getText().toString().trim().isEmpty() == false) {
                    Call<DeleteAdminLevelUser> deleteAdminLevelUser = requestInterface.deleteAdminLevelUser(edtIdLevel.getText().toString(), true);
                    deleteAdminLevelUser.enqueue(new Callback<DeleteAdminLevelUser>() {
                        @Override
                        public void onResponse(Call<DeleteAdminLevelUser> call, Response<DeleteAdminLevelUser> response) {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                            AdminLevelUserActivity.AdminlevelUserActivity.refresh();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<DeleteAdminLevelUser> call, Throwable t) {
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
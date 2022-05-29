package com.nndkrnaf.acfix.admin.leveluser.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.leveluser.activity.AdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.leveluser.model.UpdateAdminLevelUser;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertAdminLevelUserActivity extends AppCompatActivity {

    EditText edtLevel;
    Button btnInsert;
    RequestInterface requestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_admin_level_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtLevel= findViewById(R.id.edtLevel);

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);

        btnInsert = findViewById(R.id.btnInsertLevelUser);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<UpdateAdminLevelUser> postLevelUserCall =
                        requestInterface.postLevelUser(
                                edtLevel.getText().toString());

                postLevelUserCall.enqueue(new Callback<UpdateAdminLevelUser>() {
                    @Override
                    public void onResponse(Call<UpdateAdminLevelUser> call, Response<UpdateAdminLevelUser> response) {
                        AdminLevelUserActivity.AdminlevelUserActivity.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UpdateAdminLevelUser> call, Throwable t) {
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
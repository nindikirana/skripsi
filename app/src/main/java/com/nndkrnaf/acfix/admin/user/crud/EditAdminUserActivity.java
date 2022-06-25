package com.nndkrnaf.acfix.admin.user.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUserData;
import com.nndkrnaf.acfix.admin.user.activity.AdminUserActivity;
import com.nndkrnaf.acfix.admin.user.adapter.LevelUserSpinnerAdapter;
import com.nndkrnaf.acfix.admin.user.model.DeleteAdminUser;
import com.nndkrnaf.acfix.admin.user.model.UpdateAdminUser;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAdminUserActivity extends AppCompatActivity {

    EditText edtIdUser;
    EditText edtUsername;
    EditText edtEmail;
    EditText edtPassword;
    AppCompatSpinner spIdLevel;
    Button btnUpdate, btnDelete;
    RequestInterface requestInterface;

    private String selectedLevelUser;
    private LevelUserSpinnerAdapter spLevelUserAdapter;
    private String currentLevelUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtIdUser = findViewById(R.id.edtAdminUserIdUser);
        edtUsername = findViewById(R.id.edtAdminUserUsername);
        edtEmail = findViewById(R.id.edtAdminUserEmail);
        edtPassword = findViewById(R.id.edtAdminUserPassword);
        spIdLevel = findViewById(R.id.spIdLevel);
        Intent mIntent = getIntent();

        edtIdUser.setText(mIntent.getStringExtra("Id_User"));
        edtIdUser.setTag(edtIdUser.getKeyListener());
        edtIdUser.setKeyListener(null);

        edtUsername.setText(mIntent.getStringExtra("Username"));
        edtEmail.setText(mIntent.getStringExtra("Email"));
        edtPassword.setText(mIntent.getStringExtra("Password"));
        currentLevelUser = mIntent.getStringExtra("Id_Level");

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);

        requestInterface.getLevelUser().enqueue(new Callback<ListAdminLevelUser>() {
            @Override
            public void onResponse(Call<ListAdminLevelUser> call, Response<ListAdminLevelUser> response) {
                if (response.isSuccessful()) {
                    if (response.body().isStatus()) {
                        setSpinnerLevelUserData(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListAdminLevelUser> call, Throwable t) {

            }
        });

        btnUpdate = findViewById(R.id.btUpdate2);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<UpdateAdminUser> updateUserCall = requestInterface.updateUser(
                        edtIdUser.getText().toString(),
                        edtUsername.getText().toString(),
                        edtEmail.getText().toString(),
                        edtPassword.getText().toString(),
                        selectedLevelUser);

                updateUserCall.enqueue(new Callback<UpdateAdminUser>() {
                    @Override
                    public void onResponse(Call<UpdateAdminUser> call, Response<UpdateAdminUser> response) {
                        AdminUserActivity.AdminUserActivity.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UpdateAdminUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnDelete = findViewById(R.id.btDelete2);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtIdUser.getText().toString().trim().isEmpty() == false) {
                    Call<DeleteAdminUser> deleteUser = requestInterface.deleteUser(edtIdUser.getText().toString(), true);
                    deleteUser.enqueue(new Callback<DeleteAdminUser>() {
                        @Override
                        public void onResponse(Call<DeleteAdminUser> call, Response<DeleteAdminUser> response) {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                            AdminUserActivity.AdminUserActivity.refresh();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<DeleteAdminUser> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void initSpinner() {

    }
    private void setSpinnerLevelUserData(List<ListAdminLevelUserData> data) {
        List<String> stringData = new ArrayList<>();
        final List<String> stringIdData = new ArrayList<>();
        int position = 0;
        for (ListAdminLevelUserData userData : data) {
            stringData.add(userData.getLevel());
            stringIdData.add(userData.getIdLevel());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                stringData);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spIdLevel.setAdapter(adapter);

        spIdLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLevelUser = stringIdData.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for (int i=0; i <= data.size()-1; i++) {
            if (currentLevelUser.equals(stringIdData.get(i))) {
                position = i;
            }
        }
        spIdLevel.setSelection(position);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }
}

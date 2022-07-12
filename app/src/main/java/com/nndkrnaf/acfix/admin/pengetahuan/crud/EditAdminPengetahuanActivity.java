package com.nndkrnaf.acfix.admin.pengetahuan.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.gejala.model.ListAdminGejala;
import com.nndkrnaf.acfix.admin.gejala.model.ListAdminGejalaData;
import com.nndkrnaf.acfix.admin.kerusakan.model.ListAdminKerusakan;
import com.nndkrnaf.acfix.admin.kerusakan.model.ListAdminKerusakanData;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUserData;
import com.nndkrnaf.acfix.admin.pengetahuan.activity.AdminPengetahuanActivity;
import com.nndkrnaf.acfix.admin.pengetahuan.adapter.GejalaSpinnerAdapter;
import com.nndkrnaf.acfix.admin.pengetahuan.adapter.KerusakanSpinnerAdapter;
import com.nndkrnaf.acfix.admin.pengetahuan.model.DeleteAdminPengetahuan;
import com.nndkrnaf.acfix.admin.pengetahuan.model.UpdateAdminPengetahuan;
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

public class EditAdminPengetahuanActivity extends AppCompatActivity {

    EditText edtIdPengetahuan;
    AppCompatSpinner spIdKerusakan;
    AppCompatSpinner spIdGejala;
    Button btnUpdate, btnDelete;
    RequestInterface requestInterface;

    private String selectedIdKerusakan;
    private KerusakanSpinnerAdapter spKerusakanAdapter;
    private String currentIdKerusakan;

    private String selectedIdGejala;
    private GejalaSpinnerAdapter spGejalaAdapter;
    private String currentIdGejala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_pengetahuan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtIdPengetahuan = findViewById(R.id.edtIdPengetahuan);
        spIdKerusakan = findViewById(R.id.spIdKerusakan);
        spIdGejala = findViewById(R.id.spIdGejala);

        Intent mIntent = getIntent();

        edtIdPengetahuan.setText(mIntent.getStringExtra("Id_Pengetahuan"));
        edtIdPengetahuan.setTag(edtIdPengetahuan.getKeyListener());
        edtIdPengetahuan.setKeyListener(null);

        currentIdKerusakan = mIntent.getStringExtra("Id_Kerusakan");
        currentIdGejala = mIntent.getStringExtra("Id_Gejala");

        initSpinner();

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);

        requestInterface.getAdminKerusakan().enqueue(new Callback<ListAdminKerusakan>() {
            @Override
            public void onResponse(Call<ListAdminKerusakan> call, Response<ListAdminKerusakan> response) {
                if (response.isSuccessful()) {
                    if (response.body().isStatus()) {
                        setSpinnerKerusakanData(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListAdminKerusakan> call, Throwable t) {

            }
        });

        requestInterface.getAdminGejala().enqueue(new Callback<ListAdminGejala>() {
            @Override
            public void onResponse(Call<ListAdminGejala> call, Response<ListAdminGejala> response) {
                if (response.isSuccessful()) {
                    if (response.body().isStatus()) {
                        setSpinnerGejalaData(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListAdminGejala> call, Throwable t) {

            }
        });

        btnUpdate = findViewById(R.id.btUpdate2);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<UpdateAdminPengetahuan> updatePengetahuanCall = requestInterface.updateAdminPengetahuan(
                        edtIdPengetahuan.getText().toString(),
                        selectedIdKerusakan,
                        selectedIdGejala);

                updatePengetahuanCall.enqueue(new Callback<UpdateAdminPengetahuan>() {
                    @Override
                    public void onResponse(Call<UpdateAdminPengetahuan> call, Response<UpdateAdminPengetahuan> response) {
                        AdminPengetahuanActivity.adminPengetahuanActivity.refresh();
                        if (response.isSuccessful()){
                            if(response.code()==200){
                                finish();
                            }else{
                                Log.d("UPDATE PENGETAHUAN WOI", "onResponse: " + String.valueOf(response.message()));
                            }
                        }else{
                            Log.d("UPDATE PENGETAHUAN WOI", "onResponse: " + String.valueOf(response.message()));

                        }
                    }


                    @Override
                    public void onFailure(Call<UpdateAdminPengetahuan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnDelete = findViewById(R.id.btDelete2);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtIdPengetahuan.getText().toString().trim().isEmpty()) {
                    Call<DeleteAdminPengetahuan> deletePengetahuan = requestInterface.deleteAdminPengetahuan(edtIdPengetahuan.getText().toString());
                    deletePengetahuan.enqueue(new Callback<DeleteAdminPengetahuan>() {
                        @Override
                        public void onResponse(Call<DeleteAdminPengetahuan> call, Response<DeleteAdminPengetahuan> response) {
                            AdminPengetahuanActivity.adminPengetahuanActivity.refresh();
                            if (response.isSuccessful()){
                                if(response.code()==200){
                                    finish();
                                }else{
                                    Log.d("DELETE PENGETAHUAN WOI", "onResponse: " + String.valueOf(response.message()));
                                }
                            }else{
                                Log.d("DELETE PENGETAHUAN WOI", "onResponse: " + String.valueOf(response.message()));

                            }
                        }

                        @Override
                        public void onFailure(Call<DeleteAdminPengetahuan> call, Throwable t) {
                            Log.d("DELETE PENGETAHUAN WOI", "onFailure: " +  t.getMessage());
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

    private void setSpinnerKerusakanData(List<ListAdminKerusakanData> data) {
        List<String> stringData = new ArrayList<>();
        int position = 0;
        for (ListAdminKerusakanData kerusakanData : data) {
            stringData.add(kerusakanData .getIdKerusakan());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                stringData);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spIdKerusakan.setAdapter(adapter);

        spIdKerusakan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedIdKerusakan = stringData.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for (int i=0; i <= data.size()-1; i++) {
            if (currentIdKerusakan.equals(stringData.get(i))) {
                position = i;
            }
        }
        spIdKerusakan.setSelection(position);
    }

    private void setSpinnerGejalaData(List<ListAdminGejalaData> data) {
        List<String> stringData = new ArrayList<>();

        int position = 0;
        for (ListAdminGejalaData gejalaData : data) {
            stringData.add(gejalaData .getIdGejala());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                stringData);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spIdGejala.setAdapter(adapter);

        spIdGejala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedIdGejala = stringData.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for (int i=0; i <= data.size()-1; i++) {
            if (currentIdGejala.equals(stringData.get(i))) {
                position = i;
            }
        }
        spIdGejala.setSelection(position);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }
}
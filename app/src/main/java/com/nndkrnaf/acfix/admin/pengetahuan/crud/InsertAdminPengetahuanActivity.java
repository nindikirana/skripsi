package com.nndkrnaf.acfix.admin.pengetahuan.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

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
import com.nndkrnaf.acfix.admin.pengetahuan.model.UpdateAdminPengetahuan;
import com.nndkrnaf.acfix.admin.user.activity.AdminUserActivity;
import com.nndkrnaf.acfix.admin.user.adapter.LevelUserSpinnerAdapter;
import com.nndkrnaf.acfix.admin.user.model.UpdateAdminUser;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertAdminPengetahuanActivity extends AppCompatActivity {

    AppCompatSpinner spIdKerusakan;
    AppCompatSpinner spIdGejala;
    Button btnInsert;
    RequestInterface requestInterface;

    private String selectedKerusakan;
    private KerusakanSpinnerAdapter spKerusakanAdapter;
    private String currentKerusakan;

    private String selectedGejala;
    private GejalaSpinnerAdapter spGejalaAdapter;
    private String currentGejala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_admin_pengetahuan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        spIdKerusakan = findViewById(R.id.spIdKerusakan);
        spIdGejala = findViewById(R.id.spIdGejala);

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);

        requestInterface.getAdminKerusakan().enqueue(new Callback<ListAdminKerusakan>() {
            @Override
            public void onResponse(Call<ListAdminKerusakan> call, Response<ListAdminKerusakan> response) {
                if (response.isSuccessful()) {
                    if (response.code()==200) {
                        Log.d("SIZE KERUSAKAN", String.valueOf(response.body().getData().size()));
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

        btnInsert = findViewById(R.id.btnInsertPengetahuan);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PARAMETER PENGETAHUAN", selectedKerusakan + selectedGejala);
                Call<UpdateAdminPengetahuan> postAdminPengetahuanCall =
                        requestInterface.postAdminPengetahuan(
                                selectedKerusakan,
                                selectedGejala);

                postAdminPengetahuanCall.enqueue(new Callback<UpdateAdminPengetahuan>() {
                    @Override
                    public void onResponse(Call<UpdateAdminPengetahuan> call, Response<UpdateAdminPengetahuan> response) {
                        AdminPengetahuanActivity.adminPengetahuanActivity.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UpdateAdminPengetahuan> call, Throwable t) {
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

    private void setSpinnerKerusakanData(List<ListAdminKerusakanData> data) {
        List<String> stringData = new ArrayList<>();
        int position = 0;
        for (ListAdminKerusakanData pengetahuanData : data) {
            stringData.add(pengetahuanData.getIdKerusakan());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                stringData);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spIdKerusakan.setAdapter(adapter);

        spIdKerusakan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedKerusakan = stringData.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spIdKerusakan.setSelection(position);
    }

    private void setSpinnerGejalaData(List<ListAdminGejalaData> data) {
        List<String> stringData = new ArrayList<>();
        int position = 0;
        for (ListAdminGejalaData pengetahuanData : data) {
            stringData.add(pengetahuanData.getIdGejala());
            //stringIdData.add(pengetahuanData.getNamaGejala());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                stringData);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spIdGejala.setAdapter(adapter);

        spIdGejala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGejala = stringData.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spIdGejala.setSelection(position);
    }

}
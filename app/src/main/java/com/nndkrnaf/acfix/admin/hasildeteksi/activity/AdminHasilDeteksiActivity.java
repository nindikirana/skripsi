package com.nndkrnaf.acfix.admin.hasildeteksi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.hasildeteksi.adapter.AdminHasilDeteksiAdapter;
import com.nndkrnaf.acfix.admin.hasildeteksi.crud.InsertAdminHasilDeteksiActivity;
import com.nndkrnaf.acfix.admin.hasildeteksi.model.ListAdminHasilDeteksi;
import com.nndkrnaf.acfix.admin.hasildeteksi.model.ListAdminHasilDeteksiData;
import com.nndkrnaf.acfix.admin.pengetahuan.activity.AdminPengetahuanActivity;
import com.nndkrnaf.acfix.admin.pengetahuan.adapter.AdminPengetahuanAdapter;
import com.nndkrnaf.acfix.admin.pengetahuan.crud.InsertAdminPengetahuanActivity;
import com.nndkrnaf.acfix.admin.pengetahuan.model.ListAdminPengetahuan;
import com.nndkrnaf.acfix.admin.pengetahuan.model.ListAdminPengetahuanData;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminHasilDeteksiActivity extends AppCompatActivity {

    Button btnInsert;
    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static AdminHasilDeteksiActivity adminHasilDeteksiActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hasil_deteksi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHasilDeteksiActivity.this, InsertAdminHasilDeteksiActivity.class));
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        adminHasilDeteksiActivity = this;
        refresh();
    }

    public void refresh() {
        Call<ListAdminHasilDeteksi> adminHasilDeteksiCall = requestInterface.getAdminHasilDeteksi();
        adminHasilDeteksiCall.enqueue(new Callback<ListAdminHasilDeteksi>() {
            @Override
            public void onResponse(Call<ListAdminHasilDeteksi> call, Response<ListAdminHasilDeteksi> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListAdminHasilDeteksiData> AdminHasilDeteksiList = response.body().getData();
                        Log.d("bbbb", "Jumlah data Pengetahuan : " +
                                String.valueOf(AdminHasilDeteksiList.size()));
                        adapter = new AdminHasilDeteksiAdapter(AdminHasilDeteksiList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("bbbb", "onResponse success: " + response.body());
                    }
                } else {
                    Log.e("bbbb", "onResponse error: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<ListAdminHasilDeteksi> call, Throwable t) {
                Log.e("bbbb", t.toString());
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
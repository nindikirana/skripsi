package com.nndkrnaf.acfix.admin.pengetahuan.activity;

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
import com.nndkrnaf.acfix.admin.pengetahuan.adapter.AdminPengetahuanAdapter;
import com.nndkrnaf.acfix.admin.pengetahuan.crud.InsertAdminPengetahuanActivity;
import com.nndkrnaf.acfix.admin.pengetahuan.model.ListAdminPengetahuan;
import com.nndkrnaf.acfix.admin.pengetahuan.model.ListAdminPengetahuanData;
import com.nndkrnaf.acfix.admin.user.activity.AdminUserActivity;
import com.nndkrnaf.acfix.admin.user.adapter.AdminUserAdapter;
import com.nndkrnaf.acfix.admin.user.crud.InsertAdminUserActivity;
import com.nndkrnaf.acfix.admin.user.model.ListAdminUser;
import com.nndkrnaf.acfix.admin.user.model.ListAdminUserData;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPengetahuanActivity extends AppCompatActivity {

    Button btnInsert;
    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static AdminPengetahuanActivity adminPengetahuanActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pengetahuan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPengetahuanActivity.this, InsertAdminPengetahuanActivity.class));
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        adminPengetahuanActivity = this;
        refresh();
    }

    public void refresh() {
        Call<ListAdminPengetahuan> adminPengetahuanCall = requestInterface.getAdminPengetahuan();
        adminPengetahuanCall.enqueue(new Callback<ListAdminPengetahuan>() {
            @Override
            public void onResponse(Call<ListAdminPengetahuan> call, Response<ListAdminPengetahuan> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListAdminPengetahuanData> AdminPengetahuanList = response.body().getData();
                        Log.d("bbbb", "Jumlah data Pengetahuan : " +
                                String.valueOf(AdminPengetahuanList.size()));
                        adapter = new AdminPengetahuanAdapter(AdminPengetahuanList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("bbbb", "onResponse success: " + response.body());
                    }
                } else {
                    Log.e("bbbb", "onResponse error: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<ListAdminPengetahuan> call, Throwable t) {
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
package com.nndkrnaf.acfix.admin.gejala.activity;

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
import com.nndkrnaf.acfix.admin.gejala.adapter.AdminGejalaAdapter;
import com.nndkrnaf.acfix.admin.gejala.crud.InsertAdminGejalaActivity;
import com.nndkrnaf.acfix.admin.gejala.model.ListAdminGejala;
import com.nndkrnaf.acfix.admin.gejala.model.ListAdminGejalaData;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminGejalaActivity extends AppCompatActivity {

    Button btnInsert;
    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static AdminGejalaActivity AdminGejalaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_gejala);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnInsert = (Button) findViewById(R.id.btnInsertAdminGejala);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminGejalaActivity.this, InsertAdminGejalaActivity.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        AdminGejalaActivity = this;
        refresh();
    }

    public void refresh() {
        Call<ListAdminGejala> adminGejalaCall = requestInterface.getAdminGejala();
        adminGejalaCall.enqueue(new Callback<ListAdminGejala>() {
            @Override
            public void onResponse(Call<ListAdminGejala> call, Response<ListAdminGejala> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListAdminGejalaData> AdminGejalaList = response.body().getData();
                        Log.d("bbbb", "Jumlah data Admin : " +
                                String.valueOf(AdminGejalaList.size()));
                        adapter = new AdminGejalaAdapter(AdminGejalaList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("bbbb", "onResponse success: " + response.body());
                    }
                } else {
                    Log.e("bbbb", "onResponse error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ListAdminGejala> call, Throwable t) {
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
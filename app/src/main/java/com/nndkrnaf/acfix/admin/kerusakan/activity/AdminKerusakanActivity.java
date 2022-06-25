package com.nndkrnaf.acfix.admin.kerusakan.activity;

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
import com.nndkrnaf.acfix.admin.kerusakan.adapter.AdminKerusakanAdapter;
import com.nndkrnaf.acfix.admin.kerusakan.crud.InsertAdminKerusakanActivity;
import com.nndkrnaf.acfix.admin.kerusakan.model.ListAdminKerusakan;
import com.nndkrnaf.acfix.admin.kerusakan.model.ListAdminKerusakanData;
import com.nndkrnaf.acfix.admin.leveluser.activity.AdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.leveluser.adapter.AdminLevelUserAdapter;
import com.nndkrnaf.acfix.admin.leveluser.crud.InsertAdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUserData;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminKerusakanActivity extends AppCompatActivity {

    Button btnInsert;
    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static AdminKerusakanActivity AdminKerusakanActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kerusakan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminKerusakanActivity.this, InsertAdminKerusakanActivity.class));
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        AdminKerusakanActivity = this;
        refresh();
    }

    public void refresh() {
        Call<ListAdminKerusakan> kerusakanCall = requestInterface.getAdminKerusakan();
        kerusakanCall.enqueue(new Callback<ListAdminKerusakan>() {
            @Override
            public void onResponse(Call<ListAdminKerusakan> call, Response<ListAdminKerusakan> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListAdminKerusakanData> AdminKerusakanList = response.body().getData();
                        Log.d("bbbb", "Jumlah data Kerusakan : " +
                                String.valueOf(AdminKerusakanList.size()));
                        adapter = new AdminKerusakanAdapter(AdminKerusakanList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("bbbb", "onResponse success: " + response.body());
                    }
                } else {
                    Log.e("bbbb", "onResponse error: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<ListAdminKerusakan> call, Throwable t) {
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
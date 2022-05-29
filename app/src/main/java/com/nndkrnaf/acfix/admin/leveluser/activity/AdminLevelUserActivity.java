package com.nndkrnaf.acfix.admin.leveluser.activity;

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

public class AdminLevelUserActivity extends AppCompatActivity {

    Button btnInsert;
    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static AdminLevelUserActivity AdminlevelUserActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_level_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLevelUserActivity.this, InsertAdminLevelUserActivity.class));
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        AdminlevelUserActivity = this;
        refresh();
    }

    public void refresh() {
        Call<ListAdminLevelUser> levelUserCall = requestInterface.getLevelUser();
        levelUserCall.enqueue(new Callback<ListAdminLevelUser>() {
            @Override
            public void onResponse(Call<ListAdminLevelUser> call, Response<ListAdminLevelUser> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListAdminLevelUserData> AdminLevelUserList = response.body().getData();
                        Log.d("bbbb", "Jumlah data Level User : " +
                                String.valueOf(AdminLevelUserList.size()));
                        adapter = new AdminLevelUserAdapter(AdminLevelUserList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("bbbb", "onResponse success: " + response.body());
                    }
                } else {
                    Log.e("bbbb", "onResponse error: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<ListAdminLevelUser> call, Throwable t) {
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
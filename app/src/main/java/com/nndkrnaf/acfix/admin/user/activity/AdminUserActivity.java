package com.nndkrnaf.acfix.admin.user.activity;

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
import com.nndkrnaf.acfix.admin.leveluser.activity.AdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.leveluser.adapter.AdminLevelUserAdapter;
import com.nndkrnaf.acfix.admin.leveluser.crud.InsertAdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUserData;
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

public class AdminUserActivity extends AppCompatActivity {


    Button btnInsert;
    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static AdminUserActivity AdminUserActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminUserActivity.this, InsertAdminUserActivity.class));
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        AdminUserActivity = this;
        refresh();
    }

    public void refresh() {
        Call<ListAdminUser> userCall = requestInterface.getUser("");
        userCall.enqueue(new Callback<ListAdminUser>() {
            @Override
            public void onResponse(Call<ListAdminUser> call, Response<ListAdminUser> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListAdminUserData> AdminUserList = response.body().getData();
                        Log.d("bbbb", "Jumlah data User : " +
                                String.valueOf(AdminUserList.size()));
                        adapter = new AdminUserAdapter(AdminUserList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("bbbb", "onResponse success: " + response.body());
                    }
                } else {
                    Log.e("bbbb", "onResponse error: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<ListAdminUser> call, Throwable t) {
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
package com.nndkrnaf.acfix.kerusakan.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.gejala.activity.GejalaActivity;
import com.nndkrnaf.acfix.gejala.adapter.GejalaAdapter;
import com.nndkrnaf.acfix.gejala.model.ListGejala;
import com.nndkrnaf.acfix.gejala.model.ListGejalaData;
import com.nndkrnaf.acfix.kerusakan.adapter.KerusakanAdapter;
import com.nndkrnaf.acfix.kerusakan.model.ListKerusakan;
import com.nndkrnaf.acfix.kerusakan.model.ListKerusakanData;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KerusakanActivity extends AppCompatActivity {

    SearchView searchView;
    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static KerusakanActivity kerusakanActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kerusakan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        searchView = findViewById(R.id.SearchKerusakan);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                refresh(newText);
                return true;
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        kerusakanActivity = this;
        refresh("");
    }

    public void refresh(String param) {
        Call<ListKerusakan> kerusakanCall = requestInterface.getKerusakan(param);
        kerusakanCall.enqueue(new Callback<ListKerusakan>() {
            @Override
            public void onResponse(Call<ListKerusakan> call, Response<ListKerusakan> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListKerusakanData> KerusakanList = response.body().getData();
                        Log.d("bbbb", "Jumlah data Kerusakan : " +
                                String.valueOf(KerusakanList.size()));
                        adapter = new KerusakanAdapter(KerusakanList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("bbbb", "onResponse success: " + response.body());
                    }
                } else {
                    Log.e("bbbb", "onResponse error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ListKerusakan> call, Throwable t) {
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
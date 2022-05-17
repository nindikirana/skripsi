package com.nndkrnaf.acfix.gejala.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.gejala.adapter.GejalaAdapter;
import com.nndkrnaf.acfix.gejala.model.ListGejala;
import com.nndkrnaf.acfix.gejala.model.ListGejalaData;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GejalaActivity extends AppCompatActivity {

    SearchView searchView;
    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static GejalaActivity gejalaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        searchView = findViewById(R.id.SearchGejala);
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
        gejalaActivity = this;
        refresh("");
    }

    public void refresh(String param) {
        Call<ListGejala> gejalaCall = requestInterface.getGejala(param);
        gejalaCall.enqueue(new Callback<ListGejala>() {
            @Override
            public void onResponse(Call<ListGejala> call, Response<ListGejala> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListGejalaData> GejalaList = response.body().getData();
                        Log.d("bbbb", "Jumlah data Gejala : " +
                                String.valueOf(GejalaList.size()));
                        adapter = new GejalaAdapter(GejalaList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("bbbb", "onResponse success: " + response.body());
                    }
                } else {
                    Log.e("bbbb", "onResponse error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ListGejala> call, Throwable t) {
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
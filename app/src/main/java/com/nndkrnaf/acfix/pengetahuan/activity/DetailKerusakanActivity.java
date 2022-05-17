package com.nndkrnaf.acfix.pengetahuan.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.pengetahuan.adapter.PengetahuanGejalaAdapter;
import com.nndkrnaf.acfix.pengetahuan.adapter.PengetahuanKerusakanAdapter;
import com.nndkrnaf.acfix.pengetahuan.model.ListPengetahuan;
import com.nndkrnaf.acfix.pengetahuan.model.ListPengetahuanData;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKerusakanActivity extends AppCompatActivity {

    TextView tvNamaKerusakan;
    TextView tvNamaSolusi;
    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static DetailKerusakanActivity detailkerusakanActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kerusakan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        tvNamaKerusakan = (TextView) findViewById(R.id.tvNama_Kerusakan);
        tvNamaKerusakan.setText(getIntent().getStringExtra("Nama_Kerusakan"));
        tvNamaSolusi = (TextView) findViewById(R.id.tvNama_Solusi);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        detailkerusakanActivity = this;
        refresh();
    }
    public void refresh() {
        Call<ListPengetahuan> pengetahuanCall = requestInterface.getPengetahuanKerusakan(getIntent().getStringExtra("Id_Kerusakan"));
        pengetahuanCall.enqueue(new Callback<ListPengetahuan>() {
            @Override
            public void onResponse(Call<ListPengetahuan> call, Response<ListPengetahuan> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListPengetahuanData> PengetahuanList = response.body().getData();
                        Log.d("bbbb", "Jumlah data Pengetahuan : " +
                                String.valueOf(PengetahuanList.get(0).getSolusi()));
                        adapter = new PengetahuanKerusakanAdapter(PengetahuanList);
                        recyclerView.setAdapter(adapter);
                        tvNamaSolusi.setText(PengetahuanList.get(0).getSolusi());
                    } else {
                        Log.e("bbbb", "onResponse success: " + response.body());
                    }
                } else {
                    Log.e("bbbb", "onResponse error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ListPengetahuan> call, Throwable t) {
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
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
import com.nndkrnaf.acfix.pengetahuan.model.ListPengetahuan;
import com.nndkrnaf.acfix.pengetahuan.model.ListPengetahuanData;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailGejalaActivity extends AppCompatActivity {

    TextView tvNamaGejala;
    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static DetailGejalaActivity detailgejalaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gejala);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        tvNamaGejala = (TextView) findViewById(R.id.tvNama_Gejala);
        tvNamaGejala.setText(getIntent().getStringExtra("Nama_Gejala"));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        detailgejalaActivity = this;
        refresh();
    }
    public void refresh() {
        Call<ListPengetahuan> pengetahuanCall = requestInterface.getPengetahuanGejala(getIntent().getStringExtra("Id_Gejala"));
        pengetahuanCall.enqueue(new Callback<ListPengetahuan>() {
            @Override
            public void onResponse(Call<ListPengetahuan> call, Response<ListPengetahuan> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListPengetahuanData> PengetahuanList = response.body().getData();
                        Log.d("bbbb", "Jumlah data Pengetahuan : " +
                                String.valueOf(PengetahuanList.size()));
                        adapter = new PengetahuanGejalaAdapter(PengetahuanList);
                        recyclerView.setAdapter(adapter);
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
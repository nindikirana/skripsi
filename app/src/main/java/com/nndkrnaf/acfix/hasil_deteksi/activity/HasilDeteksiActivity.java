package com.nndkrnaf.acfix.hasil_deteksi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.hasil_deteksi.adapter.HasilDeteksiAdapter;
import com.nndkrnaf.acfix.hasil_deteksi.model.ListHasilDeteksi;
import com.nndkrnaf.acfix.hasil_deteksi.model.ListHasilDeteksiData;
import com.nndkrnaf.acfix.login.sp.SharedPrefManager;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilDeteksiActivity extends AppCompatActivity {

    RequestInterface requestInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPrefManager sharedPrefManager;
    public static HasilDeteksiActivity hasilDeteksiActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_deteksi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        sharedPrefManager = new SharedPrefManager(this);
        hasilDeteksiActivity = this;
        refresh();
    }



    public void refresh() {

        Call<ListHasilDeteksi> hasilDeteksiCall = requestInterface.getHasilDeteksi(sharedPrefManager.getId_User());
        Toast.makeText(getApplicationContext(), sharedPrefManager.getId_User(),Toast.LENGTH_LONG).show();
        hasilDeteksiCall.enqueue(new Callback<ListHasilDeteksi>() {
            @Override
            public void onResponse(Call<ListHasilDeteksi> call, Response<ListHasilDeteksi> response) {
                if(response.isSuccessful()) {
                    if(response.body().isStatus()) {
                        List<ListHasilDeteksiData> hasilDeteksiList = response.body().getData();
                        Log.d("bbbb", "Jumlah data Hasil Deteksi : " +
                                String.valueOf(hasilDeteksiList.size()));
                        adapter = new HasilDeteksiAdapter(hasilDeteksiList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.e("bbbb", "onResponse success: " + response.body());
                    }
                } else {
                    Log.e("bbbb", "onResponse error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ListHasilDeteksi> call, Throwable t) {
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
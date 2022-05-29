package com.nndkrnaf.acfix.deteksi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.deteksi.model.ListDeteksi;
import com.nndkrnaf.acfix.deteksi.model.ListDeteksiData;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeteksiActivity extends AppCompatActivity {

    RequestInterface requestInterface;
    TextView tvPertanyaan, tvNext, tvBack;
    Button btnYa, btnTidak;
    ImageView imgAsk;
    List<ListDeteksiData> listDeteksiData;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deteksi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        tvPertanyaan = findViewById(R.id.tvPertanyaan);
        tvBack = findViewById(R.id.tvKembali);
        tvNext = findViewById(R.id.tvSelanjutnya);
        btnYa = findViewById(R.id.btnYa);
        btnTidak = findViewById(R.id.btnTidak);
        imgAsk = findViewById(R.id.imgAsk);


        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        getDeteksi();

        btnYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (listDeteksiData != null) {
                    if (!Objects.equals(listDeteksiData.get(i).getYa(), "")) {
                        Collection<ListDeteksiData> males = Collections2.filter(listDeteksiData, user -> user.getIdGejala().equals(listDeteksiData.get(i).getYa()));
                        i = i + 1;
                        tvPertanyaan.setText(males.toString());
                    }else{
                        Toast.makeText(getApplicationContext(),"Selesai",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

    private void getDeteksi() {
        Log.d("NINDI KIRANA","TERPANGGIL");
        requestInterface.getDeteksi().enqueue(new Callback<ListDeteksi>() {
            @Override
            public void onResponse(Call<ListDeteksi> call, Response<ListDeteksi> response) {
                Log.d("YOLO", String.valueOf(response.code()));
                if (response.isSuccessful()) {

                    if (response.code() == 200) {

                        listDeteksiData = response.body().getData();
                        Log.d("NINDI KI",listDeteksiData.get(i).getPertanyaan());
                        tvPertanyaan.setText(listDeteksiData.get(0).getPertanyaan());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListDeteksi> call, Throwable t) {
                Log.d("NINDI KI ERROR", String.valueOf(t));
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
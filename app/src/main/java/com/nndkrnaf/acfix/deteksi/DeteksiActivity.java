package com.nndkrnaf.acfix.deteksi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.Collections2;
import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.deteksi.model.ListDeteksi;
import com.nndkrnaf.acfix.deteksi.model.ListDeteksiData;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeteksiActivity extends AppCompatActivity {

    RequestInterface requestInterface;
    TextView tvPertanyaan, tvNext, tvBack;
    Button btnYa, btnTidak;
    ImageView imgAsk;

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

    }

    private void getDeteksi(String Id_Gejala){
        requestInterface.getDeteksi(Id_Gejala).enqueue(new Callback<ListDeteksi>() {
            @Override
            public void onResponse(Call<ListDeteksi> call, Response<ListDeteksi> response) {
                if (response.isSuccessful()) {
                    if(response.code()==200){
                         List<ListDeteksiData> listDeteksiData =response.body().getData();
                        Collection<ListDeteksiData> males = Collections2.filter(listDeteksiData, listDeteksiData1 -> listDeteksiData1.getIdDeteksi().equals(listDeteksiData.get(0));

                    }
                }
            }

            @Override
            public void onFailure(Call<ListDeteksi> call, Throwable t) {

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
package com.nndkrnaf.acfix.rules.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.gejala.model.ListGejala;
import com.nndkrnaf.acfix.hasil_deteksi.model.AddHasilDeteksi;
import com.nndkrnaf.acfix.login.sp.SharedPrefManager;
import com.nndkrnaf.acfix.rules.adapter.RuleAdapter;
import com.nndkrnaf.acfix.rules.interfaces.RuleView;
import com.nndkrnaf.acfix.rules.model.RuleResponse;
import com.nndkrnaf.acfix.rules.presenter.RulePresenter;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.ArrayList;
import java.util.List;

public class RuleActivity extends AppCompatActivity implements RuleView {
    RecyclerView rv;
    Button btSubmit,btClear;
    RuleAdapter adapter;
    RulePresenter presenter;
    String namaGejala = "";
    private SharedPrefManager sharedPrefManager;
    ArrayList<String> idGejalaList = new ArrayList<>();
    ArrayList<String> namaGejalaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        rv = findViewById(R.id.rvListGejala);
        btSubmit = findViewById(R.id.btSubmitRule);
        btClear = findViewById(R.id.btClearRule);
        initPresenter();
        sharedPrefManager = new SharedPrefManager(this);
    }

    private void initPresenter() {
        RequestInterface requestInterface = ApiClient.getApiClient().create(RequestInterface.class);
        presenter = new RulePresenter(requestInterface, this);
        presenter.getGejala();
    }

    private void submitRule() {

       for(int i=0;i<idGejalaList.size();i++){
           Log.d("NIDIXX", "submitRule: "+idGejalaList.get(i));
       }
        presenter.submitRules(idGejalaList);

    }

    @Override
    public void onSuccess(RuleResponse body) {
        for (String nama:namaGejalaList){
            namaGejala += nama+", ";
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Hasil Deteksi").setMessage("Nama Kerusakan: \n" + body.getData().get(0).getNamaKerusakan() + "\n\n" + "Solusi: \n" + body.getData().get(0).getSolusi())
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d("NAMA GEJALA", namaGejala);
            presenter.sendDeteksi(sharedPrefManager.getId_User(),body.getData().get(0).getIdKerusakan(),namaGejala);
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onGetGejalaSuccess(ListGejala body) {
       initAdapter(body);
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //idGejalaList.clear();
                namaGejala="";
                initAdapter(body);

            }
        });
        btSubmit.setOnClickListener(v->{
            submitRule();
        });

    }

    private void initAdapter(ListGejala body) {
        rv.setItemViewCacheSize(body.getData().size());
        adapter = new RuleAdapter(body.getData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        idGejalaList = adapter.getIdGejalaList();
        namaGejalaList = adapter.getNamaGejalaList();


    }

    @Override
    public void onGetGejalaError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSendDeteksiSuccess(AddHasilDeteksi body) {
        namaGejala = "";
        Toast.makeText(getApplicationContext(), "Berhasil Menyimpan Deteksi", Toast.LENGTH_LONG).show();
        //finish();
    }

    @Override
    public void onSendDeteksiError(String toString) {
        Log.d("SEND DETEKSI ERROR", "onSendDeteksiError: "+ toString);
        Toast.makeText(getApplicationContext(), "Gagal Menyimpan Deteksi", Toast.LENGTH_LONG).show();
    }
}
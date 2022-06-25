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
    ArrayList<String> idGejalaList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        initPresenter();
        rv = findViewById(R.id.rvListGejala);
        btSubmit = findViewById(R.id.btSubmitRule);
        btClear = findViewById(R.id.btClearRule);


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
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Hasil Deteksi").setMessage("Nama Kerusakan: \n" + body.getData().get(0).getNamaKerusakan() + "\n\n" + "Solusi: \n" + body.getData().get(0).getSolusi())
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
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
                initAdapter(body);
            }
        });
        btSubmit.setOnClickListener(v->{
            submitRule();
        });

    }

    private void initAdapter(ListGejala body) {
        adapter = new RuleAdapter(body.getData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        idGejalaList = adapter.getIdGejalaList();
    }

    @Override
    public void onGetGejalaError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
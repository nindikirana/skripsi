package com.nndkrnaf.acfix.admin.kerusakan.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.kerusakan.activity.AdminKerusakanActivity;
import com.nndkrnaf.acfix.admin.kerusakan.model.UpdateAdminKerusakan;
import com.nndkrnaf.acfix.admin.leveluser.activity.AdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.leveluser.model.UpdateAdminLevelUser;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertAdminKerusakanActivity extends AppCompatActivity {

    EditText edtNamaKerusakan;
    EditText edtSolusi;
    Button btnInsert;
    RequestInterface requestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_admin_kerusakan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtNamaKerusakan= findViewById(R.id.edtAdminKerusakanNamaKerusakan);
        edtSolusi= findViewById(R.id.edtAdminKerusakanSolusi);

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);

        btnInsert = findViewById(R.id.btnInsertKerusakan);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<UpdateAdminKerusakan> postKerusakanCall =
                        requestInterface.postAdminKerusakan(
                                edtNamaKerusakan.getText().toString(),
                                edtSolusi.getText().toString());

                postKerusakanCall.enqueue(new Callback<UpdateAdminKerusakan>() {
                    @Override
                    public void onResponse(Call<UpdateAdminKerusakan> call, Response<UpdateAdminKerusakan> response) {
                        AdminKerusakanActivity.AdminKerusakanActivity.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UpdateAdminKerusakan> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
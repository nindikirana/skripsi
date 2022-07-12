package com.nndkrnaf.acfix.admin.hasildeteksi.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.hasildeteksi.activity.AdminHasilDeteksiActivity;
import com.nndkrnaf.acfix.admin.hasildeteksi.adapter.UserSpinnerAdapter;
import com.nndkrnaf.acfix.admin.hasildeteksi.model.UpdateAdminHasilDeteksi;
import com.nndkrnaf.acfix.admin.kerusakan.model.ListAdminKerusakan;
import com.nndkrnaf.acfix.admin.kerusakan.model.ListAdminKerusakanData;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUser;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUserData;
import com.nndkrnaf.acfix.admin.pengetahuan.adapter.GejalaSpinnerAdapter;
import com.nndkrnaf.acfix.admin.pengetahuan.adapter.KerusakanSpinnerAdapter;
import com.nndkrnaf.acfix.admin.user.activity.AdminUserActivity;
import com.nndkrnaf.acfix.admin.user.adapter.LevelUserSpinnerAdapter;
import com.nndkrnaf.acfix.admin.user.model.ListAdminUser;
import com.nndkrnaf.acfix.admin.user.model.ListAdminUserData;
import com.nndkrnaf.acfix.admin.user.model.UpdateAdminUser;
import com.nndkrnaf.acfix.utils.ApiClient;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertAdminHasilDeteksiActivity extends AppCompatActivity {

    AppCompatSpinner spIdUser;
    AppCompatSpinner spIdKerusakan;
    EditText edtNamaGejala;
    Button btnInsert;
    RequestInterface requestInterface;

    private String selectedUser;
    private UserSpinnerAdapter spUserAdapter;
    private String currentIdUser;

    private String selectedKerusakan;
    private KerusakanSpinnerAdapter spKerusakanAdapter;
    private String currentIdKerusakan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_admin_hasil_deteksi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        spIdUser= findViewById(R.id.spIdUser);
        spIdKerusakan= findViewById(R.id.spIdKerusakan);
        edtNamaGejala= findViewById(R.id.edtAdminHasilDeteksiNamaGejala);

        requestInterface = ApiClient.getApiClient().create(RequestInterface.class);

        requestInterface.getUser().enqueue(new Callback<ListAdminUser>() {
            @Override
            public void onResponse(Call<ListAdminUser> call, Response<ListAdminUser> response) {
                if (response.isSuccessful()) {
                    if (response.body().isStatus()) {
                        setSpinnerIdUserData(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListAdminUser> call, Throwable t) {

            }
        });

        requestInterface.getAdminKerusakan().enqueue(new Callback<ListAdminKerusakan>() {
            @Override
            public void onResponse(Call<ListAdminKerusakan> call, Response<ListAdminKerusakan> response) {
                if (response.isSuccessful()) {
                    if (response.body().isStatus()) {
                        setSpinnerIdKerusakanData(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListAdminKerusakan> call, Throwable t) {

            }
        });

        btnInsert = findViewById(R.id.btnInsertDeteksi);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PARAMETER HASIL DETEKSI WOI", selectedUser + selectedKerusakan + edtNamaGejala);
                Call<UpdateAdminHasilDeteksi> postAdminHasilDeteksiCall =
                        requestInterface.postAdminHasilDeteksi(
                                selectedUser,
                                selectedKerusakan,
                                edtNamaGejala.getText().toString()
                        );

                postAdminHasilDeteksiCall.enqueue(new Callback<UpdateAdminHasilDeteksi>() {
                    @Override
                    public void onResponse(Call<UpdateAdminHasilDeteksi> call, Response<UpdateAdminHasilDeteksi> response) {
                        AdminHasilDeteksiActivity.adminHasilDeteksiActivity.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UpdateAdminHasilDeteksi> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setSpinnerIdUserData(List<ListAdminUserData> data) {
        List<String> stringData = new ArrayList<>();
        int position = 0;
        for (ListAdminUserData UserData : data) {
            stringData.add(UserData.getIdUser());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                stringData);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spIdUser.setAdapter(adapter);

        spIdUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUser = stringData.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spIdUser.setSelection(position);
    }

    private void setSpinnerIdKerusakanData(List<ListAdminKerusakanData> data) {
        List<String> stringData = new ArrayList<>();
        int position = 0;
        for (ListAdminKerusakanData kerusakanData : data) {
            stringData.add(kerusakanData.getIdKerusakan());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                stringData);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spIdKerusakan.setAdapter(adapter);

        spIdKerusakan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUser = stringData.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spIdKerusakan.setSelection(position);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;

    }
}
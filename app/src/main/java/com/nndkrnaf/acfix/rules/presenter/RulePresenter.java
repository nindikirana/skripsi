package com.nndkrnaf.acfix.rules.presenter;

import com.nndkrnaf.acfix.gejala.model.ListGejala;
import com.nndkrnaf.acfix.hasil_deteksi.model.AddHasilDeteksi;
import com.nndkrnaf.acfix.rules.interfaces.RuleView;
import com.nndkrnaf.acfix.rules.model.RuleResponse;
import com.nndkrnaf.acfix.utils.RequestInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RulePresenter {
    RequestInterface client;
    RuleView view;

    public RulePresenter(RequestInterface client, RuleView view) {
        this.client = client;
        this.view = view;
    }
    public void getGejala(){
        client.getGejala("").enqueue(new Callback<ListGejala>() {
            @Override
            public void onResponse(Call<ListGejala> call, Response<ListGejala> response) {
                if (response.isSuccessful()){
                    if (response.code()==200){
                        view.onGetGejalaSuccess(response.body());
                    }else{
                        view.onGetGejalaError(response.message());
                    }
                }else{
                    view.onGetGejalaError(response.message());

                }
            }

            @Override
            public void onFailure(Call<ListGejala> call, Throwable t) {
                view.onGetGejalaError(String.valueOf(t));

            }
        });
    }

    public void submitRules(List<String> listIdGejala) {
        client.sendRules(listIdGejala).enqueue(new Callback<RuleResponse>() {
            @Override
            public void onResponse(Call<RuleResponse> call, Response<RuleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        view.onSuccess(response.body());
                    } else {
                        view.onError(response.message());
                    }
                } else {
                    view.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<RuleResponse> call, Throwable t) {
                view.onError(String.valueOf(t));
            }
        });
    }

    public void sendDeteksi(String idUser, String idKerusakan, String namaGejala){
        client.createHasilDeteksi(idUser,idKerusakan, namaGejala).enqueue(new Callback<AddHasilDeteksi>() {
            @Override
            public void onResponse(Call<AddHasilDeteksi> call, Response<AddHasilDeteksi> response) {
                if (response.isSuccessful()){
                    if(response.code()==201){
                        view.onSendDeteksiSuccess(response.body());
                    }else{
                        view.onSendDeteksiError(response.message());

                    }
                }
            }

            @Override
            public void onFailure(Call<AddHasilDeteksi> call, Throwable t) {
                view.onSendDeteksiError(t.toString());
            }
        });
    }
}

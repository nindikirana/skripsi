package com.nndkrnaf.acfix.rules.interfaces;

import com.nndkrnaf.acfix.gejala.model.ListGejala;
import com.nndkrnaf.acfix.hasil_deteksi.model.AddHasilDeteksi;
import com.nndkrnaf.acfix.rules.model.RuleResponse;

public interface RuleView {
    void onSuccess(RuleResponse body);

    void onError(String message);

    void onGetGejalaSuccess(ListGejala body);

    void onGetGejalaError(String message);

    void onSendDeteksiSuccess(AddHasilDeteksi body);

    void onSendDeteksiError(String toString);
}


package com.nndkrnaf.acfix.admin.hasildeteksi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nndkrnaf.acfix.R;

import java.util.ArrayList;
import java.util.List;

public class KerusakanSpinnerAdapter extends ArrayAdapter<String> {

    private List<String> data;
    private Context context;
    private int resource;

    public KerusakanSpinnerAdapter(@NonNull Context context, int resource, List<String> stringData) {
        super(context, resource);

        this.context = context;
        this.resource = resource;

        data = new ArrayList<>();

        data.addAll(stringData);
    }

    @Override
    public void addAll(String... items) {
        super.addAll(items);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView mTextSpinnerItem = v.findViewById(R.id.tv_list);

        mTextSpinnerItem.setText(data.get(position));

        return v;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }
}


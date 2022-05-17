package com.nndkrnaf.acfix.gejala.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.pengetahuan.activity.DetailGejalaActivity;
import com.nndkrnaf.acfix.gejala.model.ListGejalaData;

import java.util.List;

public class GejalaAdapter extends RecyclerView.Adapter<GejalaAdapter.MyViewHolder> {
    List<ListGejalaData> mGejalaList;

    public GejalaAdapter(List<ListGejalaData> GejalaList) {
        mGejalaList = GejalaList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gejala_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mTextViewNama_Gejala.setText(mGejalaList.get(position).getNamaGejala());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailGejalaActivity.class);
                mIntent.putExtra("Id_Gejala", mGejalaList.get(position).getIdGejala());
                mIntent.putExtra("Nama_Gejala", mGejalaList.get(position).getNamaGejala());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGejalaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNama_Gejala;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNama_Gejala = (TextView) itemView.findViewById(R.id.tvNama_Gejala);
        }
    }
}

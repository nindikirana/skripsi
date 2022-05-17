package com.nndkrnaf.acfix.pengetahuan.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.pengetahuan.model.ListPengetahuanData;

import java.util.List;

public class PengetahuanKerusakanAdapter extends RecyclerView.Adapter<PengetahuanKerusakanAdapter.MyViewHolder> {
    List<ListPengetahuanData> mPengetahuanList;

    public PengetahuanKerusakanAdapter(List<ListPengetahuanData> PengetahuanList) {
        mPengetahuanList = PengetahuanList;
    }


    @Override
    public PengetahuanKerusakanAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pengetahuan_kerusakan_list, parent, false);
        PengetahuanKerusakanAdapter.MyViewHolder myViewHolder = new PengetahuanKerusakanAdapter.MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(PengetahuanKerusakanAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.mTextViewNama_Gejala.setText( "-  " + mPengetahuanList.get(position).getNamaGejala());
    }

    @Override
    public int getItemCount() {
        return mPengetahuanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNama_Gejala;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNama_Gejala = (TextView) itemView.findViewById(R.id.tvNama_Gejala);
        }
    }
}


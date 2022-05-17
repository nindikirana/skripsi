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

public class PengetahuanGejalaAdapter extends RecyclerView.Adapter<PengetahuanGejalaAdapter.MyViewHolder> {
    List<ListPengetahuanData> mPengetahuanList;

    public PengetahuanGejalaAdapter(List<ListPengetahuanData> PengetahuanList) {
        mPengetahuanList = PengetahuanList;
    }


    @Override
    public PengetahuanGejalaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pengetahuan_gejala_list, parent, false);
        PengetahuanGejalaAdapter.MyViewHolder myViewHolder = new PengetahuanGejalaAdapter.MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(PengetahuanGejalaAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

          holder.mTextViewNama_Kerusakan.setText(  mPengetahuanList.get(position).getNamaKerusakan());
          holder.mTextViewKerusakan.setText("Kerusakan " + (position+1) +"\n");
          holder.mTextViewNama_Solusi.setText(  mPengetahuanList.get(position).getSolusi());
          holder.mTextViewSolusi.setText("Solusi " +"\n");
    }

    @Override
    public int getItemCount() {
        return mPengetahuanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewKerusakan,mTextViewSolusi, mTextViewNama_Kerusakan, mTextViewNama_Solusi;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewKerusakan= (TextView) itemView.findViewById(R.id.tvKerusakan);
            mTextViewNama_Kerusakan = (TextView) itemView.findViewById(R.id.tvNama_Kerusakan);
            mTextViewSolusi = (TextView) itemView.findViewById(R.id.tvSolusi);
            mTextViewNama_Solusi = (TextView) itemView.findViewById(R.id.tvNama_Solusi);
        }
    }
}

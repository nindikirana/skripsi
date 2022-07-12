package com.nndkrnaf.acfix.hasil_deteksi.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.gejala.adapter.GejalaAdapter;
import com.nndkrnaf.acfix.gejala.model.ListGejalaData;
import com.nndkrnaf.acfix.hasil_deteksi.activity.DetailHasilDeteksiActivity;
import com.nndkrnaf.acfix.hasil_deteksi.model.ListHasilDeteksiData;
import com.nndkrnaf.acfix.pengetahuan.activity.DetailGejalaActivity;

import java.util.List;

public class HasilDeteksiAdapter extends RecyclerView.Adapter<HasilDeteksiAdapter.MyViewHolder> {
    List<ListHasilDeteksiData> mHasilDeteksiList;

    public HasilDeteksiAdapter(List<ListHasilDeteksiData> HasilDeteksiList) {
        mHasilDeteksiList = HasilDeteksiList;
    }


    @Override
    public HasilDeteksiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hasil_deteksi_list, parent, false);
        HasilDeteksiAdapter.MyViewHolder myViewHolder = new HasilDeteksiAdapter.MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(HasilDeteksiAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mTextViewNama_Kerusakan.setText(mHasilDeteksiList.get(position).getNamaKerusakan());
        holder.mTextViewSolusi.setText(mHasilDeteksiList.get(position).getSolusi());
        holder.mTextViewNama_Gejala.setText(mHasilDeteksiList.get(position).getNamaGejala());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailHasilDeteksiActivity.class);
                mIntent.putExtra("Nama_Kerusakan", mHasilDeteksiList.get(position).getNamaKerusakan());
                mIntent.putExtra("Solusi", mHasilDeteksiList.get(position).getSolusi());
                mIntent.putExtra("Nama_Gejala", mHasilDeteksiList.get(position).getNamaGejala());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHasilDeteksiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNama_Kerusakan, mTextViewSolusi, mTextViewNama_Gejala;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNama_Kerusakan = (TextView) itemView.findViewById(R.id.tvNamaKerusakanHasilDeteksi);
            mTextViewSolusi = (TextView) itemView.findViewById(R.id.tvSolusiHasilDeteksi);
            mTextViewNama_Gejala = (TextView) itemView.findViewById(R.id.tvNamaGejalaHasilDeteksi);
        }
    }
}


package com.nndkrnaf.acfix.kerusakan.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.pengetahuan.activity.DetailKerusakanActivity;
import com.nndkrnaf.acfix.kerusakan.model.ListKerusakanData;

import java.util.List;

public class KerusakanAdapter extends RecyclerView.Adapter<KerusakanAdapter.MyViewHolder> {
    List<ListKerusakanData> mKerusakanList;

    public KerusakanAdapter(List<ListKerusakanData> KerusakanList) {
        mKerusakanList = KerusakanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kerusakan_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(KerusakanAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mTextViewNama_Kerusakan.setText(mKerusakanList.get(position).getNamaKerusakan());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailKerusakanActivity.class);
                mIntent.putExtra("Id_Kerusakan", mKerusakanList.get(position).getIdKerusakan());
                mIntent.putExtra("Nama_Kerusakan", mKerusakanList.get(position).getNamaKerusakan());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKerusakanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNama_Kerusakan;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewNama_Kerusakan = (TextView) itemView.findViewById(R.id.tvNama_Kerusakan);
        }
    }
}

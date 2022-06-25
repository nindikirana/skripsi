package com.nndkrnaf.acfix.admin.kerusakan.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.kerusakan.crud.EditAdminKerusakanActivity;
import com.nndkrnaf.acfix.admin.kerusakan.model.ListAdminKerusakanData;

import java.util.List;

public class AdminKerusakanAdapter extends RecyclerView.Adapter<AdminKerusakanAdapter.MyViewHolder>{
    List<ListAdminKerusakanData> mAdminKerusakanList;

    public AdminKerusakanAdapter(List<ListAdminKerusakanData> KerusakanList) {
        mAdminKerusakanList = KerusakanList;
    }

    @Override
    public AdminKerusakanAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_kerusakan_list, parent, false);
        AdminKerusakanAdapter.MyViewHolder myViewHolder = new AdminKerusakanAdapter.MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(AdminKerusakanAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mTextViewId_Kerusakan.setText("Id_Kerusakan = " + mAdminKerusakanList.get(position).getIdKerusakan());
        holder.mTextViewNama_Kerusakan.setText("Nama_Kerusakan = " + mAdminKerusakanList.get(position).getNamaKerusakan());
        holder.mTextViewSolusi.setText("Solusi = " + mAdminKerusakanList.get(position).getSolusi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditAdminKerusakanActivity.class);
                mIntent.putExtra("Id_Kerusakan", mAdminKerusakanList.get(position).getIdKerusakan());
                mIntent.putExtra("Nama_Kerusakan", mAdminKerusakanList.get(position).getNamaKerusakan());
                mIntent.putExtra("Solusi", mAdminKerusakanList.get(position).getSolusi());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAdminKerusakanList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId_Kerusakan, mTextViewNama_Kerusakan, mTextViewSolusi;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId_Kerusakan = (TextView) itemView.findViewById(R.id.tvAdminIdKerusakan);
            mTextViewNama_Kerusakan = (TextView) itemView.findViewById(R.id.tvAdminNamaKerusakan);
            mTextViewSolusi = (TextView) itemView.findViewById(R.id.tvAdminSolusi);
        }
    }

}

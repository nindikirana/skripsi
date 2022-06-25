package com.nndkrnaf.acfix.admin.pengetahuan.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.pengetahuan.crud.EditAdminPengetahuanActivity;
import com.nndkrnaf.acfix.admin.pengetahuan.model.ListAdminPengetahuanData;

import java.util.List;

public class AdminPengetahuanAdapter extends RecyclerView.Adapter<AdminPengetahuanAdapter.MyViewHolder>{
    List<ListAdminPengetahuanData> mAdminPengetahuanList;

    public AdminPengetahuanAdapter(List<ListAdminPengetahuanData> AdminPengetahuanList) {
        mAdminPengetahuanList = AdminPengetahuanList;
    }

        @Override
        public AdminPengetahuanAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_pengetahuan_list, parent, false);
            AdminPengetahuanAdapter.MyViewHolder myViewHolder = new AdminPengetahuanAdapter.MyViewHolder(mView);
            return myViewHolder;
    }

    @Override
    public void onBindViewHolder(AdminPengetahuanAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mTextViewId_Pengetahuan.setText("Id_Pengetahuan = " + mAdminPengetahuanList.get(position).getIdPengetahuan());
        holder.mTextViewId_Kerusakan.setText("Id_Kerusakan = " + mAdminPengetahuanList.get(position).getIdKerusakan());
        holder.mTextViewId_Gejala.setText("Id_Gejala = " + mAdminPengetahuanList.get(position).getIdGejala());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditAdminPengetahuanActivity.class);
                mIntent.putExtra("Id_Pengetahuan", mAdminPengetahuanList.get(position).getIdPengetahuan());
                mIntent.putExtra("Id_Kerusakan", mAdminPengetahuanList.get(position).getIdKerusakan());
                mIntent.putExtra("Id_Gejala", mAdminPengetahuanList.get(position).getIdGejala());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAdminPengetahuanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId_Pengetahuan, mTextViewId_Kerusakan, mTextViewId_Gejala;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId_Pengetahuan = (TextView) itemView.findViewById(R.id.tvAdminPengetahuanIdPengetahuan);
            mTextViewId_Kerusakan = (TextView) itemView.findViewById(R.id.tvAdminPengetahuanIdKerusakan);
            mTextViewId_Gejala= (TextView) itemView.findViewById(R.id.tvAdminPengetahuanIdGejala);

        }
    }
}


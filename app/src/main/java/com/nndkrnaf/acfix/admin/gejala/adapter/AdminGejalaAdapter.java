package com.nndkrnaf.acfix.admin.gejala.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.gejala.crud.EditAdminGejalaActivity;
import com.nndkrnaf.acfix.admin.gejala.model.ListAdminGejalaData;

import java.util.List;

public class AdminGejalaAdapter extends RecyclerView.Adapter<AdminGejalaAdapter.MyViewHolder>{
    List<ListAdminGejalaData> mAdminGejalaList;

    public AdminGejalaAdapter(List<ListAdminGejalaData> AdminGejalaList) {
        mAdminGejalaList = AdminGejalaList;
    }

    @Override
    public AdminGejalaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_gejala_list, parent, false);
        AdminGejalaAdapter.MyViewHolder myViewHolder = new AdminGejalaAdapter.MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(AdminGejalaAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mTextViewId_Gejala.setText("Id_Gejala = " + mAdminGejalaList.get(position).getIdGejala());
        holder.mTextViewNama_Gejala.setText("Nama_Gejala = " + mAdminGejalaList.get(position).getNamaGejala());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditAdminGejalaActivity.class);
                mIntent.putExtra("Id_Gejala", mAdminGejalaList.get(position).getIdGejala());
                mIntent.putExtra("Nama_Gejala", mAdminGejalaList.get(position).getNamaGejala());
                view.getContext().startActivity(mIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mAdminGejalaList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId_Gejala, mTextViewNama_Gejala;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId_Gejala = (TextView) itemView.findViewById(R.id.tvAdminGejalaIdGejala);
            mTextViewNama_Gejala = (TextView) itemView.findViewById(R.id.tvAdminGejalaNamaGejala);
        }
    }
}

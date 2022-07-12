package com.nndkrnaf.acfix.admin.hasildeteksi.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.hasildeteksi.crud.EditAdminHasilDeteksiActivity;
import com.nndkrnaf.acfix.admin.hasildeteksi.model.ListAdminHasilDeteksiData;

import java.util.List;

public class AdminHasilDeteksiAdapter extends RecyclerView.Adapter<AdminHasilDeteksiAdapter.MyViewHolder> {
    List<ListAdminHasilDeteksiData> mAdminHasilDeteksiList;

    public AdminHasilDeteksiAdapter(List<ListAdminHasilDeteksiData> AdminHasilDeteksiList) {
        mAdminHasilDeteksiList = AdminHasilDeteksiList;
    }

    @Override
    public AdminHasilDeteksiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_hasil_deteksi_list, parent, false);
        AdminHasilDeteksiAdapter.MyViewHolder myViewHolder = new AdminHasilDeteksiAdapter.MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(AdminHasilDeteksiAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mTextViewId_Deteksi.setText("Id_Deteksi = " + mAdminHasilDeteksiList.get(position).getIdDeteksi());
        holder.mTextViewId_User.setText("Id_User = " + mAdminHasilDeteksiList.get(position).getIdUser());
        holder.mTextViewId_Kerusakan.setText("Id_Kerusakan = " + mAdminHasilDeteksiList.get(position).getIdKerusakan());
        holder.mTextViewNama_Gejala.setText("Nama_Gejala = " + mAdminHasilDeteksiList.get(position).getNamaGejala());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditAdminHasilDeteksiActivity.class);
                mIntent.putExtra("Id_Deteksi", mAdminHasilDeteksiList.get(position).getIdDeteksi());
                mIntent.putExtra("Id_User", mAdminHasilDeteksiList.get(position).getIdUser());
                mIntent.putExtra("Id_Kerusakan", mAdminHasilDeteksiList.get(position).getIdKerusakan());
                mIntent.putExtra("Nama_Gejala", mAdminHasilDeteksiList.get(position).getNamaGejala());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAdminHasilDeteksiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId_Deteksi, mTextViewId_User, mTextViewId_Kerusakan, mTextViewNama_Gejala;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId_Deteksi = (TextView) itemView.findViewById(R.id.tvAdminIdDeteksi);
            mTextViewId_User = (TextView) itemView.findViewById(R.id.tvAdminIdUser);
            mTextViewId_Kerusakan= (TextView) itemView.findViewById(R.id.tvAdminIdKerusakan);
            mTextViewNama_Gejala= (TextView) itemView.findViewById(R.id.tvAdminNamaGejala);

        }
    }
}




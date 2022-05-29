package com.nndkrnaf.acfix.admin.leveluser.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.leveluser.crud.EditAdminLevelUserActivity;
import com.nndkrnaf.acfix.admin.leveluser.model.ListAdminLevelUserData;

import java.util.List;

public class AdminLevelUserAdapter extends RecyclerView.Adapter<AdminLevelUserAdapter.MyViewHolder>{
    List<ListAdminLevelUserData> mAdminLevelUserList;

    public AdminLevelUserAdapter(List<ListAdminLevelUserData> LevelUserList) {
        mAdminLevelUserList = LevelUserList;
    }

    @Override
    public AdminLevelUserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_level_user_list, parent, false);
        AdminLevelUserAdapter.MyViewHolder myViewHolder = new AdminLevelUserAdapter.MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(AdminLevelUserAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mTextViewId_Level.setText("Id_Level = " + mAdminLevelUserList.get(position).getIdLevel());
        holder.mTextViewLevel.setText("Level = " + mAdminLevelUserList.get(position).getLevel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditAdminLevelUserActivity.class);
                mIntent.putExtra("Id_Level", mAdminLevelUserList.get(position).getIdLevel());
                mIntent.putExtra("Level", mAdminLevelUserList.get(position).getLevel());
                view.getContext().startActivity(mIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mAdminLevelUserList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId_Level, mTextViewLevel;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId_Level = (TextView) itemView.findViewById(R.id.tvAdminIdLevel);
            mTextViewLevel = (TextView) itemView.findViewById(R.id.tvAdminLevel);
        }
    }
}

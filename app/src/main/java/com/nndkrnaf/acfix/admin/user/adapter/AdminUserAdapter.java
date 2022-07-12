package com.nndkrnaf.acfix.admin.user.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.admin.user.crud.EditAdminUserActivity;
import com.nndkrnaf.acfix.admin.user.model.ListAdminUserData;

import java.util.List;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.MyViewHolder>{
    List<ListAdminUserData> mAdminUserList;

    public AdminUserAdapter(List<ListAdminUserData> AdminUserList) {
        mAdminUserList = AdminUserList;
    }

    @Override
    public AdminUserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_user_list, parent, false);
        AdminUserAdapter.MyViewHolder myViewHolder = new AdminUserAdapter.MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(AdminUserAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mTextViewId_User.setText("Id_User = " + mAdminUserList.get(position).getIdUser());
        holder.mTextViewId_Level.setText("Id_Level = " + mAdminUserList.get(position).getIdLevel());
        holder.mTextViewUsername.setText("Username = " + mAdminUserList.get(position).getUsername());
        holder.mTextViewEmail.setText("Email = " + mAdminUserList.get(position).getEmail());
        holder.mTextViewPassword.setText("Password = " + mAdminUserList.get(position).getPassword());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditAdminUserActivity.class);
                mIntent.putExtra("Id_User", mAdminUserList.get(position).getIdUser());
                mIntent.putExtra("Id_Level", mAdminUserList.get(position).getIdLevel());
                mIntent.putExtra("Username", mAdminUserList.get(position).getUsername());
                mIntent.putExtra("Email", mAdminUserList.get(position).getEmail());
                mIntent.putExtra("Password", mAdminUserList.get(position).getPassword());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAdminUserList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId_User, mTextViewId_Level, mTextViewUsername, mTextViewEmail, mTextViewPassword;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewId_User = (TextView) itemView.findViewById(R.id.tvAdminUserIdUser);
            mTextViewId_Level = (TextView) itemView.findViewById(R.id.tvAdminUserIdLevel);
            mTextViewUsername= (TextView) itemView.findViewById(R.id.tvAdminUserUsername);
            mTextViewEmail= (TextView) itemView.findViewById(R.id.tvAdminUserEmail);
            mTextViewPassword= (TextView) itemView.findViewById(R.id.tvAdminUserPassword);

        }
    }
}


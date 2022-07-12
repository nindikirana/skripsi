package com.nndkrnaf.acfix.rules.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nndkrnaf.acfix.R;
import com.nndkrnaf.acfix.gejala.model.ListGejalaData;

import java.util.ArrayList;
import java.util.List;

public class RuleAdapter extends RecyclerView.Adapter<RuleAdapter.ViewHolder> {
    List<ListGejalaData> itemList;
    ArrayList<String> idGejalaList = new ArrayList<>();
    ArrayList<String> namaGejalaList = new ArrayList<>();


    public RuleAdapter(List<ListGejalaData> itemList) {
        this.itemList = itemList;
    }

    public ArrayList<String> getIdGejalaList() {
        return idGejalaList;
    }
    public ArrayList<String> getNamaGejalaList() {
        return namaGejalaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gejala, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemNo.setText(String.valueOf(position+1));
        holder.itemText.setText(itemList.get(position).getNamaGejala());
        holder.itemCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    idGejalaList.add(itemList.get(holder.getAdapterPosition()).getIdGejala());
                    namaGejalaList.add(itemList.get(holder.getAdapterPosition()).getNamaGejala());
                } else {
                    String idGejala = itemList.get(holder.getAdapterPosition()).getIdGejala();
                    String namaGejala = itemList.get(holder.getAdapterPosition()).getNamaGejala();
                    idGejalaList.remove(idGejala);
                    namaGejalaList.remove(namaGejala);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox itemCheck;
        TextView itemText,itemNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCheck = itemView.findViewById(R.id.checkItemGejala);
            itemText = itemView.findViewById(R.id.tvItemGejala);
            itemNo = itemView.findViewById(R.id.tvNoItemGejala);
        }
    }
}

package com.example.docsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docsapp.Models.LabTestMo;
import com.example.docsapp.Models.PackageInfoMo;
import com.example.docsapp.R;

import java.util.List;

public class PackageInfoAd extends RecyclerView.Adapter<PackageInfoAd.MyViewHolder> {
    public Context mContext;
    public List<PackageInfoMo> packageInfoMoList;

    public PackageInfoAd(Context mContext, List<PackageInfoMo> packageInfoMoList) {
        this.mContext = mContext;
        this.packageInfoMoList = packageInfoMoList;
    }
    @NonNull
    @Override
    public PackageInfoAd.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_package,parent,false);

        return new PackageInfoAd.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageInfoAd.MyViewHolder holder, int position) {


        PackageInfoMo packageInfoMo = packageInfoMoList.get(position);
        holder.info.setText(packageInfoMo.getInfo());
        holder.type.setText(packageInfoMo.getType());
    }

    @Override
    public int getItemCount() {
        return packageInfoMoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView type,info;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.text_type);
            info = itemView.findViewById(R.id.text_info);
        }
    }
}

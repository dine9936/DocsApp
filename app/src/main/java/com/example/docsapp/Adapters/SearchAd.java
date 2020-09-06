package com.example.docsapp.Adapters;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.docsapp.Models.CitiesMo;
import com.example.docsapp.R;


import java.util.ArrayList;
import java.util.List;

public class SearchAd extends RecyclerView.Adapter<SearchAd.MyViewHolder> {

    List<CitiesMo> citiesMoList;
   Context context;

    public SearchAd(List<CitiesMo> citiesMoList, Context context) {
        this.citiesMoList = citiesMoList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_other_cities,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.itemname.setText(citiesMoList.get(position).getCityName());


      }

    @Override
    public int getItemCount() {
        return citiesMoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView itemname;

        public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        itemname = itemView.findViewById(R.id.name);


        }
}
}

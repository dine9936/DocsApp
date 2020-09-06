package com.example.docsapp.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.docsapp.Models.CitiesMo;
import com.example.docsapp.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class CitiesAd extends RecyclerView.Adapter<CitiesAd.ViewHolder>{
    public Context mContext;
    public List<CitiesMo> citiesMoList;

    public CitiesAd(Context mContext, List<CitiesMo> citiesMoList) {
        this.mContext = mContext;
        this.citiesMoList = citiesMoList;
    }

    @NonNull
    @Override
    public CitiesAd.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_cities,parent,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CitiesAd.ViewHolder holder, int position) {

        final CitiesMo citiesMo = citiesMoList.get(position);
        holder.cityname.setText(citiesMo.getCityName());
    }

    @Override
    public int getItemCount() {
        return citiesMoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cityname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityname = itemView.findViewById(R.id.city_name);
        }
    }
}


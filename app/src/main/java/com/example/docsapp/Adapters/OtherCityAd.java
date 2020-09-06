package com.example.docsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docsapp.Models.CitiesMo;
import com.example.docsapp.R;

import java.util.List;

public class OtherCityAd extends RecyclerView.Adapter<OtherCityAd.ViewHolder> {
    public Context mContext;
    public List<CitiesMo> citiesMoList;

    public OtherCityAd(Context mContext, List<CitiesMo> citiesMoList) {
        this.mContext = mContext;
        this.citiesMoList = citiesMoList;
    }

    @NonNull
    @Override
    public OtherCityAd.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_other_cities,parent,false);
        return new OtherCityAd.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OtherCityAd.ViewHolder holder, int position) {

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
            cityname = itemView.findViewById(R.id.name);
        }
    }
}



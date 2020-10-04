package com.example.docsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docsapp.Models.CouponMo;
import com.example.docsapp.R;

import java.net.ContentHandler;
import java.util.List;

public class CouponAd extends RecyclerView.Adapter<CouponAd.MyViewHolder> {
    public Context mContext;
    public List<CouponMo>couponMoList;

    public CouponAd(Context mContext, List<CouponMo> couponMoList) {
        this.mContext = mContext;
        this.couponMoList = couponMoList;
    }

    @NonNull
    @Override
    public CouponAd.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_coupons,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponAd.MyViewHolder holder, int position) {

        CouponMo couponMo = couponMoList.get(position);
        holder.packagename.setText(couponMo.getDiscount()+"% "+"off on "+couponMo.getPackeagename());
    }

    @Override
    public int getItemCount() {
        return couponMoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView packagename;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            packagename = itemView.findViewById(R.id.text_package_name2);

        }
    }
}

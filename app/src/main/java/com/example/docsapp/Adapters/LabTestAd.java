package com.example.docsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.docsapp.Models.LabTestMo;
import com.example.docsapp.PackageBook;
import com.example.docsapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LabTestAd extends RecyclerView.Adapter<LabTestAd.MyViewHolder> {
    public Context mContext;
    public List<LabTestMo> labTestMoList;

    public LabTestAd(Context mContext, List<LabTestMo> labTestMoList) {
        this.mContext = mContext;
        this.labTestMoList = labTestMoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lab_test,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        LabTestMo labTestMo = labTestMoList.get(position);
        holder.name.setText(labTestMo.getPackagename());
        holder.price.setText("₹"+labTestMo.getTestprice());
        holder.mrp.setPaintFlags(holder.mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mrp.setText("₹"+labTestMo.getMrp());
        holder.test.setText(labTestMo.getNotest()+" Tests");
        Glide.with(holder.imageView.getContext()).load(labTestMo.getImage()).into(holder.imageView);
        if (labTestMo.getPopular().equals("0")){
            holder.popular.setVisibility(View.GONE);
        }
        else if (labTestMo.getPopular().equals("1")){
        holder.popular.setVisibility(View.VISIBLE);

        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(holder.linearLayout.getContext(), PackageBook.class);
                intent.putExtra("name",labTestMo.getPackagename());
                intent.putExtra("image",labTestMo.getImage());
                intent.putExtra("price",labTestMo.getTestprice());
                intent.putExtra("mrp",labTestMo.getMrp());
                intent.putExtra("test",labTestMo.getNotest());
                intent.putExtra("popular",labTestMo.getPopular());
                intent.putExtra("id",labTestMo.getId());
                holder.linearLayout.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return labTestMoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,test,mrp,price;
        ImageView popular;
        CircleImageView  imageView;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_package_name3);
            test = itemView.findViewById(R.id.text_total_tests);
            mrp = itemView.findViewById(R.id.text_mrp);
            price = itemView.findViewById(R.id.text_tesr_price);
            popular = itemView.findViewById(R.id.image_popular);

            imageView = itemView.findViewById(R.id.package_image);
            linearLayout = itemView.findViewById(R.id.ll_card_package);

        }
    }
}

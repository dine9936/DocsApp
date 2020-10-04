package com.example.docsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.docsapp.Models.MedicalRecordMo;
import com.example.docsapp.R;

import java.util.List;

public class MedicalRecordAd extends RecyclerView.Adapter<MedicalRecordAd.MyViewHolder> {
    public Context mContext;
    public List<MedicalRecordMo> medicalRecordMoList;

    public MedicalRecordAd(Context mContext, List<MedicalRecordMo> medicalRecordMoList) {
        this.mContext = mContext;
        this.medicalRecordMoList = medicalRecordMoList;
    }

    @NonNull
    @Override
    public MedicalRecordAd.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_consults, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalRecordAd.MyViewHolder holder, int position) {

        MedicalRecordMo medicalRecordMo = medicalRecordMoList.get(position);
        if (medicalRecordMo.getTypecard().equals("co")){
            holder.llco.setVisibility(View.VISIBLE);
            holder.lltest.setVisibility(View.GONE);

            holder.dateco.setText(medicalRecordMo.getDateco());
            holder.spec.setText(medicalRecordMo.getDocspec());
            holder.docname.setText(medicalRecordMo.getDocname());
            Glide.with(holder.imageView.getContext()).load(medicalRecordMo.getDocimage()).into(holder.imageView);
        }
        else if (medicalRecordMo.getTypecard().equals("test")){

            holder.lltest.setVisibility(View.VISIBLE);
            holder.llco.setVisibility(View.GONE);

            holder.datetest.setText(medicalRecordMo.getDatetest());
            holder.packegename.setText(medicalRecordMo.getPackkagename());
            holder.price.setText("₹"+medicalRecordMo.getPrice());
        }


    }

    @Override
    public int getItemCount() {
        return medicalRecordMoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView docname,dateco,datetest,packegename,price,spec;
        LinearLayout llco,lltest;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            llco = itemView.findViewById(R.id.ll_consults);
            lltest = itemView.findViewById(R.id.ll_test);

            docname = itemView.findViewById(R.id.text_doc_name);
            dateco = itemView.findViewById(R.id.text_con_date);
            datetest = itemView.findViewById(R.id.text_test_date);
            packegename = itemView.findViewById(R.id.text_packege_name);
            price = itemView.findViewById(R.id.text_package_price);
            spec = itemView.findViewById(R.id.text_doc_spec);
            imageView = itemView.findViewById(R.id.doc_image);
        }
    }
}

package com.example.docsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docsapp.Models.RapidxMo;
import com.example.docsapp.Models.ThyrocareMo;
import com.example.docsapp.R;
import com.example.docsapp.Rapid;
import com.example.docsapp.ThyrocareChild;

import java.util.List;

public class RapidxAd extends RecyclerView.Adapter<RapidxAd.ViewHolder> {
    public Context mContext;
    public List<RapidxMo> thyrocareMoList;

    public RapidxAd(Context mContext, List<RapidxMo> thyrocareMoList) {
        this.mContext = mContext;
        this.thyrocareMoList = thyrocareMoList;
    }

    @NonNull
    @Override
    public RapidxAd.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lab_test,parent,false);

        return new RapidxAd.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RapidxAd.ViewHolder holder, int position) {
        final RapidxMo thyrocareMo = thyrocareMoList.get(position);
        holder.name.setText(thyrocareMo.getName());
        holder.number.setText(thyrocareMo.getNumber());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Rapid.class);
                intent.putExtra("image",thyrocareMo.getImage());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return thyrocareMoList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,number;
        LinearLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ll = itemView.findViewById(R.id.ll_card);
            name = itemView.findViewById(R.id.text);
            number = itemView.findViewById(R.id.test);
        }
    }
}

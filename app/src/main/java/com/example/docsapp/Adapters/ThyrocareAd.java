package com.example.docsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docsapp.Models.CitiesMo;
import com.example.docsapp.Models.ThyrocareMo;
import com.example.docsapp.R;
import com.example.docsapp.ThyrocareChild;

import java.util.List;

public class ThyrocareAd extends RecyclerView.Adapter<ThyrocareAd.ViewHolder>{
    public Context mContext;
    public List<ThyrocareMo> thyrocareMoList;

    public ThyrocareAd(Context mContext, List<ThyrocareMo> thyrocareMos) {
        this.mContext = mContext;
        this.thyrocareMoList = thyrocareMos;
    }

    @NonNull
    @Override
    public ThyrocareAd.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_thyrocare,parent,false);
        return new ThyrocareAd.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThyrocareAd.ViewHolder holder, int position) {
        final ThyrocareMo thyrocareMo = thyrocareMoList.get(position);
        holder.name.setText(thyrocareMo.getName());
        holder.number.setText(thyrocareMo.getNumber());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, ThyrocareChild.class);
                intent.putExtra("image",thyrocareMo.getImage());
                intent.putExtra("price",thyrocareMo.getPrice());
                intent.putExtra("test",thyrocareMo.getNumber());
                intent.putExtra("name",thyrocareMo.getName());
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

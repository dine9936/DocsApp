package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.docsapp.Adapters.CouponAd;
import com.example.docsapp.Adapters.MedicalRecordAd;
import com.example.docsapp.Models.CouponMo;
import com.example.docsapp.Models.MedicalRecordMo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CouponsAct extends AppCompatActivity {
Toolbar toolbar;
    DatabaseReference reference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CouponMo> medicalRecordMoList;

    private LinearLayout linearLayout;
    DialogLoading loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);

        loading = new DialogLoading();

        linearLayout = findViewById(R.id.ll_no_coupons);
        reference = FirebaseDatabase.getInstance().getReference("Coupons");

        toolbar = findViewById(R.id.toolbar_coupons);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.recycler_coupons);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        medicalRecordMoList = new ArrayList<>();
        adapter = new CouponAd(this, medicalRecordMoList);
        recyclerView.setAdapter(adapter);




        readPost();
    }
    private void readPost() {

        loading.show(getSupportFragmentManager(),"hello");
        reference.keepSynced(true);



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    loading.dismiss();
                    recyclerView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);

                    medicalRecordMoList = new ArrayList<>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        medicalRecordMoList.add(dataSnapshot1.getValue(CouponMo.class));
                    }
                    adapter = new CouponAd(CouponsAct.this, medicalRecordMoList);
                    recyclerView.setAdapter(adapter);

                } else {
                    loading.dismiss();
                    recyclerView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }

        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
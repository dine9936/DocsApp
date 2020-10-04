package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.docsapp.Adapters.LabTestAd;
import com.example.docsapp.Models.LabTestMo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Gd extends AppCompatActivity {
Toolbar toolbar;
    DatabaseReference reference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LabTestMo> medicalRecordMoList;
    DialogLoading loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gd_act);

        loading = new DialogLoading();

        reference = FirebaseDatabase.getInstance().getReference("LabTests");
        toolbar = findViewById(R.id.toolbar_book);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.recycler_book);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        medicalRecordMoList = new ArrayList<>();
        adapter = new LabTestAd(this, medicalRecordMoList);
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

                    medicalRecordMoList = new ArrayList<>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        medicalRecordMoList.add(dataSnapshot1.getValue(LabTestMo.class));
                    }
                    adapter = new LabTestAd(Gd.this, medicalRecordMoList);
                    recyclerView.setAdapter(adapter);

                } else {
                    loading.dismiss();

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
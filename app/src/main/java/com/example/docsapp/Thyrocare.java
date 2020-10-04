package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.docsapp.Adapters.LabTestAd;
import com.example.docsapp.Adapters.ThyrocareAd;
import com.example.docsapp.Models.LabTestMo;
import com.example.docsapp.Models.ThyrocareMo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Thyrocare extends AppCompatActivity {
    Toolbar toolbar;
    DatabaseReference reference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ThyrocareMo> medicalRecordMoList;
    DialogLoading loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thyrocare);

        loading = new DialogLoading();

        reference = FirebaseDatabase.getInstance().getReference("Thyrocare");
        toolbar = findViewById(R.id.toolbar_thyrocare);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.recycler_thyrocare);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        medicalRecordMoList = new ArrayList<>();
        adapter = new ThyrocareAd(this, medicalRecordMoList);
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
                        medicalRecordMoList.add(dataSnapshot1.getValue(ThyrocareMo.class));
                    }
                    adapter = new ThyrocareAd(Thyrocare.this, medicalRecordMoList);
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
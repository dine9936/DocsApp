package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.docsapp.Adapters.MedicalRecordAd;
import com.example.docsapp.Models.MedicalRecordMo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecordAct extends AppCompatActivity {
Toolbar toolbar;
DatabaseReference reference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<MedicalRecordMo> medicalRecordMoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        reference = FirebaseDatabase.getInstance().getReference("Records");
        toolbar = findViewById(R.id.toolbar_record);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());


        recyclerView = findViewById(R.id.recycler_record);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        medicalRecordMoList = new ArrayList<>();
        adapter = new MedicalRecordAd(this, medicalRecordMoList);
        recyclerView.setAdapter(adapter);




        readPost();

    }



    private void readPost() {

        reference.keepSynced(true);



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        medicalRecordMoList = new ArrayList<>();
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            medicalRecordMoList.add(dataSnapshot1.getValue(MedicalRecordMo.class));
                        }
                        adapter = new MedicalRecordAd(RecordAct.this, medicalRecordMoList);
                        recyclerView.setAdapter(adapter);

                    } else {

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
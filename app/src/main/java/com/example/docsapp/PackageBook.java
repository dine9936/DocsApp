package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.docsapp.Adapters.MedicalRecordAd;
import com.example.docsapp.Adapters.PackageInfoAd;
import com.example.docsapp.Models.MedicalRecordMo;
import com.example.docsapp.Models.PackageInfoMo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PackageBook extends AppCompatActivity {

    CircleImageView circleImageView;
    Button buttonView,buttonBook;

    Toolbar toolbar;
    DatabaseReference reference;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<PackageInfoMo> packageInfoMoList;
    TextView mrpText,priceText,tests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_book);


        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");

        String price = getIntent().getStringExtra("price");
        String test = getIntent().getStringExtra("test");
        String popular = getIntent().getStringExtra("popular");
        String mrp = getIntent().getStringExtra("mrp");
        String id = getIntent().getStringExtra("id");

        reference = FirebaseDatabase.getInstance().getReference("Package").child(id);

        toolbar = findViewById(R.id.toolbar_book_package);

        tests = findViewById(R.id.test);
        tests.setText(test+" Tests");
        priceText = findViewById(R.id.price_text);
        priceText.setText("₹"+price);

       mrpText = findViewById(R.id.mrp);
       mrpText.setPaintFlags(mrpText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        mrpText.setText("₹"+mrp);
       buttonBook = findViewById(R.id.button_book_now);
       buttonBook.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               DialogBook dialogBook = new DialogBook();
               dialogBook.show(getSupportFragmentManager(),"hello");
           }
       });
       buttonView = findViewById(R.id.button_view_all);
       circleImageView = findViewById(R.id.package_image);
        Glide.with(this).load(image).into(circleImageView);


        toolbar.setTitle(name);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        recyclerView = findViewById(R.id.recycler_book_package);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        packageInfoMoList = new ArrayList<>();
        adapter = new PackageInfoAd(this, packageInfoMoList);
        recyclerView.setAdapter(adapter);




        readPost();
    }

    private void readPost() {

        reference.keepSynced(true);



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    packageInfoMoList = new ArrayList<>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        packageInfoMoList.add(dataSnapshot1.getValue(PackageInfoMo.class));
                    }
                    adapter = new PackageInfoAd(PackageBook.this, packageInfoMoList);
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
package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docsapp.Adapters.CitiesAd;
import com.example.docsapp.Adapters.OtherCityAd;
import com.example.docsapp.Adapters.SearchAd;
import com.example.docsapp.Models.CitiesMo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectLoactionActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference, reference2;

    Context context;

    TextView textViewToolbarSearch;
    private LocationManager locationManager;

    private Button buttonPickMyCurrentLocation;

    private RecyclerView recyclerView, recyclerView2;
    private RecyclerView.Adapter cityAdapter, otherCityAdapter;
    private String provider;

    private List<CitiesMo> citiesMoList, citiesMoList2;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_loaction);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("cities");

        toolbar = findViewById(R.id.toolbar_select_location);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });






        recyclerView = findViewById(R.id.recycler_popular_city);
        recyclerView2 = findViewById(R.id.recycler_other_city);

        textViewToolbarSearch = findViewById(R.id.toolbar_text_select_location);
        textViewToolbarSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectLoactionActivity.this, SearchLocationActivity.class);
                startActivity(intent);
            }
        });

        GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 3);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        //recyclerView.addItemDecoration(new SpacesItemDecoration(8));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        citiesMoList = new ArrayList<>();
        citiesMoList2 = new ArrayList<>();

        cityAdapter = new CitiesAd(context, citiesMoList);
        otherCityAdapter = new OtherCityAd(context, citiesMoList2);


        recyclerView.setAdapter(cityAdapter);
        recyclerView2.setAdapter(otherCityAdapter);


buttonPickMyCurrentLocation = findViewById(R.id.pick_my_current_location);
        buttonPickMyCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        readPost();
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

    private void readPost() {

        reference.keepSynced(true);
        reference.child("cities").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    citiesMoList = new ArrayList<>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        citiesMoList.add(dataSnapshot1.getValue(CitiesMo.class));
                    }
                    cityAdapter = new SearchAd(citiesMoList, getBaseContext());
                    otherCityAdapter = new SearchAd(citiesMoList, getBaseContext());
                    recyclerView.setAdapter(cityAdapter);
                    recyclerView2.setAdapter(otherCityAdapter);

                } else {

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getBaseContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }

        });

    }
}
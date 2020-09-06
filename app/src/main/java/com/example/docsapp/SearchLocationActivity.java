package com.example.docsapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docsapp.Adapters.SearchAd;
import com.example.docsapp.Models.CitiesMo;
import com.ferfalk.simplesearchview.SimpleSearchView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchLocationActivity extends AppCompatActivity {
    DatabaseReference reference;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter searchAd;
    private List<CitiesMo> citiesMoList;
    SimpleSearchView simpleSearchView;
    EditText editText;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);
        reference = FirebaseDatabase.getInstance().getReference();

        toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        citiesMoList = new ArrayList<>();
        searchAd = new SearchAd(citiesMoList, getBaseContext());

        simpleSearchView = findViewById(R.id.searchView);

        simpleSearchView.setOnQueryTextListener(new SimpleSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;

                searchFun(text);
                return false;
            }

            @Override
            public boolean onQueryTextCleared() {
                readPost();
                return false;
            }
        });
        simpleSearchView.setOnSearchViewListener(new SimpleSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                onBackPressed();
            }

            @Override
            public void onSearchViewShownAnimation() {

            }

            @Override
            public void onSearchViewClosedAnimation() {

            }
        });


        recyclerView.setAdapter(searchAd);
        readPost();

    }

    private void searchFun(String newText) {
        ArrayList<CitiesMo> myList = new ArrayList<>();
        for (CitiesMo object : citiesMoList) {
            if (object.getCityName().toLowerCase().contains(newText.toLowerCase())) {
                myList.add(object);
            }
        }
        SearchAd searchAd = new SearchAd(myList, getApplicationContext());
        recyclerView.setAdapter(searchAd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.nav_search);
        simpleSearchView.setMenuItem(item);
        simpleSearchView.showSearch(true);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (reference != null) {
            reference.child("cities").orderByChild("cityName").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        citiesMoList = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {

                            citiesMoList.add(ds.getValue(CitiesMo.class));
                        }
                        SearchAd searchAd = new SearchAd(citiesMoList, getBaseContext());
                        recyclerView.setAdapter(searchAd);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

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
                    searchAd = new SearchAd(citiesMoList, getBaseContext());
                    recyclerView.setAdapter(searchAd);

                } else {

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getBaseContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void onBackPressed() {
        if (simpleSearchView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

}

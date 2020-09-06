package com.example.docsapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.TestLooperManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.docsapp.Adapters.SliderAdapterExample;
import com.example.docsapp.Models.SliderItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    SliderView sliderView;
    private SliderAdapterExample adapter;
    List<SliderItem> sliderItemList;
    FirebaseDatabase database;
    DatabaseReference referenceSlider;
    ImageView imageView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.home_fragment, container, false);

       database = FirebaseDatabase.getInstance();

       imageView = view.findViewById(R.id.image_lab_test);
       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getActivity(), BookAtc.class);
               startActivity(intent);
           }
       });
        sliderItemList = new ArrayList<>();
        loadSliderImage(view);
       return view;
    }

    private void loadSliderImage(View view){
        referenceSlider = database.getReference().child("imageSlider");
        sliderItemList = new ArrayList<>();
        referenceSlider.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    sliderItemList.clear();
                    for (DataSnapshot post : snapshot.getChildren()) {
                        SliderItem sliderItem = post.getValue(SliderItem.class);


                        sliderItemList.add(sliderItem);
                        adapter.renewItems(sliderItemList);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        sliderView = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(getContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setIndicatorEnabled(true);
        sliderView.setIndicatorVisibility(true);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }


    @Override
    public void onStart() {
        super.onStart();

//        Document doc = null;
//        try {
//            doc = Jsoup.connect("http://en.wikipedia.org/").get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Elements newsHeadlines = doc.select("#mp-itn b a");
//        Toast.makeText(getContext(), newsHeadlines.toString(), Toast.LENGTH_SHORT).show();
    }
}
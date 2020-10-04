package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class BookInfoAct extends AppCompatActivity {
    private SmartMaterialSpinner spProvince;
    private SmartMaterialSpinner spEmptyItem;
    private List<String> provinceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        initSpinner();
    }

    private void initSpinner() {
        spProvince = findViewById(R.id.spinner1);

        provinceList = new ArrayList<>();

        provinceList.add("05:30AM - 06:30AM");
        provinceList.add("06:00AM - 07:00AM");
        provinceList.add("06:30AM - 07:30AM");
        provinceList.add("07:00AM - 08:00AM");
        provinceList.add("07:30AM - 08:30AM");
        provinceList.add("08:00AM - 09:00AM");


        provinceList.add("08:30AM - 09:30AM");
        provinceList.add("09:00AM - 10:00AM");
        provinceList.add("09:30AM - 10:30AM");
        provinceList.add("10:00AM - 11:00AM");


        spProvince.setItem(provinceList);

        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(BookInfoAct.this, provinceList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
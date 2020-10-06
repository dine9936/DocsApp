package com.example.docsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;


public class ThyrocareChild extends AppCompatActivity {
private ZoomageView zoomageView;
private TextView pricee,test,name;
Toolbar toolbar;
Button button;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thyrocare_child);
        String image = getIntent().getStringExtra("image");
        String number = getIntent().getStringExtra("test");
        String price = getIntent().getStringExtra("price");
        String namee = getIntent().getStringExtra("name");


        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThyrocareChild.this,BookInfoAct.class);

                intent.putExtra("name",namee);
                startActivity(intent);

            }
        });
        toolbar = findViewById(R.id.toolbar_thyrocare_child);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        pricee = findViewById(R.id.pricee);
        test = findViewById(R.id.number);
        name = findViewById(R.id.name);



       // Toast.makeText(this, namee+number+price, Toast.LENGTH_SHORT).show();
        test.setText(number+" Tests");
        pricee.setText("₹"+price);
        name.setText(namee);


        zoomageView = findViewById(R.id.image);
        if(!image.equals(""))
        Glide.with(this).load(image).into(zoomageView);





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
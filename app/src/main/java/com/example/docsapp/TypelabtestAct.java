package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TypelabtestAct extends AppCompatActivity {
Button buttonthyro,buttongd,buttonrapid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typelabtest);

        buttonthyro = findViewById(R.id.thyrocare);
        buttongd = findViewById(R.id.gd);
        buttonrapid = findViewById(R.id.rapid);


        buttonthyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TypelabtestAct.this,Thyrocare.class);
                startActivity(intent);
            }
        });

        buttonrapid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TypelabtestAct.this,Rapid.class);
                startActivity(intent);
            }
        });

        buttongd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TypelabtestAct.this, Gd.class);
                startActivity(intent);
            }
        });
    }
}
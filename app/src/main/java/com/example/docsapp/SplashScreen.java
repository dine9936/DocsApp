package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.docsapp.Models.CustomerInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if (user != null){

                    reference.child("paitent").child(user.getPhoneNumber()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            CustomerInfo customerInfo = snapshot.getValue(CustomerInfo.class);
                            CommonClass.name = customerInfo.getUserName();
                            CommonClass.phone = customerInfo.getUserPhone();
                            CommonClass.email = customerInfo.getUserEmail();
                            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else {
                    Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
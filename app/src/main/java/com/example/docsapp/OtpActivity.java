package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PhoneAuthActivity";

    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    private FirebaseAuth mAuth;
    // [END declare_auth]

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private DatabaseReference reference;
    private FirebaseDatabase database;

    String phone, name, email;

    private TextView textViewPhone, textViewChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        textViewPhone = findViewById(R.id.text_phone);
        textViewChange = findViewById(R.id.text_change);

        phone = getIntent().getExtras().getString("phone");
        name = getIntent().getExtras().getString("name");
        email = getIntent().getExtras().getString("email");

        textViewPhone.setText("+91-"+phone);
        textViewChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OtpActivity.this,LoginActivity.class);
//                ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim., R.anim.fade_out);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);

    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public void onClick(View view) {

    }
}
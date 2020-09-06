package com.example.docsapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.docsapp.Adapters.SliderAdapterExample;
import com.example.docsapp.Models.CustomerInfo;
import com.example.docsapp.Models.SliderItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    SliderView sliderView;
    private SliderAdapterExample adapter;

    private Button buttonProceed, buttonVerify, buttonResend;
    private EditText editTextPhone, editTextEmail, editTextName, editTextOtp;

    private ScrollView scrollViewUserInfo;
    private LinearLayout linearLayoutOtp, llProgress;
    private RelativeLayout rlResend;

    CountDownTimer yourCountDownTimer;

    //    private static final String TAG = "PhoneAuthActivity";
//
    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    private FirebaseAuth mAuth;
    // [END declare_auth]

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private DatabaseReference reference;
    private FirebaseDatabase database;


    private TextView textViewPhone, textViewChange, textViewTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        textViewPhone = findViewById(R.id.text_phone);
        textViewTimer = findViewById(R.id.text_timer);
        textViewChange = findViewById(R.id.text_change);
        sliderView = findViewById(R.id.imageSlider);

        buttonProceed = findViewById(R.id.button_proceed);
        buttonResend = findViewById(R.id.button_resend);
        buttonResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonResend.setEnabled(false);
                rlResend.setVisibility(View.GONE);
                llProgress.setVisibility(View.VISIBLE);
                resendVerificationCode("+91"+editTextPhone.getText().toString().trim(),mResendToken);

                buttonResend.setEnabled(true);

            }
        });
        buttonVerify = findViewById(R.id.button_verify);
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonVerify.setEnabled(false);
                yourCountDownTimer.cancel();
                String code = editTextOtp.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    buttonVerify.setEnabled(true);
                    editTextOtp.setError("Cannot be empty");
                    return;
                }
                buttonVerify.setEnabled(true);
                verifyPhoneNumberWithCode(mVerificationId, code);
            }
        });
        editTextName = findViewById(R.id.edit_name);
        editTextEmail = findViewById(R.id.edit_email);
        editTextPhone = findViewById(R.id.edit_phone);
        editTextOtp = findViewById(R.id.editText_otp);


        llProgress = findViewById(R.id.ll_progress);
        rlResend = findViewById(R.id.rl_resend);

        scrollViewUserInfo = findViewById(R.id.ll_user_info);
        linearLayoutOtp = findViewById(R.id.ll_otp);


        textViewChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVerificationInProgress = false;
                scrollViewUserInfo.setVisibility(View.VISIBLE);
                linearLayoutOtp.setVisibility(View.GONE);
            }
        });

        mAuth = FirebaseAuth.getInstance();


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                mVerificationInProgress = false;

//                layoutPhone.setVisibility(View.GONE);
//                layoutOtp.setVisibility(View.GONE);
//                layoutLocation.setVisibility(View.VISIBLE);

                if (credential != null) {
                    if (credential.getSmsCode() != null) {
                        yourCountDownTimer.cancel();
                        editTextOtp.setText(credential.getSmsCode());
                    } else {
                        editTextOtp.setText(R.string.instant_validation);
                    }
                }
                Toast.makeText(LoginActivity.this, "completed", Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                mVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                    editTextPhone.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {

                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                }

                Toast.makeText(LoginActivity.this, "failed", Toast.LENGTH_SHORT).show();

                scrollViewUserInfo.setVisibility(View.VISIBLE);
                linearLayoutOtp.setVisibility(View.GONE);

                // [END_EXCLUDE]
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                mVerificationId = verificationId;
                mResendToken = token;
                scrollViewUserInfo.setVisibility(View.GONE);
                linearLayoutOtp.setVisibility(View.VISIBLE);

                yourCountDownTimer = new CountDownTimer(60000, 1000) {
                    public void onTick(long millisUntilFinished) {

                        textViewTimer.setText("00:" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        llProgress.setVisibility(View.GONE);
                        rlResend.setVisibility(View.VISIBLE);
                    }

                }.start();
                textViewPhone.setText(editTextPhone.getText().toString() + " ");


            }
        };


        buttonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();

                if (name.equals("") || phone.equals("") || email.equals("")) {
                    Toast.makeText(LoginActivity.this, "Fill All Entries", Toast.LENGTH_SHORT).show();
                } else {

                    textViewPhone.setText("+91-" + phone);
                    scrollViewUserInfo.setVisibility(View.GONE);
                    linearLayoutOtp.setVisibility(View.VISIBLE);

                    buttonProceed.setEnabled(false);

                    if (!validatePhoneNumber()) {

                        buttonProceed.setEnabled(true);
                        return;
                    }

                    buttonProceed.setEnabled(true);
                    startPhoneNumberVerification("+91" + editTextPhone.getText().toString().trim());


                }
            }
        });

        adapter = new SliderAdapterExample(this);
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


        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
            if (i == 0)
                sliderItem.setImageUrl("data:image/jpeg;base64,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");
            else if (i == 1)
                sliderItem.setImageUrl("data:image/jpeg;base64,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");
            else if (i == 2)
                sliderItem.setImageUrl("data:image/png;base64,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");
            else if (i == 3)
                sliderItem.setImageUrl("data:image/jpeg;base64,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");
            else if (i == 4)
                sliderItem.setImageUrl("data:image/jpeg;base64,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");
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);

    }


    @Override
    public void onStart() {
        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//
//        if (mVerificationInProgress && validatePhoneNumber()) {
//            startPhoneNumberVerification("+91" + editTextPhone.getText().toString());
//        }
//    }
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


    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

        mVerificationInProgress = true;
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }

    //
//
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = task.getResult().getUser();

                            String mobile = user.getPhoneNumber();

                            reference.child("paitent").child(mobile).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        CustomerInfo customerInfo = snapshot.getValue(CustomerInfo.class);
                                        CommonClass.name = customerInfo.getUserName();
                                        CommonClass.phone = customerInfo.getUserPhone();
                                        CommonClass.email = customerInfo.getUserEmail();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        sendCustomerInfo();
                                        editTextName.setText(null);
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                findViewById(R.id.button_verify).setEnabled(true);
                                //progressBar2.setVisibility(View.GONE);
                                editTextOtp.setError("Invalid code.");

                            }


                        }
                    }
                });
    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            reference.child("paitent").child(user.getPhoneNumber()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        editTextPhone.setText(null);
                        editTextName.setText(null);
                        scrollViewUserInfo.setVisibility(View.GONE);
                        linearLayoutOtp.setVisibility(View.GONE);
                        // layoutLocation.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
//        } else {
//            layoutPhone.setVisibility(View.VISIBLE);
//            editTextPhone.setText(null);
//            layoutOtp.setVisibility(View.GONE);
//            layoutLocation.setVisibility(View.GONE);
//        }
    }


    private boolean validatePhoneNumber() {
        String phoneNumber = editTextPhone.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            editTextPhone.setError("Invalid phone number.");
            return false;
        }

        return true;
    }


//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.button_login:
//                findViewById(R.id.button_login).setEnabled(false);
//                progressBar.setVisibility(View.VISIBLE);
//                if (!validatePhoneNumber()) {
//                    progressBar.setVisibility(View.GONE);
//                    findViewById(R.id.button_login).setEnabled(true);
//                    return;
//                }
//
//                startPhoneNumberVerification("+91" + editTextPhone.getText().toString().trim());
//                break;
//            case R.id.button_otp:
//                findViewById(R.id.button_otp).setEnabled(false);
//                progressBar2.setVisibility(View.VISIBLE);
//                yourCountDownTimer.cancel();
//
//                String code = editTextOtp.getText().toString().trim();
//                if (TextUtils.isEmpty(code)) {
//                    findViewById(R.id.button_otp).setEnabled(true);
//                    progressBar2.setVisibility(View.GONE);
//                    editTextOtp.setError("Cannot be empty.");
//                    return;
//                }
//
//                verifyPhoneNumberWithCode(mVerificationId, code);
//                break;
//            case R.id.textView_resend_otp:
//                resendVerificationCode("+91" + editTextPhone.getText().toString(), mResendToken);
//                break;
//            case R.id.change_number:
//                findViewById(R.id.button_login).setEnabled(true);
//                progressBar.setVisibility(View.GONE);
//                findViewById(R.id.button_otp).setEnabled(true);
//                progressBar2.setVisibility(View.GONE);
//                yourCountDownTimer.cancel();
//                layoutOtp.setVisibility(View.GONE);
//                layoutPhone.setVisibility(View.VISIBLE);
//                layoutLocation.setVisibility(View.GONE);
//                editTextOtp.setText("");
//                break;
//
//            case R.id.button_location:
//                sendCustomerInfo();
//                break;
//
//            case R.id.ll_region:
//                spinnerDialog.showSpinerDialog();
//                break;
//            case R.id.ll_region2:
//                if (!regionList2.isEmpty()){
//                    spinnerDialog2.showSpinerDialog();
//                }
//                else {
//                    Toast.makeText(this, "Select Region", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void sendCustomerInfo() {
//        FirebaseUser user = mAuth.getCurrentUser();
//        String name = editTextName.getText().toString().trim();
//        String region = textViewRegion.getText().toString().trim();
//        String nearest = textViewRegion2.getText().toString().trim();
//
//        if (name.isEmpty() || region.equals("") || nearest.equals("")) {
//            Toast.makeText(this, "Fill All Fields", Toast.LENGTH_SHORT).show();
//        } else {
//            CustomerInfo customerInfo = new CustomerInfo(name, region, nearest);
//            reference.child("customer").child(user.getPhoneNumber()).setValue(customerInfo);
//        }
//
//    }
//    @Override
//    public void onClick(View view) {


    private void sendCustomerInfo() {

        FirebaseUser usernew = FirebaseAuth.getInstance().getCurrentUser();

        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        //String region = textViewRegion.getText().toString().trim();
        //String nearest = textViewRegion2.getText().toString().trim();
        if (name.equals("") ||email.equals("")||phone.equals("")) {
            Toast.makeText(this, "Fill All Fields", Toast.LENGTH_SHORT).show();
        } else {


            CommonClass.name = name;
            CommonClass.email = email;
            CommonClass.phone = usernew.getPhoneNumber();

            CustomerInfo customerInfo = new CustomerInfo();
            customerInfo.setUserName(CommonClass.name);
            customerInfo.setUserPhone(CommonClass.phone);
            customerInfo.setUserEmail(CommonClass.email);


            reference.child("paitent").child(CommonClass.phone).setValue(customerInfo);

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

    }

}

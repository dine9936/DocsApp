package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.docsapp.Models.CustomerInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class BookInfoAct extends AppCompatActivity {
    private SmartMaterialSpinner<String> spProvince;
    private SmartMaterialSpinner spEmptyItem;
    private List<String> provinceList;
    private EditText editTextname,editTextage,editTextemail,editTextAddress,editTextPincode,editTextPhone,editTextdiscount;
    private Button button;

    FirebaseDatabase database;
    DatabaseReference reference;

    FirebaseUser user;

    RadioGroup radioGroup;
    RadioButton radioButton;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);


        radioGroup = findViewById(R.id.gender);


        String namee = getIntent().getStringExtra("name");
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        user = FirebaseAuth.getInstance().getCurrentUser();

        initSpinner();

        editTextname = findViewById(R.id.name);
        editTextemail = findViewById(R.id.emailAddress);
        editTextAddress = findViewById(R.id.address);
        editTextdiscount  = findViewById(R.id.discount);


        editTextPincode = findViewById(R.id.pincode);

        editTextage = findViewById(R.id.age);
        editTextPhone = findViewById(R.id.phone);

        button = findViewById(R.id.submitbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = radioGroup.getCheckedRadioButtonId();


                radioButton = (RadioButton) findViewById(id);
                gender = radioButton.getText().toString();
                String name = editTextname.getText().toString().trim();
                String email = editTextemail.getText().toString().trim();
                String pincode = editTextPincode.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                String age = editTextage .getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();

                String discount = editTextdiscount.getText().toString();




                CustomerInfo customerInfo = new CustomerInfo();
                customerInfo.setUserEmail(email);
                customerInfo.setUserName(name);
                customerInfo.setPincode(pincode);
                customerInfo.setAddress(address);
                customerInfo.setUserPhone(phone);
                customerInfo.setAge(age);
                customerInfo.setPackagename(namee);

                customerInfo.setDiscount(discount);
                customerInfo.setGender(gender);



                if (!name.equals("") && !email.equals("") && !pincode.equals("") && !address.equals("") && age.equals("") && !phone.equals("")){
                    reference.child("BookedPatient").child(user.getPhoneNumber()).setValue(customerInfo);
                }
            }
        });

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
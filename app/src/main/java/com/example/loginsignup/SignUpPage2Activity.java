package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class SignUpPage2Activity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;
    String fullName,email,username,password,gender,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page2);

        //Get the data which were sent
        Intent intent = getIntent();
        fullName = intent.getStringExtra("full_Name");
        email = intent.getStringExtra("email_id");
        username = intent.getStringExtra("user_name");
        password = intent.getStringExtra("pass_word");

        //Hooks
        radioGroup = findViewById(R.id.radioGroup);
        datePicker = findViewById(R.id.datePicker);

    }

    //Functions

    public void callSignUpPage3(View view){

        if(!validateAge() | !validateGender()){
            return;
        }

        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
        gender = selectedGender.getText().toString();
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        date = day+ "/" + month + "/" + year;

        Intent intent = new Intent(getApplicationContext(), EnterMobileNum.class);
        intent.putExtra("full_Name",fullName);
        intent.putExtra("email_id",email);
        intent.putExtra("user_name",username);
        intent.putExtra("pass_word",password);
        intent.putExtra("gender",gender);
        intent.putExtra("date",date);
        startActivity(intent);

    }

    public boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    public boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 14) {
            Toast.makeText(this, "You are not eligible to apply", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

}
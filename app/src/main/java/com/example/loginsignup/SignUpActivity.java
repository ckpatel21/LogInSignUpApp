package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {
    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText;
    TextInputEditText fullName, email, username, password;
    String full_name, pass_word, user_name, e_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks
        backBtn = findViewById(R.id.SignUpBackButtton);
        next = findViewById(R.id.SignUpNextBtn);
        login = findViewById(R.id.SignUpLoginBtn);
        titleText = findViewById(R.id.SignUpTitleText);
        fullName = findViewById(R.id.evFullName);
        email = findViewById(R.id.evEmail);
        username = findViewById(R.id.evUsername);
        password = findViewById(R.id.evPassword);

    }

    //Functions

    public void callNextSignupScreen(View view) {

        if (!validateFullName() | !validateEmail() | !validateUsername() | !validatePassword()){
            return;
        }

        Intent intent = new Intent(getApplicationContext(), SignUpPage2Activity.class);
        intent.putExtra("full_name",full_name);
        intent.putExtra("email_id", e_mail);
        intent.putExtra("user_name",user_name);
        intent.putExtra("pass_word",pass_word);
        startActivity(intent);
    }

    private boolean validateFullName() {
        String val = fullName.getText().toString();

        if (val.isEmpty()) {
            fullName.setError("Field cannot be empty");
            return false;
        } else {
            fullName.setError(null);
            full_name = val;
            return true;
        }

    }

    private boolean validateUsername() {
        String val = username.getEditableText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else if (val.length() > 20) {
            username.setError("Username is too large");
            return false;
        } else if (!val.matches(checkspaces)) {
            username.setError("no White spaces are allowed");
            return false;
        } else {
            username.setError(null);
            user_name = val;
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditableText().toString().trim();
        String checkEmail  = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email");
            return false;
        } else {
            email.setError(null);
            e_mail = val;
            return true;
        }
    }

    private boolean validatePassword() {
        String val = password.getEditableText().toString().trim();
        String checkPassword  =  ".{4,}";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            password.setError("Invalid Password");
            return false;
        } else {
            password.setError(null);
            pass_word = val;
            return true;
        }
    }



}








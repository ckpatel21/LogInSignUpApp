package com.example.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogInActivity extends AppCompatActivity {

    TextInputLayout phoneNumber, password;
    String _phoneNumber,_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        phoneNumber = findViewById(R.id.loginPhoneNum);
        password = findViewById(R.id.loginPassword);

    }

    public void letTheUserLogin(View view){
        if(!validateFields()){
            return;
        }

        //Get data
        _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        _password = password.getEditText().getText().toString().trim();

        //Database
        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("mobileNumber").equalTo(_phoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String systemPassword = snapshot.child(_phoneNumber).child("password").getValue(String.class);
                    if(systemPassword.equals(_password)){
                        password.setError(null);
                        password.setErrorEnabled(false);

                        // if password matches
                        String _fullname = snapshot.child(_phoneNumber).child("fullName").getValue(String.class);
                        String _email = snapshot.child(_phoneNumber).child("email").getValue(String.class);
                        String _username = snapshot.child(_phoneNumber).child("userName").getValue(String.class);
                        String _gender = snapshot.child(_phoneNumber).child("gender").getValue(String.class);
                        String _phoneNum = snapshot.child(_phoneNumber).child("phoneNumber").getValue(String.class);

                        Toast.makeText(LogInActivity.this, _fullname + "/n" +_email+ "/n" +_username+ "/n" +_gender+ "/n" +_phoneNum, Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(LogInActivity.this, "Password does not match.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LogInActivity.this, "Data Does not exist.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LogInActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateFields(){
        _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        _password = password.getEditText().getText().toString().trim();
        if(_phoneNumber.isEmpty()){
            phoneNumber.setError("Phone number cannot be empty");
            phoneNumber.requestFocus();
            return false;
        } else if(_password.isEmpty()){
            password.setError("Password cannot be empty");
            password.requestFocus();
            return false;
        }
        return true;
    }
}
package com.example.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPageActivity extends AppCompatActivity {
    Button btnLogin;
    TextInputLayout phoneNumber, password;
    String _phoneNumber,_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        btnLogin = findViewById(R.id.btnLetUserLogin);
        phoneNumber = findViewById(R.id.loginPhoneNum);
        password = findViewById(R.id.loginPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateFields()) {
                    return;
                }

//
//                //Get data
//                _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
//                _password = password.getEditText().getText().toString().trim();

                    //Database
                    Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phoneNumber").equalTo(_phoneNumber);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                phoneNumber.setError(null);
                                phoneNumber.setErrorEnabled(false);

                                String systemPassword = snapshot.child(_phoneNumber).child("password").getValue(String.class);
                                if (systemPassword.equals(_password)) {
                                    password.setError(null);
                                    password.setErrorEnabled(false);

                                    // if password matches
                                    String _email = snapshot.child(_phoneNumber).child("email").getValue(String.class);
                                    String _username = snapshot.child(_phoneNumber).child("userName").getValue(String.class);
                                    String _gender = snapshot.child(_phoneNumber).child("gender").getValue(String.class);
                                    String _phoneNum = snapshot.child(_phoneNumber).child("phoneNumber").getValue(String.class);

                                    Toast.makeText(LoginPageActivity.this, _email + "/n" + _username + "/n" + _gender + "/n" + _phoneNum, Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(LoginPageActivity.this, "Password does not match.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginPageActivity.this, "Data Does not exist.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(LoginPageActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

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
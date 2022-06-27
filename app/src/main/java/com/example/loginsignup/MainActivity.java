package com.example.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button logIn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooks
        logIn = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.btnSignUp);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginPageActivity.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

//    public void callLogInScreen(View view){
//        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
//        startActivity(intent);
//    }
//    public void callSignUpScreen(View view){
//        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
//        startActivity(intent);
//    }
}
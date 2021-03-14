package com.example.noodles_fitnesspal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button signup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar Identifies it in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Sets Title of the toolbar
        getSupportActionBar().setTitle("Fitness Pal");
        // Signup Button being identified from the xml file
        signup = findViewById(R.id.button);
        // Sign up button is starting an activity in which leads to Sign Up Activity
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistering();
            }
        });
        // Log In Button being identified from the xml file
        login = findViewById(R.id.button2);
        // Log In button is starting an activity in which leads to Log In Activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginUser();

            }
        });
    }

    public void openLoginUser() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openRegistering() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
package com.example.testacazia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView wel ;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        wel = findViewById(R.id.welcomeText);
        Intent intent = getIntent();
        data = intent.getExtras();

        String name = data.getString("userName");

        wel.setText("Welcome "+ name);


    }
}
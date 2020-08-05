package com.example.testacazia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView wel ;
    Bundle data;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        wel = findViewById(R.id.welcomeText);
        logout = findViewById(R.id.logoutBtn);
        Intent intent = getIntent();
        data = intent.getExtras();

        String name = data.getString("userName");

        wel.setText("Welcome "+ name);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Home.this, Login.class);
                startActivity(login);


            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
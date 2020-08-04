package com.example.testacazia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText Email;
    EditText Password;
    CheckBox Remember;
    Button LoginBtn;
    DataBase dataBase;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefesEditor;
    boolean saveLogin;
    TextView signup;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//......................... Database..............................
        dataBase = new DataBase(this, "Data.sqlite", null,1);
      // create Table....

        dataBase.QueryData("CREATE TABLE IF NOT EXISTS User(UserId INTEGER PRIMARY KEY AUTOINCREMENT, userName TEXT, userEmail TEXT, userPassword TEXT)");
//



        Email = findViewById(R.id.edtEmail);
        Password = findViewById(R.id.edtPassword);
        Remember = findViewById(R.id.RememberUser);
        LoginBtn = findViewById(R.id.LoginBtn);
        signup = findViewById(R.id.SignupTv);
        loginPreferences = getSharedPreferences("loginPrefs",MODE_PRIVATE);
        loginPrefesEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin",false);
        if(saveLogin == true){
            Email.setText(loginPreferences.getString("email",""));
            Password.setText(loginPreferences.getString("password",""));
            Remember.setChecked(true);
        }



        //


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               email = Email.getText().toString().trim();
               password =  Password.getText().toString().trim();

               String _name ="",_email = "", _password = "";

                Cursor data = dataBase.GetData("SELECT * FROM User WHERE userEmail ='" + email + "'");
                while (data.moveToNext()){
                    _name = data.getString(1);
                    _email = data.getString(2);
                    _password = data.getString(3);
                }

                if(_email.equals(email) == true && _password.equals(password) == true){

                    if(Remember.isChecked()){
                        loginPrefesEditor.putBoolean("saveLogin",true);
                        loginPrefesEditor.putString("email",email);
                        loginPrefesEditor.putString("password",password);
                        loginPrefesEditor.commit();
                    }

                    else {
                        loginPrefesEditor.clear();
                        loginPrefesEditor.commit();
                    }

                    Intent home = new Intent(Login.this, Home.class);
                    home.putExtra("userName",_name);
                    startActivity(home);
                }
                else {
                    Toast.makeText(Login.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();


                }



            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(Login.this, Signup.class);

                startActivity(signup);
            }
        });
    }




}
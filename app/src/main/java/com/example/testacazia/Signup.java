package com.example.testacazia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    EditText Name;
    EditText Email;
    EditText Password;
    Button signupBtn;
    String name,email,password;
    DataBase dataBase;
    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dataBase = new DataBase(this, "Data.sqlite", null,1);

        Name = findViewById(R.id.edtName);
        Email = findViewById(R.id.Emailedt);
        Password = findViewById(R.id.PassWordedt);
        signupBtn = findViewById(R.id.btnSignUp);
        login = findViewById(R.id.Btnlogin);



        //

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = Name.getText().toString().trim();
                email = Email.getText().toString().trim();
                password = Password.getText().toString().trim();

                if(name.length() != 0 && email.length() != 0 && password.length() != 0){
                    String _email = "";
                    Cursor data = dataBase.GetData("SELECT * FROM USER WHERE userEmail = '" + email + "'");
                    while (data.moveToNext()) {
                        _email = data.getString(2);
                    }
                    if(_email == ""){
                        if(validatePassword(password)){
                            dataBase.QueryData("INSERT INTO User VALUES(null,'" + name + "','" + email + "','" + password + "')");
                            Toast.makeText(Signup.this, "Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                            Name.setText("");
                            Email.setText("");
                            Password.setText("");


                        }
                        else {
                            Toast.makeText(Signup.this, "Mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Signup.this,"Email đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Signup.this, "Hãy nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Signup.this,Login.class);
                startActivity(login);
            }
        });
    }

    public boolean validatePassword(String password){

        if(password.length() < 6){
            return false;
        }
        else {
            Pattern p = Pattern.compile("[0-9]");
            Pattern spe = Pattern.compile("[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]");
            Matcher m = p.matcher(password);
            Matcher n = spe.matcher(password);

            if(m.find() == true && n.find() == true){
                return true;
            }
            else return false;
        }


    }
}
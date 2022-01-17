package com.example.arduinoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText username,password,repassword;
    Button button1, button2;
    DBConfig DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        DB = new DBConfig(this);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("")| pass.equals("")|repass.equals(""))
                    Toast.makeText(MainActivity.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                else {
                        if(pass.equals(repass)){
                            boolean checkuser = DB.checkusername(user);
                            if(checkuser==false){
                                Boolean insert = DB.insertData(user,pass);
                                if(insert==true){
                                    Toast.makeText(MainActivity.this, "Registered.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                    startActivity(intent);
                                } else{
                                    Toast.makeText(MainActivity.this, "Not Registered.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this,"User Exist", Toast.LENGTH_SHORT).show();
                            }
                    } else {
                            Toast.makeText(MainActivity.this,"Password Not Matched.", Toast.LENGTH_SHORT).show();
                        }
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
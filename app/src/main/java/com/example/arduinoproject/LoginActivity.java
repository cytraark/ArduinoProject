package com.example.arduinoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username1,password1;
    Button button3;
    DBConfig DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username1 = (EditText) findViewById(R.id.username1);
        password1 = (EditText) findViewById(R.id.password1);
        button3 = (Button) findViewById(R.id.button3);
        DB = new DBConfig(this);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username1.getText().toString();
                String pass = password1.getText().toString();

                if(user.equals("")|pass.equals(""))
                    Toast.makeText(LoginActivity.this,"Please Insert Data", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this,"Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else{
                        Toast.makeText(LoginActivity.this,"Password Wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
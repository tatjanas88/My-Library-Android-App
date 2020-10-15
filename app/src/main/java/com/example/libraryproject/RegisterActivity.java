package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText e1, e2, e3;
    private CardView b1, returnButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        e1 = findViewById(R.id.username);
        e2 = findViewById(R.id.pass);
        e3 = findViewById(R.id.cpass);
        b1 = findViewById(R.id.registerCard);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                //checking all the fields for good registration
                if (s1.equals("") || s2.equals("") || s3.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (s2.equals(s3)) {
                        //in Reg.Act. activity checking just username
                        Boolean chkUsername = db.checkUsername(s1);
                        if (chkUsername == true) {
                            Boolean insert = db.insert(s1, s2);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                                returnButton = findViewById(R.id.returnButton);
                                b1.setVisibility(View.GONE);
                                returnButton.setVisibility(View.VISIBLE);
                                returnButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent r = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(r);

                                    }
                                });
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password does not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
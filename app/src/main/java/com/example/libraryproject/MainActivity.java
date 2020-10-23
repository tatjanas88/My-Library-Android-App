package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.MailTo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //za pristupanje elementu van ove aktivnosti
    //private static MainActivity mInstance=null;
    //public String usernameDB;
    //protected MainActivity(){}
       /* public static synchronized MainActivity getInstance()
        {
            if(null==mInstance)
            {
                mInstance=new MainActivity();
            }
            return mInstance;
        }*/

    private EditText editText, editText1;
    private CardView card;
    private TextView textView2;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);
        card = findViewById(R.id.card);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText.getText().toString();
                String password = editText1.getText().toString();
                Boolean chkEmailPass = db.usernamepassword(username, password);
                if (chkEmailPass == true) {
                    //saving username for creating SharedPref.
                    //usernameDB = username;
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent g = new Intent(MainActivity.this, MainActivity2.class);

                    startActivity(g);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView2 = findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
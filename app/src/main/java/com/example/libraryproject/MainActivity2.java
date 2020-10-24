package com.example.libraryproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
//in this activity just setting OnClickListeners for all the buttons

public class MainActivity2 extends AppCompatActivity {
    private CardView btnAllBooks, btnCurrentlyReading, btnAlreadyRead, btnWantToRead, btnFavorite, btnAbout;
    private TextView txtName, txtLicence, btnSignOff;
    //public static Boolean signedOff=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();
        Utils.getInstance(this);
        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });
        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            //zastita, ako korisnik na pocetnom ekranu kline na Already Read, da ne bude null point. exc
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, AlreadyReadBooksActivity.class);
                startActivity(intent);
            }
        });
        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            //zastita, ako korisnik na pocetnom ekranu kline na Already Read, da ne bude null point. exc
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, CurrentlyReadingActivity.class);
                startActivity(intent);
            }
        });
        btnWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, WantToReadActivity.class);
                startActivity(intent);
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, FavoriteBooksActivity.class);
                startActivity(intent);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("More information about all the books from the app  and ways to buy can be checked on the Book Depository web page\n\n" + "*Check the website*");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: Show website
                        Intent intent = new Intent(MainActivity2.this, WebSiteActivity.class);
                        startActivity(intent);

                    }
                });
                builder.create().show();
            }
        });

        btnSignOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "Signed Off", Toast.LENGTH_SHORT).show();
                Intent signOff = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(signOff);
                //signedOff = true;
            }
        });

    }


    private void initViews() {

        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWantToRead = findViewById(R.id.btnWantToRead);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnAbout = findViewById(R.id.btnAbout);
        txtLicence = findViewById(R.id.txtLicence);
        btnSignOff = findViewById(R.id.btnSignOff);
    }
}
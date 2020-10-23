package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class CurrentlyReadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);
        RecyclerView bookRecView = findViewById(R.id.booksRecView);
        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this, "currentlyReading");
        bookRecView.setAdapter(adapter);
        bookRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getCurrentlyReadingBooks());
        if (Utils.getInstance(this).getCurrentlyReadingBooks().isEmpty()) {
            Toast.makeText(this, "--There are no books in Currently Reading--", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
package com.example.libraryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FavoriteBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_books);
        RecyclerView bookRecView = findViewById(R.id.booksRecView);
        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this, "favoriteBooks");
        bookRecView.setAdapter(adapter);
        bookRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getInstance(this).getFavoriteBooks());
        //checking if the list is empty
        if (Utils.getInstance(this).getFavoriteBooks().isEmpty())
        {
            Toast.makeText(this, "--There are currently no books in favorites--", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //securing that anim works on onbackPressed from this point
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
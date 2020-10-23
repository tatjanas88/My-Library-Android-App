package com.example.libraryproject;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

//this activity enables manipulating with the specific book
public class BookActivity extends AppCompatActivity {
    public static final String BOOK_ID_KEY = "bookId";

    private Button btnAddToCurrentlyReading, btnAddToWantRead, btnAddToAlreadyRead, btnAddToFavorites;
    private TextView txtDescription, txtBookName, txtAuthorName, txtPages, descriptionText, txt1, btnHome;
    private ImageView imgBook;
    private ConstraintLayout homeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                //bookId is the main element of the book
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                    homeBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(BookActivity.this, "Returning to Home Page", Toast.LENGTH_SHORT).show();
                            Intent intentHome = new Intent(BookActivity.this, MainActivity2.class);
                            startActivity(intentHome);
                        }
                    });
                }
            }
        }
    }

    public void handleFavoriteBooks(final Book book) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();
        boolean existInFavoriteBooks = false;
        for (Book b : favoriteBooks) {
            if (b.getId() == book.getId()) {
                existInFavoriteBooks = true;
            }
        }
        if (existInFavoriteBooks) {
            //disabling button when we've already added the book
            btnAddToFavorites.setEnabled(false);
            //setCardViewBackgroundColor(btnAddToFavorites, R.color.myColor2);
        } else {
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFavoriteBooks(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something Wrong Happened", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    public void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for (Book b : currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
            }
        }
        if (existInCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setEnabled(false);

            //setCardViewBackgroundColor(btnAddToCurrentlyReading, R.color.myColor2);
        } else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReadingBooks(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something Wrong Happened", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    public void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();
        boolean existinWantToReadBooks = false;
        for (Book b : wantToReadBooks) {
            if (b.getId() == book.getId()) {
                existinWantToReadBooks = true;
            }
        }
        if (existinWantToReadBooks) {
            btnAddToWantRead.setEnabled(false);
            //setCardViewBackgroundColor(btnAddToWantRead, R.color.myColor2);
        } else {
            btnAddToWantRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToWantToReadBooks(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something Wrong Happened", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    public void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;
        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyReadBooks = true;
            }
        }
        if (existInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
            //setCardViewBackgroundColor(btnAddToAlreadyRead, R.color.myColor2);

        } else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyReadBooks(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBooksActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something Wrong Happened", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    public void setData(Book book) {
        //getting and setting information about the book from the class Book
        txtBookName.setText(book.getName());
        txtAuthorName.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        descriptionText.setText(book.getShortDesc());
        txtDescription.setText(book.getLongDesc());
        Glide.with(this).asBitmap().load(book.getImageUrl()).into(imgBook);


    }

    public void setCardViewBackgroundColor(CardView btn, int color) {
        //I firstly used CardView instead of Button
        btn.setCardBackgroundColor(Color.TRANSPARENT);
        btn.setCardElevation(0);
        //btn.setCardBackgroundColor(Color.WHITE);
        btn.setCardBackgroundColor(color);
    }

    public void initViews() {
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToWantRead = findViewById(R.id.btnAddToWantRead);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
        txtDescription = findViewById(R.id.txtDescription);
        descriptionText = findViewById(R.id.descriptionText);
        txtBookName = findViewById(R.id.txtBookName);
        txtAuthorName = findViewById(R.id.txtAuthorName);
        txtPages = findViewById(R.id.txtPages);
        imgBook = findViewById(R.id.imgBook);
        btnHome = findViewById(R.id.btnHome);
        homeBtn = findViewById(R.id.homeBtn);


    }
}
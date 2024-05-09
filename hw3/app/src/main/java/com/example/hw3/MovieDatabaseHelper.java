package com.example.hw3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movies3.db";
    private static final int DATABASE_VERSION = 2;

    private static final String CREATE_TABLE =
            "CREATE TABLE movies (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, trailer_url TEXT)";

    public MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        // Insert sample data
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movies");
        onCreate(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        // Sample movie data
        addMovie(db, "Inception", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8hP9D6kZseM?si=07m-p23CT-FOFWyl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>");
        addMovie(db, "inception", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8hP9D6kZseM?si=07m-p23CT-FOFWyl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>");
        addMovie(db, "The Dark Knight", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8hP9D6kZseM?si=07m-p23CT-FOFWyl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>");
        addMovie(db, "Interstellar", "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/8hP9D6kZseM?si=07m-p23CT-FOFWyl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>");
        // Add more sample data as needed
    }

    private void addMovie(SQLiteDatabase db, String name, String trailerUrl) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("trailer_url", trailerUrl);
        db.insert("movies", null, values);
    }
}


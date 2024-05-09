package com.example.hw2;

// DictionaryDatabaseHelper.java

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DictionaryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dictionary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE =
            "CREATE TABLE dictionary (id INTEGER PRIMARY KEY AUTOINCREMENT, word TEXT, definition TEXT)";

    public DictionaryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        // Populate sample data
        insertSampleData(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO dictionary (word, definition) VALUES ('apple', 'A fruit with red or yellow skin and a rounded shape.')");
        db.execSQL("INSERT INTO dictionary (word, definition) VALUES ('banana', 'A long curved fruit which grows in clusters.')");
        db.execSQL("INSERT INTO dictionary (word, definition) VALUES ('orange', 'A round juicy citrus fruit with a tough bright reddish-yellow rind.')");
        // Add more sample data as needed
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS dictionary");
        onCreate(db);
    }
}

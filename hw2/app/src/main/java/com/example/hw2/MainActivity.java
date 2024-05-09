package com.example.hw2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button searchButton;
    private TextView resultTextView;
    private DictionaryDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);
        dbHelper = new DictionaryDatabaseHelper(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWord();
            }
        });
    }

    private void searchWord() {
        String query = editText.getText().toString().trim();
        if (query.isEmpty()) {
            Toast.makeText(this, "Please enter a word to search", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM dictionary WHERE word = ?", new String[]{query});

        if (cursor.moveToFirst()) {
            int definitionColumnIndex = cursor.getColumnIndex("definition");
            if (definitionColumnIndex != -1) {
                String definition = cursor.getString(definitionColumnIndex);
                resultTextView.setText(definition);
            } else {
                resultTextView.setText("Definition not found");
            }
        } else {
            cursor = db.rawQuery("SELECT * FROM dictionary", null);
            StringBuilder result = new StringBuilder();
            int wordColumnIndex = cursor.getColumnIndex("word");
            int definitionColumnIndex = cursor.getColumnIndex("definition");
            while (cursor.moveToNext()) {
                String word = cursor.getString(wordColumnIndex);
                String definition = cursor.getString(definitionColumnIndex);
                result.append("Word: ").append(word).append("\n").append("Definition: ").append(definition).append("\n\n");
            }
            if (result.length() > 0) {
                resultTextView.setText("Word not found. Here are all words in the dictionary:\n\n" + result.toString());
            } else {
                resultTextView.setText("Dictionary is empty");
            }
        }
        cursor.close();
        db.close();
    }






}

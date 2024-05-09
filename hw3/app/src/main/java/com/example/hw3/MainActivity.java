package com.example.hw3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button searchButton;
    private TextView resultTextView;
    private WebView webView;
    private MovieDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);
        webView = findViewById(R.id.webView);
        dbHelper = new MovieDatabaseHelper(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovie();
            }
        });
    }

    private void searchMovie() {
        String query = editText.getText().toString().trim();
        if (query.isEmpty()) {
            Toast.makeText(this, "Please enter a movie name to search", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movies WHERE name LIKE ?", new String[]{"%" + query + "%"});

        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex("name");
            int trailerUrlColumnIndex = cursor.getColumnIndex("trailer_url");

            if (trailerUrlColumnIndex != -1) {
                // Movie trailer URL found, proceed with displaying the video
                String trailerUrl = cursor.getString(trailerUrlColumnIndex);
                resultTextView.setText("Movie Name: " + cursor.getString(nameColumnIndex));

                // Load HTML content into WebView
                webView.loadDataWithBaseURL(null, trailerUrl, "text/html", "utf-8", null);

                // Enable JavaScript for the WebView
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);

                // Set WebChromeClient for handling video playback
                webView.setWebChromeClient(new WebChromeClient());
            } else {
                // Trailer URL not found for the movie
                resultTextView.setText("Trailer URL not found for the movie: " + query);
            }
        } else {
            // No data found for the movie
            resultTextView.setText("No data found for the movie: " + query);
        }

        cursor.close();
        db.close();
    }

}

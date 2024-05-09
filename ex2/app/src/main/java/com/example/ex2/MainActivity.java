package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
    }

    public void readFile(View view) {
        try {
            FileInputStream fis = openFileInput("myfile.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            editText.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading file.", Toast.LENGTH_SHORT).show();
        }
    }

    public void writeFile(View view) {
        String text = editText.getText().toString();
        try {
            FileOutputStream fos = openFileOutput("myfile.txt", Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(this, "File saved.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error writing file.", Toast.LENGTH_SHORT).show();
        }
    }
}
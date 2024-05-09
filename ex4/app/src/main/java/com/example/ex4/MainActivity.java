    package com.example.ex4;

    import android.app.Activity;
    import android.os.Bundle;
    import android.os.Environment;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Toast;

    import java.io.File;
    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.nio.charset.StandardCharsets;

    public class MainActivity extends Activity implements View.OnClickListener {

        private Button btnRead, btnWrite;
        private EditText editText;
        private String fileName = "myfile.txt";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btnRead = findViewById(R.id.button_read);
            btnWrite = findViewById(R.id.button_write);
            editText = findViewById(R.id.edit_text);

            btnRead.setOnClickListener(this);
            btnWrite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.button_read:
                    readData();
                    break;
                case R.id.button_write:
                    writeData();
                    break;
            }
        }
    }

        private void readData() {
            try (FileInputStream fis = new FileInputStream(new File(getExternalFilesDir(null), fileName))) {
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                String data = new String(buffer, StandardCharsets.UTF_8);
                editText.setText(data);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error reading data from file: " + fileName, Toast.LENGTH_SHORT).show();
            }
        }

        private void writeData() {
            try (FileOutputStream fos = new FileOutputStream(new File(getExternalFilesDir(null), fileName))) {
                fos.write(editText.getText().toString().getBytes(StandardCharsets.UTF_8));
                Toast.makeText(this, "Data saved to file: " + fileName, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error saving data to file: " + fileName, Toast.LENGTH_SHORT).show();
            }
        }
    }
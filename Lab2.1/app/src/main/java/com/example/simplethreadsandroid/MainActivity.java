package com.example.simplethreadsandroid;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ExampleThread.ExampleThreadReporterInterface {

    ExampleThread thread = new ExampleThread(this);
    private TextView textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textField = findViewById(R.id.textView);
        textField.setMovementMethod(ScrollingMovementMethod.getInstance());
        findViewById(R.id.buttonStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thread.isAlive()) {
                    thread.start();
                } else {
                    thread = new ExampleThread(MainActivity.this);
                    thread.start();
                }
            }
        });

        findViewById(R.id.buttonStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                threadStopped();
            }
        });
    }

    @Override
    public void threadRunning() {
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        updateUIText(currentTime + " Tiisu, We Want More! \n");
    }

    private void updateUIText(final String newText) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textField.append(newText);
            }
        });
    }

    @Override
    public void threadStopped() {
        thread.interrupt();
    }
}

package com.example.androidthreadsandobservers;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ThreadClass.ThreadClassInterface {

    ThreadClass thread;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        createThreads();
        findViewById(R.id.createNewThreadButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createThreads();
            }
        });
    }

    private void createThreads() {
        thread = new ThreadClass(this);
        thread.start();
    }

    private void updateUI(String updateText) {
        textView.append("\n" + updateText);
    }

    @Override
    public void progressUpdate(int progress) {
        updateUI("Thread Id:" + Thread.currentThread().getId() + " Progress: " + progress + "%");

    }

    @Override
    public void progressCompleted() {
        updateUI("Thread Id:" + Thread.currentThread().getId() + " Progress COMPLETED");
        Thread.currentThread().interrupt();
    }
}

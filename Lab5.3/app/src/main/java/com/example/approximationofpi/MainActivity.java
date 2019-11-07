package com.example.approximationofpi;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CalculateThread.CalculateThreadInterface {

    TextView textView;
    Button startButton;
    CalculateThread calculateThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPICalculate();
            }
        });
    }

    private void startPICalculate() {
        startButton.setClickable(false);
        calculateThread = new CalculateThread(this);
        calculateThread.start();
    }

    @Override
    public void updateUI(final String progress) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(progress);
                startButton.setClickable(true);
            }
        });
    }
}

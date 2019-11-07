package com.example.androidhttpfiles;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements HTTPThread.HTTPThreadReporterInterface {

    EditText httpAddressText;
    TextView htmlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpAddressText = findViewById(R.id.httpAddressEditText);
        httpAddressText.requestFocus();
        htmlText = findViewById(R.id.htmlTextView);
        findViewById(R.id.goButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDownloading();
            }
        });
    }

    @Override
    public void finishedDownloading(String htmlString) {
        updateUIText(htmlString);
    }

    private void updateUIText(final String downloadedHtmlCode) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                htmlText.setText(downloadedHtmlCode);
            }
        });
    }

    private void startDownloading() {
        HTTPThread httpThread = new HTTPThread(this);
        httpThread.setUrl(httpAddressText.getText().toString());
        httpThread.start();
    }
}

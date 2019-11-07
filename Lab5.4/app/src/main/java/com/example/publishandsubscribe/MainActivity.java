package com.example.publishandsubscribe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String progress = intent.getStringExtra("progress");
            String status = intent.getStringExtra("status");
            String time = intent.getStringExtra("time");
            if (progress.equals("0"))
                progress = "100";
            textView.append(time + " STATUS: " + status + " " + progress + "% \n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("charged"));
        findViewById(R.id.buttonPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopBroadcasts();
            }
        });
    }

    private void stopBroadcasts() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);

    }
}

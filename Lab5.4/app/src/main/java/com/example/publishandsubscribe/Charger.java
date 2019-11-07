package com.example.publishandsubscribe;

import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Charger extends Thread {

    private int progress = 0;
    private Context context = null;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {
            while (true) {
                progress += 10;
                String status = "Charging";
                if (progress == 100) {
                    status = "Fully charged!";
                    progress = 0;
                }
                statusUpdate(progress, status);
                sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void statusUpdate(int progress, String status) {
        Intent intent = new Intent("charged");
        intent.putExtra("progress", String.valueOf(progress));
        intent.putExtra("status", status);
        intent.putExtra("time", new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}

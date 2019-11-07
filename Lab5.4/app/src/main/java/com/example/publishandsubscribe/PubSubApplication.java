package com.example.publishandsubscribe;

import android.app.Application;

public class PubSubApplication extends Application {

    Charger charger = new Charger();

    @Override
    public void onCreate() {
        super.onCreate();
        charger.setContext(this);
        charger.start();
    }
}

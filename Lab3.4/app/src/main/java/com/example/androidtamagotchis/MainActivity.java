package com.example.androidtamagotchis;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TamagotchiThread.TamagotchiInterface {

    ListView listView;
    ArrayList<TamagotchiThread> tamagotchis;
    TamagotchiArrayAdapter arrayAdapter;
    final static int colorGreen = Color.parseColor("#64953F");
    int deadTamagotchis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        createTamagotchis();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TamagotchiThread feedTamagotchi = tamagotchis.get(i);
                if (feedTamagotchi.feed()) {
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void createTamagotchis() {
        deadTamagotchis = 0;
        tamagotchis = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TamagotchiThread newTamagotchi = new TamagotchiThread(10, i, colorGreen, this);
            tamagotchis.add(newTamagotchi);
            newTamagotchi.start();
            arrayAdapter = new TamagotchiArrayAdapter(this, tamagotchis);
            listView.setAdapter(arrayAdapter);
        }
    }

    @Override
    public void tamagotchiStatus(TamagotchiThread tamagotchi) {
        tamagotchis.set(tamagotchi.getID(), tamagotchi);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void tamagotchisDead() {
        deadTamagotchis++;
        if (deadTamagotchis == 5) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameOverNotification();
                }
            });
        }
    }

    private void gameOverNotification() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("Game over.");
        alertBuilder.setCancelable(false);
        alertBuilder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        createTamagotchis();
                    }
                }
        );
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
}

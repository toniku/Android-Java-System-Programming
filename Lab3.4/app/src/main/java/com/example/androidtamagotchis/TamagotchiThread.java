package com.example.androidtamagotchis;

import android.graphics.Color;

public class TamagotchiThread extends Thread {

    final static private int colorRed = Color.parseColor("#ED2024");
    private TamagotchiInterface callbackInterface;
    private boolean alive;
    private int food, TamagotchiID, statusColor;

    public TamagotchiThread(int startFood, int ID, int currentColor, TamagotchiInterface cInterface) {
        callbackInterface = cInterface;
        food = startFood;
        TamagotchiID = ID;
        alive = true;
        statusColor = currentColor;
    }

    @Override
    public void run() {
        while (alive) {
            try {
                callbackInterface.tamagotchiStatus(this);
                if (food > 20 || food < 1) {
                    alive = false;
                    statusColor = colorRed;
                    callbackInterface.tamagotchisDead();
                    break;
                }
                food--;
                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    boolean feed() {
        if (food < 1 || food > 20) {
            return false;
        } else {
            food += 10;
            return true;
        }
    }

    public int getFood() {
        return this.food;
    }

    public int getID() {
        return this.TamagotchiID;
    }

    public int getStatusColor() {
        return statusColor;
    }

    public interface TamagotchiInterface {
        void tamagotchiStatus(TamagotchiThread tamagotchi);

        void tamagotchisDead();
    }
}

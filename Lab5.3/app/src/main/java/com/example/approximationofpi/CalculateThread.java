package com.example.approximationofpi;

import java.math.BigInteger;

public class CalculateThread extends Thread {

    final BigInteger TWO = BigInteger.valueOf(2);
    final BigInteger THREE = BigInteger.valueOf(3);
    final BigInteger FOUR = BigInteger.valueOf(4);
    final BigInteger SEVEN = BigInteger.valueOf(7);

    BigInteger q = BigInteger.ONE;
    BigInteger r = BigInteger.ZERO;
    BigInteger t = BigInteger.ONE;
    BigInteger k = BigInteger.ONE;
    BigInteger n = BigInteger.valueOf(3);
    BigInteger l = BigInteger.valueOf(3);
    BigInteger nn, nr;
    private boolean first = true;
    private String totalPiValue = "";
    private CalculateThreadInterface callbackInterface;

    public CalculateThread(CalculateThreadInterface threadInterface) {
        callbackInterface = threadInterface;
    }

    @Override
    public void run() {
        try {
            long finishTime = System.currentTimeMillis() + 10000;
            while (System.currentTimeMillis() < finishTime) {
                CalculatePI();
                //Thread.sleep(1);
            }
            callbackInterface.updateUI(totalPiValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CalculatePI() {
        if (FOUR.multiply(q).add(r).subtract(t).compareTo(n.multiply(t)) == -1) {
            totalPiValue += String.valueOf(n);
            //callbackInterface.updateUI(String.valueOf(n));
            if (first) {
                //callbackInterface.updateUI(".");
                totalPiValue += (".");
                first = false;
            }
            nr = BigInteger.TEN.multiply(r.subtract(n.multiply(t)));
            n = BigInteger.TEN.multiply(THREE.multiply(q).add(r)).divide(t).subtract(BigInteger.TEN.multiply(n));
            q = q.multiply(BigInteger.TEN);
            r = nr;

        } else {
            nr = TWO.multiply(q).add(r).multiply(l);
            nn = q.multiply((SEVEN.multiply(k))).add(TWO).add(r.multiply(l)).divide(t.multiply(l));
            q = q.multiply(k);
            t = t.multiply(l);
            l = l.add(TWO);
            k = k.add(BigInteger.ONE);
            n = nn;
            r = nr;
        }
    }

    public interface CalculateThreadInterface {
        void updateUI(String progress);
    }
}

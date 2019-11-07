package com.example.androidthreadsandobservers;

public class ThreadClass extends Thread {

    private ThreadClassInterface callbackInterface;
    private int progress;

    public ThreadClass(ThreadClassInterface threadInterface) {
        callbackInterface = threadInterface;
    }

    @Override
    public void run() {
        try {
            while (true) {
                progress += 10;
                callbackInterface.progressUpdate(progress);
                if (progress == 100) {
                    callbackInterface.progressCompleted();
                }
                sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public interface ThreadClassInterface {
        void progressUpdate(int progress);

        void progressCompleted();
    }
}

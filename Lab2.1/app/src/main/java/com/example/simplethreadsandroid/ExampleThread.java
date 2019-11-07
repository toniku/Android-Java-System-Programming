package com.example.simplethreadsandroid;

public class ExampleThread extends Thread {

    private ExampleThreadReporterInterface callbackInterface;

    public ExampleThread(ExampleThreadReporterInterface cb) {
        callbackInterface = cb;
    }

    public void run() {
        try {
            while (true) {
                if (Thread.interrupted()) {
                    callbackInterface.threadStopped();
                } else {
                    callbackInterface.threadRunning();
                }
                sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface ExampleThreadReporterInterface {
        void threadRunning();

        void threadStopped();
    }
}
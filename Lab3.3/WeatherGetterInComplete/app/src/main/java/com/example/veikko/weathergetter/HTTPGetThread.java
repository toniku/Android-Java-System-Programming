package com.example.veikko.weathergetter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGetThread extends Thread {

    private OnRequestDoneInterface callbackInterface;
    private String urlAddress;

    HTTPGetThread(String url, OnRequestDoneInterface httpInterface) {
        callbackInterface = httpInterface;
        urlAddress = url;
    }

    @Override
    public void run() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            URL url = new URL(urlAddress);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            String htmlString = stringBuilder.toString();
            callbackInterface.onRequestDone(htmlString);
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            callbackInterface.onRequestError();

        }
    }

    public interface OnRequestDoneInterface {
        void onRequestDone(String data);
        void onRequestError();
    }
}

package com.example.androidhttpfiles;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPThread extends Thread {

    private String finalUrlAddress;

    private HTTPThreadReporterInterface callbackInterface;

    HTTPThread(HTTPThreadReporterInterface cb) {
        callbackInterface = cb;
    }

    public interface HTTPThreadReporterInterface {
        void finishedDownloading(String htmlString);
    }

    public void run() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            URL url = new URL(finalUrlAddress);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            String htmlString = stringBuilder.toString();
            callbackInterface.finishedDownloading(htmlString);
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setUrl(String urlAddress) {
        this.finalUrlAddress = urlAddress;
    }
}


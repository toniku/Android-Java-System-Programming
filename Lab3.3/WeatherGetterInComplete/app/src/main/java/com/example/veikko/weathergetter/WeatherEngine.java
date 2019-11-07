package com.example.veikko.weathergetter;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class WeatherEngine implements HTTPGetThread.OnRequestDoneInterface {
    private static final double KELVIN_CONVERT = 273.15;
    private String temperature, iconId;
    private WeatherDataAvailableInterface uiCallback;

    // Constructor
    WeatherEngine(WeatherDataAvailableInterface callbackInterface) {
        this.uiCallback = callbackInterface;
    }

    String getTemperature() {
        return temperature;
    }

    String getIconId() {
        return iconId;
    }

    void getWeatherData(String city) {
        String urlAddress = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=65dbec3aae5e5bf9000c7a956c8b76f6";
        HTTPGetThread getter = new HTTPGetThread(urlAddress, this);
        getter.start();
    }

    @Override
    public void onRequestError() {
        uiCallback.weatherDataNotFound("Error: City not found!");
    }

    @Override
    public void onRequestDone(String data) {
        try {
            Map<String, Object> parsed = JsonUtils.jsonToMap(new JSONObject(data));
            Map mainElement = (Map) parsed.get("main");
            double temp = (double) mainElement.get("temp");
            double tempInC = temp - KELVIN_CONVERT;
            this.temperature = String.format("%.1f", tempInC);
            ArrayList<Map<String, Object>> array = (ArrayList<Map<String, Object>>) parsed.get("weather");
            Map<String, Object> weatherElement = array.get(0);
            iconId = (String) weatherElement.get("icon");
            uiCallback.weatherDataAvailable();
        } catch (Exception e) {
            e.printStackTrace();
            uiCallback.weatherDataNotFound("Error parsing data");
        }
    }


    // This interface is used to report data back to UI
    public interface WeatherDataAvailableInterface {
        // This method is called back in background thread.
        void weatherDataAvailable();

        void weatherDataNotFound(String errorMessage);
    }

}

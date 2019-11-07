package com.example.lunchmenu;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Lunch> lunches = new ArrayList<>();
    String url = "https://www.amica.fi/api/restaurant/menu/day?date=";
    String restaurant = "&language=en&restaurantPageId=66287";
    String dateURL, dateTV;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lunch Menu");
        listView = findViewById(R.id.lunchListView);
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormatForUrl = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatForTv = new SimpleDateFormat("dd-MM-yyyy");
        dateURL = dateFormatForUrl.format(date);
        dateTV = dateFormatForTv.format(date);
        makeRequest();
    }

    public void makeRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlAddress = url + dateURL + restaurant;
        Log.d("DOWNLOADURL:", urlAddress);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlAddress, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("TAG", response.toString());
                try {
                    JSONObject object = response.getJSONObject("LunchMenu");
                    JSONArray jsonArray = object.getJSONArray("SetMenus");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        ArrayList<String> lunchList = new ArrayList<>();
                        String lunch1 = "";
                        String lunch2 = "";
                        JSONObject mealsObject = jsonArray.getJSONObject(i);
                        JSONArray mealsArray = mealsObject.getJSONArray("Meals");

                        for (int meals = 0; meals < mealsArray.length(); meals++) {
                            JSONObject mealObject = mealsArray.getJSONObject(meals);
                            lunchList.add(mealObject.getString("Name"));
                            if (mealsArray.length() > 0) {
                                lunch1 = mealsObject.getString("Name");
                            }
                        }


                        Lunch lunchToAdd = new Lunch(lunch1, lunch2);
                        lunches.add(lunchToAdd);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setupView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error,", "Error loading Volley data!");
                Log.d("VolleyError", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        queue.add(jsonObjectRequest);
    }

    public void setupView() {
        final ArrayAdapter<Lunch> adapter;
        adapter = new LunchArrayAdapter(this, lunches);
        listView.setAdapter(adapter);
    }
}

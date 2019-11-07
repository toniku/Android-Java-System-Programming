package com.example.a3rdpartylibraries;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageLoader {

    private Context context;
    private ImageLoader instance = null;
    private ImageLoaderInterface callbackInterface;

    public ImageLoader(ImageLoaderInterface imageLoaderInterface) {
        callbackInterface = imageLoaderInterface;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void jsonParse(String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject imageObject = jsonArray.getJSONObject(i);
                        String urlAddress = imageObject.getString("media");
                        String parsedUrlAddress = urlAddress.substring(6, urlAddress.length() - 2);
                        Log.d("TAG", parsedUrlAddress);
                        callbackInterface.imageUrlDownloaded(parsedUrlAddress);
                    }
                    callbackInterface.setupView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    public interface ImageLoaderInterface {
        void imageUrlDownloaded(String url);

        void setupView();
    }
}

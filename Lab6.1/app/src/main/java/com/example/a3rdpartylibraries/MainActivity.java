package com.example.a3rdpartylibraries;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ImageLoader.ImageLoaderInterface {

    EditText imageTag;
    private ListView listView;
    private ArrayList<Image> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loadIntroAlways();
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.imageListView);
        imageTag = findViewById(R.id.editTextImageTag);
        findViewById(R.id.buttonLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                images = new ArrayList<>();
                loadImages();
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception ignored) {
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Image selectedImage = images.get(i);
                String imageURL = selectedImage.getImgUrlAddress();
                Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
                intent.putExtra("ImageURL", imageURL);
                startActivity(intent);
            }
        });
    }

    private void loadImages() {
        ImageLoader imageLoader = new ImageLoader(this);
        imageLoader.setContext(this);
        imageLoader.jsonParse("https://api.flickr.com/services/feeds/photos_public.gne?nojsoncallback=?&format=json&tags=" + imageTag.getText().toString());
    }


    @Override
    public void imageUrlDownloaded(String url) {
        Image imageToAdd = new Image(url);
        images.add(imageToAdd);
    }

    @Override
    public void setupView() {
        final ArrayAdapter<Image> adapter;
        adapter = new ImageArrayAdapter(this, images);
        listView.setAdapter(adapter);
    }


    private void loadIntroAlways() {
        final Intent intent = new Intent(MainActivity.this, IntroActivity.class);
        startActivity(intent);
    }

    private void loadIntroOnce() {
        Thread sharedPreferencesThread = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
                if (isFirstStart) {
                    final Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    });

                    SharedPreferences.Editor e = getPrefs.edit();
                    //Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        sharedPreferencesThread.start();
    }
}

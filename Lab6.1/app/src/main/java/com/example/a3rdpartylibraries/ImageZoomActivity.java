package com.example.a3rdpartylibraries;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;


public class ImageZoomActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagezoom);
        Intent intent = getIntent();
        final String imageURL = intent.getExtras().getString("ImageURL");
        PhotoView photoView = findViewById(R.id.imageZoomView);
        Picasso.get().load(imageURL).fit().centerCrop().into(photoView);
    }
}

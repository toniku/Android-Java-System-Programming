package com.example.a3rdpartylibraries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        KenBurnsView kenBurnsView = findViewById(R.id.imageView);

        AccelerateDecelerateInterpolator adi = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(4000, adi);
        kenBurnsView.setTransitionGenerator(generator);


        Intent intent = getIntent();
        final String imageURL = intent.getExtras().getString("ImageURL");
        Picasso.get().load(imageURL).fit().centerCrop().into(kenBurnsView);

        kenBurnsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                panAndZoom(imageURL);
            }
        });
    }

    private void panAndZoom(String imageURL) {
        Intent panAndZoomIntent = new Intent(getApplicationContext(), ImageZoomActivity.class);
        panAndZoomIntent.putExtra("ImageURL", imageURL);
        startActivity(panAndZoomIntent);
    }
}

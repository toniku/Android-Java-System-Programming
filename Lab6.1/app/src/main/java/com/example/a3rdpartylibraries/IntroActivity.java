package com.example.a3rdpartylibraries;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

public class IntroActivity extends AppIntro {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFadeAnimation();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        //addSlide(firstFragment);
        //addSlide(secondFragment);
        //addSlide(thirdFragment);
        //addSlide(fourthFragment);

        // Instead of fragments, you can also use our default slide.
        // Just create a `SliderPage` and provide title, description, background and image.
        // AppIntro will do the rest.
        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle("Awesome image viewer");
        sliderPage.setDescription("With this app you can view images from Flickr");
        sliderPage.setImageDrawable(R.drawable.reader_mode);
        sliderPage.setBgColor(Color.parseColor("#3E4EB8"));
        addSlide(AppIntroFragment.newInstance(sliderPage));

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("Find images with tags");
        sliderPage1.setDescription("You can find images with tags, example 'dogs' ");
        sliderPage1.setImageDrawable(R.drawable.reader_mode);
        sliderPage1.setBgColor(Color.parseColor("#3E4EB8"));
        addSlide(AppIntroFragment.newInstance(sliderPage1));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("Have fun!");
        sliderPage2.setDescription("Thank you for downloading the app.\nHope you like it!");
        sliderPage2.setImageDrawable(R.drawable.reader_mode);
        sliderPage2.setBgColor(Color.parseColor("#3E4EB8"));
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        //setVibrate(true);
        //setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
        // Do something when users tap on Skip button.
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
        //mainActivityIntent();
    }

    private void mainActivityIntent() {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
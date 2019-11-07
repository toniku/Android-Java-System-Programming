package com.example.annotations;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.textView)
    TextView textView;

    @Background(id = "cancel")
    void thread() {
        int progress = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                progress += 10;
                Thread.sleep(1000);
                updateUI(progress);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.buttonStart)
    void start() {
        clearText();
        thread();
    }

    @Click(R.id.buttonPause)
    void stop() {
        BackgroundExecutor.cancelAll("cancel", true);
    }


    @UiThread
    protected void updateUI(int progress) {
        textView.append("Charged " + progress + " %\n");
    }

    @UiThread
    protected void clearText() {
        textView.setText("");
    }
}

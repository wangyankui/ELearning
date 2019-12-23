package com.example.a777;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;

public class JSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        getWindow().setEnterTransition(new Fade().setDuration(2000));
        getWindow().setExitTransition(new Fade().setDuration(2000));
    }

    public void click(View view) {
        finish();
    }
}

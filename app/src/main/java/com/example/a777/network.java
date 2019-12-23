package com.example.a777;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Fade;
import android.view.View;

public class network extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        getWindow().setEnterTransition(new Fade().setDuration(2000));
        getWindow().setExitTransition(new Fade().setDuration(2000));
    }

    public void click(View view) {
        finish();
    }
}

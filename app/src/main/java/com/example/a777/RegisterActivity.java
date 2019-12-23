package com.example.a777;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;

import android.view.animation.AccelerateInterpolator;
import com.example.animationUtils.TransitionController;
import com.example.animationUtils.ViewAnimationCompatUtils;
import com.example.listener.TransitionCustomListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegisterActivity extends AppCompatActivity {

    FloatingActionButton fab;
    CardView cvAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        cvAdd = (CardView) findViewById(R.id.cv_add);

        cvAdd.setVisibility(View.INVISIBLE);
        TransitionController.getInstance().setEnterListener(new TransitionCustomListener() {
            @Override
            public void onTransitionStart(Animator animator) {
                cvAdd.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onTransitionEnd(Animator animator) {
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Animator animator) {

            }
        });
        TransitionController.getInstance().show(this,getIntent());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
//                RegisterActivity.super.onBackPressed();
                TransitionController.getInstance().exitActivity(RegisterActivity.this);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

}

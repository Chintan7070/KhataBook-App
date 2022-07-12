package com.example.khatabook;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        // Declaring the animation view
        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.addAnimatorUpdateListener((animation) -> {
                            // Do something.
                        });
        animationView.playAnimation();

        if (animationView.isAnimating()) {
            // Do something.
        }


        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> {
            animationView.setProgress((Float) animation.getAnimatedValue());
                });
        animator.start();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
            }
        },3000);


    }
}
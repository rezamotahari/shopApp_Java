package com.example.shopapp.Activity;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.shopapp.R;
import com.wang.avi.AVLoadingIndicatorView;


public class Splash_Screen extends AppCompatActivity {

    AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash__screen);

        avi = findViewById(R.id.avi);

        startAnim();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //  stopAnim();
                startActivity(new Intent(Splash_Screen.this,HomeActivity.class));
                finish();
            }
        },2000);
    }

    void startAnim(){
        avi.show();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        avi.hide();
        // or avi.smoothToHide();
    }

}

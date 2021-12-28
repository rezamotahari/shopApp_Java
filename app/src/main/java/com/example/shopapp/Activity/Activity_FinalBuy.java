package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shopapp.Class.put;
import com.example.shopapp.R;

import java.lang.invoke.MethodType;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class Activity_FinalBuy extends AppCompatActivity {
    TextView textallprice,textrefid,textfinish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__final_buy);

        textallprice = findViewById(R.id.textALlpricefinal);
        textrefid = findViewById(R.id.textrefidFinal);
        textfinish = findViewById(R.id.textfinish);
        textfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textallprice.setText(getIntent().getStringExtra(put.allprice));
        textrefid.setText(getIntent().getStringExtra(put.refid));


    }
  //  @Override
//    protected void attachBaseContext(Context newBase) {
//    //  super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }
  protected void attachBaseContext(Context newBase) {
      super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
  }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

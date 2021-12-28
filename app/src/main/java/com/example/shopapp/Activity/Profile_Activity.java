package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopapp.Class.put;
import com.example.shopapp.R;

public class Profile_Activity extends AppCompatActivity {
    ImageView img_back;
    TextView txt_toolbar;
    AppCompatButton btn_exit, btn_favarit, btn_edit;

    SharedPreferences  sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        findview();

    }

    private void onclick() {
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences=getSharedPreferences(put.shared,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(put.email,"ورود/عضویت");
                editor.putString(put.image," ");

                editor.apply();
                Intent intent=new Intent();
                intent.putExtra(put.email,"ورود/عضویت");
                intent.putExtra(put.image," ");
                setResult(RESULT_OK,intent);
                finish();

            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Profile_Activity.this,EditProfile_Acvivity.class);
                startActivity(intent);
            }
        });


    }

    private void findview() {

        img_back = findViewById(R.id.img_back_toolbar2);
        txt_toolbar = findViewById(R.id.txt_title_toolbar2);
        txt_toolbar.setText("پروفایل");
        btn_edit = findViewById(R.id.btn_edit_profile);
        btn_favarit = findViewById(R.id.btn_favarit_profile);
        btn_exit = findViewById(R.id.btn_exit_profile);
        onclick();

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
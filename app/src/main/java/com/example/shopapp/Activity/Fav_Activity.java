package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopapp.Adapter.FavAdapter;
import com.example.shopapp.Class.DBfav;
import com.example.shopapp.Class.DbSqlite;
import com.example.shopapp.Model.FavModel;
import com.example.shopapp.Model.ModelFav;
import com.example.shopapp.R;

import java.util.ArrayList;
import java.util.List;

public class Fav_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    FavAdapter adapterFav;




    List<ModelFav> modelFavs = new ArrayList<>();
    ImageView imageback;
    TextView texttitel;
    DbSqlite dbSqlite = new DbSqlite(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_);
        texttitel = findViewById(R.id.txt_title_toolbar2);
        texttitel.setText("علاقه مندی ها");
        imageback = findViewById(R.id.img_back_toolbar2);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.recy_fav);

    }

    @Override
    public void finish() {
        super.finish();
       overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    @Override
    protected void onStart() {
        super.onStart();
        modelFavs = dbSqlite.showData();
        adapterFav = new FavAdapter(getApplicationContext(),modelFavs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapterFav);
        recyclerView.hasFixedSize();
    }
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

}

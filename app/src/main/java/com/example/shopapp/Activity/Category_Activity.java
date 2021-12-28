package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.shopapp.Adapter.CategoryAdapter;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Model.CategoryModel;
import com.example.shopapp.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Category_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModels = new ArrayList<>();
    ImageView img_back;
    TextView txt_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_);


        img_back = findViewById(R.id.img_back_toolbar2);
        txt_toolbar = findViewById(R.id.txt_title_toolbar2);
        txt_toolbar.setText(" دسته بندی محصولات");

        recyclerView = findViewById(R.id.recycategory);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        categoryAdapter = new CategoryAdapter(categoryModels, getApplicationContext());
        recyclerView.setAdapter(categoryAdapter);
        getcategory();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getcategory() {
        String url = "http://s.rezamotahari1375.ir/shop/category.php";

        ProgressDialog progressDialog = new ProgressDialog(Category_Activity.this);
        progressDialog.setMessage("در حال ارسال اطلاعات");
        progressDialog.show();

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                CategoryModel[] category = gson.fromJson(response.toString(), CategoryModel[].class);
                for (int i = 0; i < category.length; i++) {
                    categoryModels.add(category[i]);
                    categoryAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();

                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Category_Activity.this, " دریافت اطلاعات با مشکل مواجه هشد", Toast.LENGTH_SHORT).show();


            }
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
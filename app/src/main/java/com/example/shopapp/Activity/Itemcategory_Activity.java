package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shopapp.Adapter.ItemcategoryAdapter;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.ItemcategoryModel;
import com.example.shopapp.Model.ModelFree;
import com.example.shopapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Itemcategory_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemcategoryAdapter itemcategoryAdapter;
    List<ItemcategoryModel> itemcategoryModels = new ArrayList<>();
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemcategory_);
        recyclerView = findViewById(R.id.recy_itemcategory);
        itemcategoryAdapter = new ItemcategoryAdapter(getApplicationContext(), itemcategoryModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(itemcategoryAdapter);
        recyclerView.hasFixedSize();
        id=getIntent().getStringExtra(put.id);
        getitemcategory(id);


    }

    private void getitemcategory(String id) {

        String url = "http://s.rezamotahari1375.ir/shop/getcategory.php";
        ProgressDialog progressDialog = new ProgressDialog(Itemcategory_Activity.this);
        progressDialog.setMessage("در حال دریافت اطلاعات");
        progressDialog.show();
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ItemcategoryModel[] itemcategoryModels = gson.fromJson(response.toString(), ItemcategoryModel[].class);
                for (int i = 0; i < itemcategoryModels.length; i++) {
                    Itemcategory_Activity.this.itemcategoryModels.add(itemcategoryModels[i]);
                    itemcategoryAdapter.notifyDataSetChanged();


                }
                progressDialog.dismiss();


            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Itemcategory_Activity.this, "دریافت اطلاعات با مشکل مواجه شد", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap();
                map.put(put.id, id);
                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.shopapp.Adapter.ItemAdapter;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.ItemModel;
import com.example.shopapp.Model.ModelOnly;
import com.example.shopapp.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    List<ItemModel> itemModels = new ArrayList<>();
    String id;
    CardView card_sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_);
        recyclerView = findViewById(R.id.recy_item);
        itemAdapter = new ItemAdapter(getApplicationContext(), itemModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(itemAdapter);
        id = getIntent().getStringExtra(put.id);
        getItem(id);
        card_sort = findViewById(R.id.card_sort_item);
        card_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter();
            }
        });


    }

    private void getItem(final String id) {

        String url = "http://s.rezamotahari1375.ir/shop/getitemcategory.php";
        final ProgressDialog progressDialog = new ProgressDialog(Item_Activity.this);
        progressDialog.show();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                ItemModel[] items = gson.fromJson(response.toString(), ItemModel[].class);
                for (int i = 0; i < items.length; i++) {
                    itemModels.add(items[i]);
                    itemAdapter.notifyDataSetChanged();

                }
                progressDialog.dismiss();

            }
        };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Item_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        };
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put(put.id, id);
                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void filter() {
        Dialog dialog = new Dialog(Item_Activity.this);
        dialog.setContentView(R.layout.dialog);
        RadioButton rd1, rd2, rd3, rd4;
        rd1 = dialog.findViewById(R.id.rd1);
        rd2 = dialog.findViewById(R.id.rd2);
        rd3 = dialog.findViewById(R.id.rd3);
        rd4 = dialog.findViewById(R.id.rd4);



        rd1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Toast.makeText(Activity_Item.this, "1", Toast.LENGTH_SHORT).show();

                itemModels.clear();
                itemAdapter.notifyDataSetChanged();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFilter(id,"visit");
                        dialog.cancel();
                    }
                },100);

            }
        });

        rd2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                itemModels.clear();
                itemAdapter.notifyDataSetChanged();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFilter(id,"sale");
                        dialog.cancel();
                    }
                },100);
            }
        });
        rd3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                itemModels.clear();
                itemAdapter.notifyDataSetChanged();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFilter(id,"priceUp");
                        dialog.cancel();
                    }
                },100);
            }
        });
        rd4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                itemModels.clear();
                itemAdapter.notifyDataSetChanged();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFilter(id,"priceDown");
                        dialog.cancel();
                    }
                },100);
            }
        });
        dialog.show();

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        width = (int) ((width) * ((double) 4 / 5));


        dialog.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
    private void getFilter(final String id , final String param)
    {
        String url = "http://s.rezamotahari1375.ir/shop/filter.php";

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                ItemModel[] items = gson.fromJson(response.toString(), ItemModel[].class);
                for (int i = 0; i < items.length; i++) {
                    itemModels.add(items[i]);
                    itemAdapter.notifyDataSetChanged();

                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Item_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        };
        StringRequest request = new StringRequest(Request.Method.POST,url,listener,errorListener)
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("idcat",id);
                map.put("param",param);
                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
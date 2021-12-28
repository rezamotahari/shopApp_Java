package com.example.shopapp.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shopapp.Adapter.AdapterOrder;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.ModelOrder;
import com.example.shopapp.R;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class Activity_Order extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterOrder adapterOrder;
    List<ModelOrder> modelOrders = new ArrayList<>();
    String session;
    ImageView imageback;
    TextView texttitel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__order);
        texttitel = findViewById(R.id.txt_title_toolbar2);
        texttitel.setText("سفارشات");
        imageback = findViewById(R.id.img_back_toolbar2);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences preferences = getSharedPreferences(put.shared, MODE_PRIVATE);
        session = preferences.getString(put.email, "ورود/عضویت");
        recyclerView = findViewById(R.id.recyOrder);
        adapterOrder = new AdapterOrder(getApplicationContext(), modelOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapterOrder);
        getOrder(session);
    }

    private void getOrder(final String email) {
        String url = "http://s.rezamotahari1375.ir/shop/getbuy.php";
        final ProgressDialog progressDialog = new ProgressDialog(Activity_Order.this);
        progressDialog.show();


        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString(put.id);
                        String price = object.getString(put.allprice);
                        String refid = object.getString(put.refid);
                        String number = object.getString(put.number);
                        String date = object.getString(put.date);
                        String email = object.getString(put.email);
                        modelOrders.add(new ModelOrder(email, refid, price, number, date));
                        adapterOrder.notifyDataSetChanged();


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();


            }
        };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener)

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put(put.email, email);
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
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }
protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
}
}

package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shopapp.Adapter.BasketAdapter;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.ONloadprice;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.BasketModel;
import com.example.shopapp.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class Basket_Activity extends AppCompatActivity {

    BasketAdapter basketAdapter;
    RecyclerView recyclerView;
    List<BasketModel> basketModels = new ArrayList<>();
    String cookei;
    int totalprice=0;
    TextView txt_totalprice;
    String name;
    String number;
    CardView cardpayment;
    int nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_);
        findview();
    }

    private void findview() {
        recyclerView = findViewById(R.id.recy_basket);
        cardpayment=findViewById(R.id.card_payment_basket);
        basketAdapter = new BasketAdapter(getApplicationContext(), basketModels);
        basketAdapter.setoNloadprice(new ONloadprice() {
            @Override
            public void Onloadprice() {
                recreate();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(basketAdapter);
        txt_totalprice=findViewById(R.id.txt_totalallprice_basket);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Vazir-Medium-FD-WOL.ttf");
        txt_totalprice.setTypeface(typeface);

        SharedPreferences preferences = getSharedPreferences(put.shared, MODE_PRIVATE);
        cookei = preferences.getString(put.email, "ورود/عضویت");
        getbasket(cookei);
        cardpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cookei.equals("ورود/عضویت")){
                    Toast.makeText(Basket_Activity.this, "وارد حساب کاربری خود شوید", Toast.LENGTH_SHORT).show();
                }else if (txt_totalprice.getText().toString().equals("0تومان")) {

                        Toast.makeText(Basket_Activity.this, "شما محصولی برای خرید ندارید", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(Basket_Activity.this, WebGet_Activity.class);
                        intent.putExtra("total", totalprice);
                        intent.putExtra("desc", name);
                        intent.putExtra(put.number, nm);
                        startActivity(intent);
                        finish();
                    }

            }
        });

    }

    private void getbasket(String email) {
        String url = "http://s.rezamotahari1375.ir/shop/getbasket.php";
        ProgressDialog progressDialog = new ProgressDialog(Basket_Activity.this);
        progressDialog.show();


        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id=object.getString(put.id);
                         name = object.getString(put.name);
                        String price = object.getString(put.price);
                        String image = object.getString(put.image);
                        String color = object.getString(put.color);
                        String garanty = object.getString(put.garanty);
                        String allprice = object.getString(put.allprice);
                        number = object.getString(put.number);
                        totalprice+=Integer.parseInt(allprice);
                        nm +=Integer.parseInt(number);
                        basketModels.add(new BasketModel(image, number, color, garanty, price, allprice, name,Integer.parseInt(id)));
                        basketAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
                DecimalFormat decimalFormat = new DecimalFormat("###,###");
                String pricee = decimalFormat.format(totalprice);
                txt_totalprice.setText(pricee+""+"تومان ");


            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Basket_Activity.this, "دریافت اطلاعات با مشکل مواجه شد", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put(put.email, email);
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
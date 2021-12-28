package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.put;
import com.example.shopapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Wait_Activity extends AppCompatActivity {
    ProgressBar progressBar;
    String id, name, image, price, view, lable,
            date, only, sale, color, garanty, description,freeprice,cat,finalrating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_);
        progressBar = findViewById(R.id.progress_wait);

        id = getIntent().getStringExtra(put.id);
        freeprice=getIntent().getStringExtra(put.freeprice);

        sendId(id);


    }


    private void sendId(final String idd) {

        String url = "http://s.rezamotahari1375.ir/shop/getdataproduct.php";

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        id = jsonObject.getString("id");
                        image = jsonObject.getString("image");
                        name=jsonObject.getString("name");
                        price = jsonObject.getString("price");
                       cat = jsonObject.getString("cat");
                        view = jsonObject.getString("view");
                        lable = jsonObject.getString("lable");
                        date = jsonObject.getString("date");
                        only = jsonObject.getString("only");
                        sale = jsonObject.getString("sale");
                        color = jsonObject.getString("color");
                        garanty = jsonObject.getString("garanty");
                        description = jsonObject.getString("description");
                        finalrating=jsonObject.getString("finalrating");
                        float f = Float.parseFloat(finalrating);
                       // Toast.makeText(Wait_Activity.this, price, Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }



                Intent intent = new Intent(Wait_Activity.this,Show_Activity.class);
                intent.putExtra(put.id,id);
                intent.putExtra(put.image,image);
                intent.putExtra(put.name,name);
                intent.putExtra(put.view,view);
                intent.putExtra(put.price,price);
                intent.putExtra(put.label,lable);
                intent.putExtra(put.date,date);
                intent.putExtra(put.cat,cat);

                intent.putExtra(put.sale,sale);
                intent.putExtra(put.color,color);
             //   intent.putExtra(put.cat,cat);
                intent.putExtra(put.garanty,garanty);
                intent.putExtra(put.description,description);

               intent.putExtra(put.rating,finalrating);
                if (!freeprice.equals(""))
                    intent.putExtra(put.freeprice,freeprice);
               else
                    intent.putExtra(put.freeprice,"");

                startActivity(intent);

                finish();

                progressBar.setVisibility(View.GONE);
            }

        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(Wait_Activity.this, "ارسال ناموفق", Toast.LENGTH_SHORT).show();

            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put(put.id, idd);
                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }
}
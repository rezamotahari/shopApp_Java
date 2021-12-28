package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.TypeWriter;
import com.example.shopapp.Class.put;
import com.example.shopapp.R;

import java.util.HashMap;
import java.util.Map;

public class Properties_Activity extends AppCompatActivity {
    TextView txtname;
    TypeWriter txtproperties;
    String id,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_);
        txtname=findViewById(R.id.txt_title_properties);
        txtproperties=findViewById(R.id.txt_properties_properties);
        id=getIntent().getStringExtra(put.id);
        name=getIntent().getStringExtra(put.name);
        txtname.setText(name);
        getproperties(id);
    }

    private void getproperties(String id) {

        String url = "http://s.rezamotahari1375.ir/shop/getpropertise.php";
        ProgressDialog progressDialog = new ProgressDialog(Properties_Activity.this);
        progressDialog.setMessage("در حال دریافت اطلاعات");
        progressDialog.show();
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txtproperties.setText("");
                txtproperties.setCharacterDelay(30);
                txtproperties.animateText(response.toString().replace("<br/>",""));
               //txtproperties.setText(response.toString().replace("<br/>",""));
               progressDialog.dismiss();


            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Properties_Activity.this, "دریافت اطلاعات با مشکل مواجه شد", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap();
                map.put(put.id,id);
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
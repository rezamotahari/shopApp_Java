package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.example.shopapp.Adapter.FreeAdapter;
import com.example.shopapp.Adapter.OnlyAdapter;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Model.ModelFree;
import com.example.shopapp.Model.ModelOnly;
import com.example.shopapp.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Activity_AllProduct extends AppCompatActivity {

    RecyclerView recyall;
    OnlyAdapter adapterOnly;
    List<ModelOnly> modelOnlies =new ArrayList<>();
    FreeAdapter adapterFree ;
    List<ModelFree> modelFrees = new ArrayList<>();
    String idText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__all_product);
        recyall = findViewById(R.id.recyAll);
        idText = getIntent().getStringExtra("idText");
        if (idText.equals("1"))
        {
            adapterOnly = new OnlyAdapter(getApplicationContext(),modelOnlies);
            recyall.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
            recyall.setAdapter(adapterOnly);
            getOnlyData();
        }
        else if (idText.equals("2"))
        {
            adapterFree = new FreeAdapter(getApplicationContext(),modelFrees);
            recyall.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
            recyall.setAdapter(adapterFree);
            getFreeData();
        }


    }
    private void getFreeData() {

        String url = "http://s.rezamotahari1375.ir/shop/product.php";
//        final ProgressDialog progressDialog = new ProgressDialog(Home_Activity.this);
//        progressDialog.show();

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Gson gson = new Gson();
                ModelFree[] frees = gson.fromJson(response.toString(), ModelFree[].class);
                for (int i = 0; i < frees.length; i++) {
                    modelFrees.add(frees[i]);
                    adapterFree.notifyDataSetChanged();

                }
                //  progressDialog.dismiss();

            }
        };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Activity_AllProduct.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                // progressDialog.dismiss();
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
    private void getOnlyData() {

        String url = "http://s.rezamotahari1375.ir/shop/getonly.php";
//        final ProgressDialog progressDialog = new ProgressDialog(Home_Activity.this);
//        progressDialog.show();

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Gson gson = new Gson();
                ModelOnly[] onlies = gson.fromJson(response.toString(), ModelOnly[].class);
                for (int i = 0; i < onlies.length; i++) {
                    modelOnlies.add(onlies[i]);
                    adapterOnly.notifyDataSetChanged();

                }
                //progressDialog.dismiss();

            }
        };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Activity_AllProduct.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                // progressDialog.dismiss();
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

}

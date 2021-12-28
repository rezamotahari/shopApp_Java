package com.example.shopapp.Activity;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.shopapp.Adapter.AdapterSearch;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.ModelSearch;
import com.example.shopapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class Activity_Search extends AppCompatActivity {

    EditText edsearch;
    RecyclerView recySearch;
    AdapterSearch adapterSearch ;
    List<ModelSearch> modelSearches = new ArrayList<>();
    String id,image, name,price,freeprice, lable,cat;
    boolean array = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__search);

        findview();
    }
    private void findview()
    {
        edsearch = findViewById(R.id.edsearch);
        recySearch = findViewById(R.id.recysearch);
        adapterSearch = new AdapterSearch(getApplicationContext(),modelSearches);
        recySearch.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recySearch.setAdapter(adapterSearch);
        recySearch.hasFixedSize();
        editor();
    }
    private void editor()
    {
        edsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH)
                {
                    array=modelSearches.isEmpty();
                    if (array)
                    {
                        getsearch(edsearch.getText().toString());
                        edsearch.setText("");
                    }
                    else
                    {
                        modelSearches.clear();
                        adapterSearch.notifyDataSetChanged();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                getsearch(edsearch.getText().toString());
                                edsearch.setText("");
                            }
                        },100);
                    }

//                    Toast.makeText(Activity_Search.this, edsearch.getText().toString(), Toast.LENGTH_SHORT).show();

                }

                return false;
            }
        });

    }
    private void getsearch(final String search)
    {
        String url ="http://s.rezamotahari1375.ir/shop/serarch.php?="+search;
        final ProgressDialog progressDialog = new ProgressDialog(Activity_Search.this);
        progressDialog.show();


        Response.Listener<JSONObject>  listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("records");
                    for (int i = 0 ; i <jsonArray.length();i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        id = object.getString(put.id);
                        image = object.getString(put.image);
                        name = object.getString(put.name);
                        price = object.getString(put.price);
                        freeprice = object.getString(put.freeprice);
                        lable = object.getString(put.label);
                        cat = object.getString(put.cat);

                        modelSearches.add(new ModelSearch(Integer.parseInt(id),image, name,price,cat, lable,freeprice));
                        adapterSearch.notifyDataSetChanged();

                    }
                }


                catch (Exception e)
                {
                    e.printStackTrace();
                }
                progressDialog.dismiss();

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Activity_Search.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        };
        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.GET,url,null,listener,errorListener);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void finish() {
        super.finish();
       // overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
//    @Override
//    protected void attachBaseContext(Context newBase) {
//       super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }
}

package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.shopapp.Adapter.CommentAdapter;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.CommentModel;
import com.example.shopapp.Model.ModelOnly;
import com.example.shopapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Showcomment_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    CommentAdapter commentAdapter;
    ArrayList<CommentModel> commentModels = new ArrayList<>();
    FloatingActionButton floatingActionButton;
    TextView txt_title;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcomment_);
        recyclerView = findViewById(R.id.recy_showcomment);
        commentAdapter = new CommentAdapter(getApplicationContext(), commentModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(commentAdapter);
        floatingActionButton = findViewById(R.id.flot_addcom_com);
        txt_title = findViewById(R.id.txt_title_toolbar2);
        txt_title.setText("مشاهده کامنت");
        img_back = findViewById(R.id.img_back_toolbar2);


        String id = getIntent().getStringExtra(put.id);
        getcomment(id);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Showcomment_Activity.this, Sendcomment_Activity.class);
                intent.putExtra(put.id, id);
                startActivity(intent);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


    private void getcomment(String id) {
        String url = "http://s.rezamotahari1375.ir/shop/showcomment.php";
        ProgressDialog progressDialog = new ProgressDialog(Showcomment_Activity.this);
        progressDialog.setMessage("در حال دریافت اطلاعات");
        progressDialog.show();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CommentModel[] commentModelss = gson.fromJson(response.toString(), CommentModel[].class);
                for (int i = 0; i < commentModelss.length; i++) {
                    commentModels.add(commentModelss[i]);
                    commentAdapter.notifyDataSetChanged();


                }
                progressDialog.dismiss();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Showcomment_Activity.this, " دریافت اطلاعات با مشکل مواجه هشد", Toast.LENGTH_SHORT).show();


            }
        };

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap();
                hashMap.put(put.id, id);
                return hashMap;
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
package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.put;
import com.example.shopapp.R;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.HashMap;
import java.util.Map;

public class Sendcomment_Activity extends AppCompatActivity {
    EditText edt_com, edt_pos, edt_nag;
    AppCompatButton btn_sendcom;
    SharedPreferences preferences;
    String imgeuser, seesion,id;
    ScaleRatingBar ratingBar;
    TextView txt_title;
    ImageView img_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendcomment_);
        findview();


        preferences = getSharedPreferences(put.shared, MODE_PRIVATE);
        seesion = preferences.getString(put.email, "ورود/عضویت");
        imgeuser = preferences.getString(put.image, " ");
        id=getIntent().getStringExtra(put.id);

       // Toast.makeText(this, id+"    " + imgeuser+"   "+seesion, Toast.LENGTH_LONG).show();


    }

    private void findview() {

        edt_com = findViewById(R.id.edt_com_sendcom);
        edt_nag = findViewById(R.id.edt_nag_com);
        edt_pos = findViewById(R.id.edt_pos_sendcom);
        btn_sendcom = findViewById(R.id.btn_sendcom_com);
        ratingBar=findViewById(R.id.rat_ratcom_com);
        txt_title=findViewById(R.id.txt_title_toolbar2);
        txt_title.setText("ثبت کامنت");
        img_back=findViewById(R.id.img_back_toolbar2);

        onclick();

    }

    private void onclick() {
        btn_sendcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seesion.equals("ورود/عضویت")||imgeuser.equals("")){
                    Toast.makeText(Sendcomment_Activity.this, "شما وارد حساب کاربری خود نشده اید", Toast.LENGTH_SHORT).show();

                }else {
                    if(edt_com.getText().toString().equals("")){
                        Toast.makeText(Sendcomment_Activity.this, "لطفا کامنت را پر کنید", Toast.LENGTH_SHORT).show();
                    }else {
                        sendcomm(id, imgeuser, seesion, edt_com.getText().toString(),
                                edt_pos.getText().toString(), edt_nag.getText().toString(), ratingBar.getRating());
                        finish();
                    }
                }
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void sendcomm(String idproduct,String image,String email,String comm,String pos,String nag,final float rat){
        String url = "http://s.rezamotahari1375.ir/shop/sendcomment.php";

        ProgressDialog progressDialog = new ProgressDialog(Sendcomment_Activity.this);
        progressDialog.setMessage("در حال دریافت اطلاعات");
        progressDialog.show();
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("1")){

                Toast.makeText(Sendcomment_Activity.this, "کامنت با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                }
                progressDialog.dismiss();

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sendcomment_Activity.this, "کامنت ثبت نشد", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap();
                map.put(put.email, email);
                map.put(put.id,idproduct);
                map.put(put.image,image);
                map.put(put.comment,comm);
                map.put(put.posotive,pos);
                map.put(put.negative,nag);
                map.put(put.rating, String.valueOf(rat));
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
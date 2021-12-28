package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class EditProfile_Acvivity extends AppCompatActivity {
    ImageView img_back;
    TextView txt_toolbar;
    String usernamrprofile, phoneprofile, addressprofile;
    TextInputEditText txt_user, txt_phone, txt_address;
    AppCompatButton btn_send;
    SharedPreferences preferences, preferences2;
    private String emailEdite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile__acvivity);
        findview();

        preferences = getSharedPreferences(put.shared, MODE_PRIVATE);
        emailEdite = preferences.getString(put.email, "ایمیل");


        onclick();

    }

    private void senddata(String email, String username, String phone, String address) {
        String Url = "http://s.rezamotahari1375.ir/shop/profile.php";
        ProgressDialog progressDialog = new ProgressDialog(EditProfile_Acvivity.this);
        progressDialog.setMessage("در حال ارسال اطلاعات");
        progressDialog.show();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                preferences2 = getSharedPreferences(put.shared2, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences2.edit();
                editor.putString(put.username, username);
                editor.putString(put.phone, phone);
                editor.putString(put.address, address);


                editor.apply();
                Toast.makeText(EditProfile_Acvivity.this, "اراسال با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();


            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditProfile_Acvivity.this, " ارسال اطلاعات با مشکل مواجه هشد", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        };
        StringRequest request = new StringRequest(Request.Method.POST, Url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put(put.email, email);
                map.put(put.username, username);
                map.put(put.phone, phone);
                map.put(put.address, address);

                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

    private void findview() {
        img_back = findViewById(R.id.img_back_toolbar2);
        txt_toolbar = findViewById(R.id.txt_title_toolbar2);
        txt_toolbar.setText(" ویرایش پروفایل");

        btn_send = findViewById(R.id.btn_send_edit);
        txt_address = findViewById(R.id.txt_address_edit);
        txt_phone = findViewById(R.id.txt_number_edit);
        txt_user = findViewById(R.id.txt_user_edit);

        preferences2 = getSharedPreferences(put.shared2, MODE_PRIVATE);

        txt_address.setText(preferences2.getString(put.address, ""));
        txt_phone.setText(preferences2.getString(put.phone, ""));
        txt_user.setText(preferences2.getString(put.username, ""));


    }

    private void onclick() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernamrprofile = txt_user.getText().toString().trim();
                phoneprofile = txt_phone.getText().toString().trim();
                addressprofile = txt_address.getText().toString().trim();

                if (usernamrprofile.equalsIgnoreCase("") || addressprofile.equalsIgnoreCase("") || phoneprofile.equalsIgnoreCase("")) {
                    Toast.makeText(EditProfile_Acvivity.this, "فیلدهای مورد نظر را پر کنید", Toast.LENGTH_SHORT).show();
                } else
                    senddata(emailEdite, usernamrprofile, phoneprofile, addressprofile);
            }


        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
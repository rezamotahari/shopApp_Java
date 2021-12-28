package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {
    ImageView img_back;
    TextView txt_titile, txt_register, txt_forget, txt_login;
    CheckBox ch_login;
    EditText edt_email, edt_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        findwiew();
        onClick();

    }

    private void onClick() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(intent);
            }
        });
        ch_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ch_login.isChecked()) {
                    edt_pass.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    edt_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                }
            }
        });
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login(edt_email.getText().toString().trim(), edt_pass.getText().toString().trim());
            }
        });
        txt_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_Activity.this,Activity_ForgetPassword.class);
                startActivity(intent);
            }
        });

    }

    private void findwiew() {
        img_back = findViewById(R.id.img_back_toolbar2);
        txt_titile = findViewById(R.id.txt_title_toolbar2);
        txt_register = findViewById(R.id.txt_register_login);
        txt_forget = findViewById(R.id.txt_forget_login);
        ch_login = findViewById(R.id.ch_pass_login);
        edt_pass = findViewById(R.id.edt_pass_login);
        edt_email = findViewById(R.id.edt_email_login);
        txt_login = findViewById(R.id.txt_login_login);
        txt_titile.setText("صفحه ورود");
    }


    private void Login(String email, String password) {
        String url = "http://s.rezamotahari1375.ir/shop/login.php";

        ProgressDialog progressDialog = new ProgressDialog(Login_Activity.this);
        progressDialog.setMessage("در حال ارسال اطلاعات");
        progressDialog.show();


        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("504")) {

                    Toast.makeText(Login_Activity.this, "نام کاربری یا رمز عبور اشتباه است", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    String image=response.toString();
                    int splitemail=email.length();
                    int responsesplite=image.length();
                    String splite=image.substring(splitemail,responsesplite);

                    Toast.makeText(Login_Activity.this, "ورود انجام شد", Toast.LENGTH_SHORT).show();

                    edt_email.setText("");
                    edt_pass.setText("");
                    Intent intent = new Intent();
                    intent.putExtra(put.email, email);
                    intent.putExtra(put.image,splite);
                    setResult(RESULT_OK, intent);
                    finish();

                    progressDialog.dismiss();

                }
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login_Activity.this, "ارسال با مشکل مواجه شد", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                edt_email.setText("");
                edt_pass.setText("");
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("email", email);
                map.put("password", password);

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
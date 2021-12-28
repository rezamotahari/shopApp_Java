package com.example.shopapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
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
import com.example.shopapp.Class.ImageGallery;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.RuntimePermissionsActivity;
import com.example.shopapp.Class.put;
import com.example.shopapp.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.shopapp.R.drawable.user;

public class Register_Activity extends RuntimePermissionsActivity {
    EditText edt_email, edt_pass;
    CheckBox ch_register;
    TextView txt_titile, txt_register, txt_profile;
    ImageView img_back;
    CircleImageView img_profile;
    static final int CHOSE_GALLERY = 3133;
    static final int STORAGE_PERMISION = 3133;
    String picturePath = "", encodedImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        findview();
        onclick();
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        if (requestCode == STORAGE_PERMISION) {
            ImageGallery.showImage(Register_Activity.this, CHOSE_GALLERY);
        }
    }

    @Override
    public void onPermissionsDeny(int requestCode) {

    }

    private void onclick() {
        ch_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ch_register.isChecked()) {
                    edt_pass.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    edt_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                }
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        txt_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register_Activity.super.requestAppPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISION);
            }
        });
    }

    private void findview() {

        edt_email = findViewById(R.id.edt_email_register);
        edt_pass = findViewById(R.id.edt_pass_register);
        ch_register = findViewById(R.id.ch_pass_register);
        txt_titile = findViewById(R.id.txt_title_toolbar2);
        img_back = findViewById(R.id.img_back_toolbar2);
        txt_register = findViewById(R.id.txt_register_register);
        txt_titile.setText("ثبت نام");
        img_profile = findViewById(R.id.img_profile_register);
        txt_profile = findViewById(R.id.txt_profile_register);


    }

    private void Register() {
        String url = "http://s.rezamotahari1375.ir/shop/register.php";

        ProgressDialog progressDialog = new ProgressDialog(Register_Activity.this);
        progressDialog.setMessage("در حال ارسال اطلاعات");
        progressDialog.show();


        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Register_Activity.this, "ارسال با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                edt_email.setText("");
                edt_pass.setText("");
                img_profile.setImageResource(user);

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register_Activity.this, "ارسال با مشکل مواجه شد", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                edt_email.setText("");
                edt_pass.setText("");
                img_profile.setImageResource(user);
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("email", edt_email.getText().toString().trim());
                map.put("password",edt_pass.getText().toString().trim());
                map.put("image",encodedImage);

                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOSE_GALLERY && resultCode == RESULT_OK) {
//            Bitmap  bitmap=ImageGallery.getBitmap(Register_Activity.this,data.getData());
//

            try {


                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                img_profile.setImageBitmap(selectedImage);


                encodedImage=ImageGallery.getStringImage(selectedImage,300);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(Register_Activity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }


        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
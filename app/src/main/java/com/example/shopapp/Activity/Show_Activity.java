package com.example.shopapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.shopapp.Adapter.LikeAdapter;
import com.example.shopapp.Adapter.OnlyAdapter;
import com.example.shopapp.Class.DBfav;
import com.example.shopapp.Class.DbSqlite;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.BestSaleModel;
import com.example.shopapp.Model.LikeModel;
import com.example.shopapp.Model.ModelFav;
import com.example.shopapp.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.example.shopapp.Class.put.label;
import static com.example.shopapp.R.*;
import static com.example.shopapp.R.color.*;
import static com.example.shopapp.R.drawable.ic_baseline_star_24;
import static com.example.shopapp.R.drawable.ic_baseline_star_border_24;

public class Show_Activity extends AppCompatActivity {
    private SliderLayout Slider;
    AppBarLayout appBarLayout;
    ArrayList<String> strings = new ArrayList<>();

    Boolean b = true;
    TextView txt_name, txt_price, txt_color, txt_garanty, txt_desc, txt_next, textCountBasket, textRating, txt_freeprice;
    ImageView img_back, img_basket,img_fav;
    String idd, cookei, color, garanty, image, name, description, price, freeprice,visit,lable,cat;
    CardView cardbasket, comment_show, properies_show;
    Toolbar toolbar;
   String finalrating;
    ScaleRatingBar ratingbarFinal;
    Boolean bf=true;
    Context context=this;
    List<LikeModel>likeModels=new ArrayList<>();
    LikeAdapter likeAdapter;
    RecyclerView recyclerView;

    DbSqlite sqlite = new DbSqlite(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layout.activity_show_);
        SharedPreferences preferences = getSharedPreferences(put.shared, MODE_PRIVATE);
        cookei = preferences.getString(put.email, "ورود/عضویت");
        //Toast.makeText(this, cookei, Toast.LENGTH_SHORT).show();

        idd = getIntent().getStringExtra(put.id);
        color = getIntent().getStringExtra(put.color);
        image = getIntent().getStringExtra(put.image);
        garanty = getIntent().getStringExtra(put.garanty);
        name = getIntent().getStringExtra(put.name);
        description = getIntent().getStringExtra(put.description);
        price = getIntent().getStringExtra(put.price);
        finalrating = getIntent().getStringExtra(put.rating);
        int r = finalrating.length();
        if (r==1)
        {
            finalrating =finalrating+"."+"0";
        }
        else {
            finalrating = finalrating.substring(0,3);
        }
        freeprice = getIntent().getStringExtra(put.freeprice);
        visit=getIntent().getStringExtra(put.view);
        lable=getIntent().getStringExtra(label);
        cat=getIntent().getStringExtra(put.cat);
       // Toast.makeText(context, cat, Toast.LENGTH_SHORT).show();


        //Toast.makeText(this, image, Toast.LENGTH_SHORT).show();

        findview();


    }

    private void findview() {
        Slider = findViewById(R.id.slider);
        appBarLayout = findViewById(R.id.app_bar_layout);
        toolbar = findViewById(R.id.toolbar3);
        txt_name = findViewById(R.id.txt_name_show);
        txt_price = findViewById(id.txt_price_show);
        txt_color = findViewById(id.txt_color_show);
        txt_garanty = findViewById(id.txt_garanty_show);
        txt_desc = findViewById(id.txt_desc_show);
        txt_next = findViewById(id.txt_next_show);
        img_back = findViewById(id.img_back_toolbar3);
        cardbasket = findViewById(id.card_basket_show);
        textCountBasket = findViewById(id.textCountBasket);
        img_basket = findViewById(id.imageBasket);
        comment_show = findViewById(id.card_comment_show);
        ratingbarFinal = findViewById(id.ratingbarFinal);
        textRating = findViewById(id.textRating);
        properies_show = findViewById(id.card_properties_show);
        txt_freeprice = findViewById(id.txt_freeprice_show);
        img_fav=findViewById(id.img_fav_show);
        recyclerView=findViewById(id.recy_like_show);

        SharedPreferences sharedPreferences = getSharedPreferences("c", MODE_PRIVATE);
        textCountBasket.setText(sharedPreferences.getString("count", "0"));


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                //وضعیت بسته شدن اپ بار را در ای نگه میدارد

                int scroll = -(i);
                Log.i("appshop", scroll + "");
                if (scroll > 452) {

                    toolbar.setBackgroundResource(blue);


                } else {
                    toolbar.setBackgroundResource(transparent);
                }

            }
        });
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Vazir-Medium-FD-WOL.ttf");
        String rat = String.valueOf(finalrating);
        ratingbarFinal.setRating(Float.valueOf(rat));
        textRating.setText(rat + "" + "از" + "" + "5");
        textRating.setTypeface(typeface);
        txt_freeprice.setTypeface(typeface);

        setdata();
        getimagegallery(idd);
        onclick();
        Cursor cursor = sqlite.cu(Integer.parseInt(idd));

        if (cursor.getCount() == 1) {

            img_fav.setImageResource(ic_baseline_star_24);
            bf = false;
        } else {

            img_fav.setImageResource(ic_baseline_star_border_24);
            bf = true;
        }

        likeAdapter = new LikeAdapter(getApplicationContext(), likeModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(likeAdapter);


        if(cat.equals("0")){
            recyclerView.setVisibility(View.GONE);
        }else {
            getlikeproduct(cat);
        }





    }

    private void onclick() {

        img_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bf) {
                    if (!freeprice.equals("")) {
                        sqlite.insertFav(new ModelFav(Integer.parseInt(idd), image, name, visit, freeprice, price, label));
                        img_fav.setImageResource(ic_baseline_star_24);
                        Toast.makeText(context, "به لیست علاقه مندی ها اضافه شد", Toast.LENGTH_SHORT).show();
                        bf = false;
                    } else {
                        sqlite.insertFav(new ModelFav(Integer.parseInt(idd), image, name, visit, price, price, label));
                        img_fav.setImageResource(ic_baseline_star_24);

                        Toast.makeText(context, "به لیست علاقه مندی ها اضافه شد", Toast.LENGTH_SHORT).show();
                        bf = false;
                    }

                } else {
                    sqlite.deleteFav(Integer.parseInt(idd));
                    img_fav.setImageResource(ic_baseline_star_border_24);

                    Toast.makeText(context, "از لیست علاقه مندی ها حذف شد", Toast.LENGTH_SHORT).show();
                    bf = true;
                }
            }
        });
        txt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b) {
                    txt_desc.setSingleLine(false);
                    txt_next.setText("بستن");
                    b = false;
                } else {

                    txt_desc.setSingleLine(true);
                    txt_next.setText("ادامه مطلب");
                    b = true;

                }


            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        cardbasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cookei.equals("ورود/عضویت")) {
                    Toast.makeText(Show_Activity.this, "لطفا ابتدا وارد حساب کاربری خود شوید", Toast.LENGTH_LONG).show();
                } else {

                    if(freeprice.equals("")){
                        sendbasket(cookei, idd, color, garanty, image, price);
                    }else {
                        sendbasket(cookei, idd, color, garanty, image,freeprice);
                    }



                }
            }
        });
        img_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Show_Activity.this, Basket_Activity.class);
                startActivity(intent);
            }
        });
        comment_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Show_Activity.this, Showcomment_Activity.class);
                intent.putExtra(put.id, idd);
                startActivity(intent);
            }
        });
        properies_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Show_Activity.this, Properties_Activity.class);
                intent.putExtra(put.id, idd);
                intent.putExtra(put.name, name);
                startActivity(intent);
            }
        });

    }


    @SuppressLint("ResourceAsColor")
    private void setdata() {


        txt_name.setText(name);

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String pricee = decimalFormat.format(Integer.valueOf(price));

        txt_freeprice.setVisibility(View.GONE);
        if(!freeprice.equals("")){
            txt_freeprice.setVisibility(View.VISIBLE);
            String freepricee = decimalFormat.format(Integer.valueOf(freeprice));
            txt_freeprice.setText(freepricee + "تومان");


            txt_price.setPaintFlags(txt_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        }else {
            txt_freeprice.setVisibility(View.GONE);





        }

        txt_price.setText(pricee + "تومان");

        txt_color.setText(color);
        txt_garanty.setText(garanty);
        txt_desc.setText(description);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Vazir-Medium-FD-WOL.ttf");
        txt_desc.setTypeface(typeface);
        txt_price.setTypeface(typeface);
        txt_color.setTypeface(typeface);
        txt_garanty.setTypeface(typeface);
        txt_name.setTypeface(typeface);
    }

    private void getimagegallery(String idd) {
        String url = "http://s.rezamotahari1375.ir/shop/getproductimage.php";


        ProgressDialog progressDialog = new ProgressDialog(Show_Activity.this);
        progressDialog.setMessage("در حال دریافت اطلاعات");
        progressDialog.show();


        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String picse = jsonObject.getString("pics");
                        strings.add(picse);

                        TextSliderView textSliderView = new TextSliderView(Show_Activity.this);
                        textSliderView.image(strings.get(i))
                                .setScaleType(BaseSliderView.ScaleType.CenterInside);

                        Slider.setDuration(3000);
                        Slider.addSlider(textSliderView);

                    }
                } catch (Exception e) {

                }
                progressDialog.dismiss();

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Show_Activity.this, "عدم اتصال به سرور", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        };

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put(put.id, idd);
                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


    }

    private void sendbasket(String email, String id, String color, String garanty, String image, String price) {

        String url = "http://s.rezamotahari1375.ir/shop/basket.php";
        ProgressDialog progressDialog = new ProgressDialog(Show_Activity.this);
        progressDialog.show();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Show_Activity.this, response.toString().trim(), Toast.LENGTH_SHORT).show();

                String count = textCountBasket.getText().toString();
                int total = 0;
                total = Integer.parseInt(count) + 1;
                textCountBasket.setText(String.valueOf(total));


                progressDialog.dismiss();

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Show_Activity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("basket", error.getMessage());
                progressDialog.dismiss();

            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put(put.id, id);
                map.put(put.email, email);
                map.put(put.color, color);
                map.put(put.garanty, garanty);
                map.put(put.image, image);
                map.put(put.price, price);
              //  map.put(put.freeprice, freeprice);

                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    private void getlikeproduct(String cat) {
        String url = "http://s.rezamotahari1375.ir/shop/likeproduct.php";


//        ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
//        progressDialog.setMessage("در حال دریافت اطلاعات");
//        progressDialog.show();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
               LikeModel[] likeModel = gson.fromJson(response.toString(), LikeModel[].class);
                for (int i = 0; i < likeModel.length; i++) {
                    likeModels.add(likeModel[i]);
                    likeAdapter.notifyDataSetChanged();


                }
                // progressDialog.dismiss();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressDialog.dismiss();
              //  Toast.makeText(Show_Activity.this, error.getMessage()+"" , Toast.LENGTH_LONG).show();



            }
        };
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> map = new HashMap<>();
                map.put(put.cat, cat);
                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
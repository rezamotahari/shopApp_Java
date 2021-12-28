package com.example.shopapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
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
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.shopapp.Adapter.BanerAdapter;
import com.example.shopapp.Adapter.BestSalesAdapter;
import com.example.shopapp.Adapter.FreeAdapter;
import com.example.shopapp.Adapter.OnlyAdapter;
import com.example.shopapp.Adapter.VisitAdapter;
import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.Banermodel;
import com.example.shopapp.Model.BestSaleModel;
import com.example.shopapp.Model.ModelFree;
import com.example.shopapp.Model.ModelOnly;
import com.example.shopapp.Model.VisitModel;
import com.example.shopapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public class HomeActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout Slider;

    List<ModelFree> modelFrees = new ArrayList<>();
    RecyclerView recyclerView, recyclerViewonly, recyvisit, recybest, recybaner;
    DrawerLayout drawerLayout;
    ImageView menu_nav, img_basket, img_search;
    FreeAdapter freeAdapter;
    TextView txt_login, txt_category_nav, txt_count, txt_fav, txt_order, txt_about;


    List<ModelOnly> modelOnlies = new ArrayList<>();

    OnlyAdapter onlyAdapter;

    List<VisitModel> visitModels = new ArrayList<>();
    List<Banermodel> banermodels = new ArrayList<>();
    VisitAdapter visitAdapter;
    BanerAdapter banerAdapter;
    List<BestSaleModel> bestSaleModels = new ArrayList<>();
    BestSalesAdapter bestAdapter;
    CardView cardView;
    SharedPreferences preferences;
    CircleImageView img_prifile;
    String imgeuser;

    TextView textOnlyAll, textfreeAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity);


        findview();

        preferences = getSharedPreferences(put.shared, MODE_PRIVATE);
        txt_login.setText(preferences.getString(put.email, "ورود/عضویت"));
        txt_count = findViewById(R.id.txt_count_home);
        getcount(preferences.getString(put.email, "ورود/عضویت"));
        imgeuser = preferences.getString(put.image, " ");
        if (imgeuser.equals(" ")) {
            Picasso.with(getApplicationContext())
                    .load(R.drawable.baner)
                    .into(img_prifile);
        } else {
            Picasso.with(getApplicationContext())
                    .load(imgeuser)
                    .into(img_prifile);
        }
    }

    private void findview() {
        Slider = findViewById(R.id.slider);
        recyclerView = findViewById(R.id.recy_home);
        recyclerViewonly = findViewById(R.id.recyonly_home);
        recyvisit = findViewById(R.id.recy_visit);
        recybest = findViewById(R.id.recy_best);
        drawerLayout = findViewById(R.id.drawer);
        menu_nav = findViewById(R.id.menu_nav);
        txt_login = findViewById(R.id.txt_login_nav);
        cardView = findViewById(R.id.card_category_home);
        txt_category_nav = findViewById(R.id.txt_category_nav);
        img_basket = findViewById(R.id.img_basket_home);
        img_prifile = findViewById(R.id.img_profile_nav);
        img_search = findViewById(R.id.img_search);
        txt_fav = findViewById(R.id.txt_fav_nav);
        recybaner = findViewById(R.id.recy_bsner_home);
        txt_order = findViewById(R.id.txt_order_nav);
        txt_about = findViewById(R.id.txt_about_nav);

        textOnlyAll = findViewById(R.id.textOnlyAll);
        textfreeAll = findViewById(R.id.textfreeAll);
        setrecy();
        getfreedata();

        getonlydata();

        getvisitdata();
        getbestsaledata();

        Installslider();
        getbanerdata();
        Onclick();
    }

    private void setrecy() {
        freeAdapter = new FreeAdapter(getApplicationContext(), modelFrees);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(freeAdapter);


        onlyAdapter = new OnlyAdapter(getApplicationContext(), modelOnlies);
        recyclerViewonly.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        recyclerViewonly.setAdapter(onlyAdapter);


        visitAdapter = new VisitAdapter(getApplicationContext(), visitModels);
        recyvisit.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        recyvisit.setAdapter(visitAdapter);


        bestAdapter = new BestSalesAdapter(getApplicationContext(), bestSaleModels);
        recybest.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));
        recybest.setAdapter(bestAdapter);

        banerAdapter = new BanerAdapter(banermodels, getApplicationContext());
        recybaner.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recybaner.setAdapter(banerAdapter);
    }

    private void Onclick() {
        menu_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_login.getText().equals("ورود/عضویت")) {
                    Intent intent = new Intent(HomeActivity.this, Login_Activity.class);

                    startActivityForResult(intent, put.REQUEST_CODE);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    Intent intent = new Intent(HomeActivity.this, Profile_Activity.class);

                    startActivityForResult(intent, put.REQUEST_EXITE);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

            }
        });
        txt_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Fav_Activity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Activity_Search.class);
                startActivity(intent);
                //    Toast.makeText(HomeActivity.this, "dfdss", Toast.LENGTH_SHORT).show();
            }
        });
        txt_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Activity_Order.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        txt_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, About_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Category_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        txt_category_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Category_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        img_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Basket_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        textOnlyAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,Activity_AllProduct.class);
                intent.putExtra("idText","1");
                startActivity(intent);
            }
        });
        textfreeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,Activity_AllProduct.class);
                intent.putExtra("idText","2");
                startActivity(intent);
            }
        });

    }


    private void getfreedata() {
        String url = "http://s.rezamotahari1375.ir/shop/product.php";


//        ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
//        progressDialog.setMessage("در حال دریافت اطلاعات");
//        progressDialog.show();

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                ModelFree[] frees = gson.fromJson(response.toString(), ModelFree[].class);
                for (int i = 0; i < frees.length; i++) {
                    modelFrees.add(frees[i]);
                    freeAdapter.notifyDataSetChanged();


                }
              //  progressDialog.dismiss();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, " دریافت اطلاعات با مشکل مواجه هشد", Toast.LENGTH_SHORT).show();


            }
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


    }

    private void getbanerdata() {
        String url = "http://s.rezamotahari1375.ir/shop/baner.php";


        ;

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                Banermodel[] frees = gson.fromJson(response.toString(), Banermodel[].class);
                for (int i = 0; i < frees.length; i++) {
                    banermodels.add(frees[i]);
                    banerAdapter.notifyDataSetChanged();


                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(HomeActivity.this, " دریافت اطلاعات با مشکل مواجه هشد", Toast.LENGTH_SHORT).show();


            }
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


    }

    private void getonlydata() {
        String url = "http://s.rezamotahari1375.ir/shop/getonly.php";

//
//        ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
//        progressDialog.setMessage("در حال دریافت اطلاعات");
//        progressDialog.show();

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                ModelOnly[] onlies = gson.fromJson(response.toString(), ModelOnly[].class);
                for (int i = 0; i < onlies.length; i++) {
                    modelOnlies.add(onlies[i]);
                    onlyAdapter.notifyDataSetChanged();


                }
               // progressDialog.dismiss();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, " دریافت اطلاعات با مشکل مواجه هشد", Toast.LENGTH_SHORT).show();


            }
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


    }

    private void getvisitdata() {
        String url = "http://s.rezamotahari1375.ir/shop/getvisit.php";


//        ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
//        progressDialog.setMessage("در حال دریافت اطلاعات");
//        progressDialog.show();

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                VisitModel[] visit = gson.fromJson(response.toString(), VisitModel[].class);
                for (int i = 0; i < visit.length; i++) {
                    visitModels.add(visit[i]);
                    visitAdapter.notifyDataSetChanged();


                }
                //progressDialog.dismiss();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             //   progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, " دریافت اطلاعات با مشکل مواجه هشد", Toast.LENGTH_SHORT).show();


            }
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


    }

    private void getbestsaledata() {
        String url = "http://s.rezamotahari1375.ir/shop/getbestsale.php";


//        ProgressDialog progressDialog = new ProgressDialog(HomeActivity.this);
//        progressDialog.setMessage("در حال دریافت اطلاعات");
//        progressDialog.show();

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                BestSaleModel[] saleModelss = gson.fromJson(response.toString(), BestSaleModel[].class);
                for (int i = 0; i < saleModelss.length; i++) {
                    bestSaleModels.add(saleModelss[i]);
                    bestAdapter.notifyDataSetChanged();


                }
               // progressDialog.dismiss();
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // progressDialog.dismiss();
                Toast.makeText(HomeActivity.this, " دریافت اطلاعات با مشکل مواجه هشد", Toast.LENGTH_SHORT).show();


            }
        };

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


    }

    private void Installslider() {

        Map<String, String> url_image = new TreeMap<>();
        url_image.put("image1", "http://s.rezamotahari1375.ir/shop/image/refrigerator.jpg");
        url_image.put("image2", "http://s.rezamotahari1375.ir/shop/image/cooler.jpg");
        url_image.put("image3", "http://s.rezamotahari1375.ir/shop/image/heater.jpg");
        url_image.put("image4", "http://s.rezamotahari1375.ir/shop/image/stove.jpg");


        for (int i = 0; i < url_image.keySet().size(); i++) {

            String keyname = url_image.keySet().toArray()[i].toString();
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(keyname)
                    .empty(R.drawable.ic_launcher_background)
                    .error(R.drawable.shopicon)
                    .image(url_image.get(keyname))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);


            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", keyname);
            Slider.setDuration(3000);
            Slider.addSlider(textSliderView);

        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        String s = slider.getBundle().get("extra") + "";
        if (s.equals("image1")) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        } else if (s.equals("image2")) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        } else if (s.equals("image3")) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        } else if (s.equals("image4")) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == put.REQUEST_CODE && resultCode == RESULT_OK) {

            String email = data.getStringExtra(put.email);
            String image = data.getStringExtra(put.image);

            txt_login.setText(email);


            SharedPreferences sharedPreferences = getSharedPreferences(put.shared, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(put.email, email);
            editor.putString(put.image, image);
            editor.apply();
            recreate();

        } else if (requestCode == put.REQUEST_EXITE && resultCode == RESULT_OK) {
            String email = data.getStringExtra(put.email);
            String image = data.getStringExtra(put.image);

            if (image.equals(" ")) {
                Picasso.with(getApplicationContext())
                        .load(R.drawable.baner)
                        .into(img_prifile);
            } else {
                Picasso.with(getApplicationContext())
                        .load(imgeuser)
                        .into(img_prifile);
            }


            txt_login.setText(email);
            recreate();
        }
    }

    private void getcount(final String emailcount) {
        String url = "http://s.rezamotahari1375.ir/shop/count.php";
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txt_count.setText(response.toString());
                SharedPreferences sharedPreferences = getSharedPreferences("c", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("count", txt_count.getText().toString());
                editor.apply();


            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap();
                map.put(put.email, emailcount);
                return map;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    @Override
    protected void onStop() {
        getcount(preferences.getString(put.email, "ورود/عضویت"));
        super.onStop();
    }

    @Override
    protected void onStart() {
        getcount(preferences.getString(put.email, "ورود/عضویت"));
        super.onStart();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
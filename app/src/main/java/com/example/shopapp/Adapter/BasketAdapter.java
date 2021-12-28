package com.example.shopapp.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.example.shopapp.Class.MySingleton;
import com.example.shopapp.Class.ONloadprice;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.BasketModel;
import com.example.shopapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    Context context;
    List<BasketModel> basketModels;
    ONloadprice  oNloadprice;

    public BasketAdapter(Context context, List<BasketModel> basketModels) {
        this.context = context;
        this.basketModels = basketModels;
    }

    public void setoNloadprice(ONloadprice oNloadprice) {
        this.oNloadprice = oNloadprice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BasketModel basketModel = basketModels.get(position);

        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String price = decimalFormat.format(Integer.valueOf(basketModel.getPrice()));
        String allprice = decimalFormat.format(Integer.valueOf(basketModel.getAllprice()));
        holder.txt_title.setText(basketModel.getTitle());
        holder.txt_color.setText(basketModel.getColor());
        holder.txt_garanty.setText(basketModel.getGaranty());
        holder.txt_num.setText(basketModel.getNumber()+""+"عدد");
        holder.txt_allprice.setText(allprice + " " + "تومان");
        holder.txt_price.setText(price + " " + "تومان");

        Picasso.with(context)
                .load(basketModel.getImage())
                .into(holder.img_basket);
        holder.txt_delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(oNloadprice!=null) {

                    oNloadprice.Onloadprice();

                    delete(basketModel.getId() + "");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            basketModels.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, basketModels.size());
                        }
                    }, 200);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return basketModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_basket;
        TextView txt_title, txt_color, txt_garanty, txt_num, txt_price, txt_allprice, txt_delet, txtprice, txtallprice;
        Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Vazir-Medium-FD-WOL.ttf");

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_basket = itemView.findViewById(R.id.img_basket);
            txt_title = itemView.findViewById(R.id.txt_name_basket);
            txt_color = itemView.findViewById(R.id.txt_color_basket);
            txt_garanty = itemView.findViewById(R.id.txt_garanty_basket);
            txt_num = itemView.findViewById(R.id.txt_num_basket);
            txt_price = itemView.findViewById(R.id.txt_price_basket);
            txt_allprice = itemView.findViewById(R.id.txt_allprice_basket);
            txt_delet = itemView.findViewById(R.id.txt_delet_basket);
            txtprice = itemView.findViewById(R.id.txtprice);
            txtallprice = itemView.findViewById(R.id.txtallprice);

            txt_price.setTypeface(typeface);
            txt_allprice.setTypeface(typeface);
            txt_num.setTypeface(typeface);
            txt_garanty.setTypeface(typeface);
            txt_color.setTypeface(typeface);
            txt_title.setTypeface(typeface);
            txtprice.setTypeface(typeface);
            txtallprice.setTypeface(typeface);
            txt_delet.setTypeface(typeface);


        }
    }

    private void delete(String id){
        String url="http://s.rezamotahari1375.ir/shop/delete.php";
        Response.Listener<String>listener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "با موفقیت حذف شد", Toast.LENGTH_SHORT).show();

            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, " حذف نشد", Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url,listener,errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String>map=new HashMap();
                map.put(put.id,id);
                return map;
            }
        };
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

}

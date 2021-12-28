package com.example.shopapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Activity.Wait_Activity;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.BestSaleModel;

import com.example.shopapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class BestSalesAdapter extends RecyclerView.Adapter<BestSalesAdapter.ViewHolder> {
    Context context;
    List<BestSaleModel> bestSaleModels;

    public BestSalesAdapter(Context context, List<BestSaleModel> bestSaleModels) {
        this.context = context;
        this.bestSaleModels = bestSaleModels;
    }

    @NonNull
    @Override
    public BestSalesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_layout, parent, false);
        return new BestSalesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSalesAdapter.ViewHolder holder, int position) {
        BestSaleModel model = bestSaleModels.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String price = decimalFormat.format(Integer.valueOf(model.getPrice()));

        holder.txtprice.setText(price + "" + "تومان");
        holder.txtview.setText(model.getView());
        holder.txtname.setText(model.getName());

        Picasso.with(context)
                .load(model.getImage())
                .into(holder.imgfree);

        holder.card_best.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(context,free.getId()+"" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BestSalesAdapter.this.context, Wait_Activity.class);
                intent.putExtra(put.id,model.getId()+"");
                intent.putExtra(put.freeprice,"");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              //  ((Activity)context).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bestSaleModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card_best;
        ImageView imgfree;
        TextView txtname, txtview, txtprice, txtlable;
        Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Vazir-Medium-FD-WOL.ttf");

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgfree = itemView.findViewById(R.id.imgbest);
            txtname = itemView.findViewById(R.id.txtnamebest);
            txtview = itemView.findViewById(R.id.viewbest);
            txtprice = itemView.findViewById(R.id.pricebest);
            txtname.setTypeface(typeface);
            txtview.setTypeface(typeface);
            txtprice.setTypeface(typeface);
            card_best=itemView.findViewById(R.id.card_best);


        }
    }

}


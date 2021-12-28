package com.example.shopapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Activity.Wait_Activity;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.ModelOnly;
import com.example.shopapp.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;


public class OnlyAdapter extends RecyclerView.Adapter<OnlyAdapter.ViewHolder> {
    Context context;
    List<ModelOnly> modelOnly;

    public OnlyAdapter(Context context, List<ModelOnly> modelOnly) {
        this.context = context;
        this.modelOnly = modelOnly;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onlylayout, parent, false);
        return new OnlyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlyAdapter.ViewHolder holder, int position) {
        ModelOnly modelOnly1 = modelOnly.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String price = decimalFormat.format(Integer.valueOf(modelOnly1.getPrice()));

        holder.txtprice.setText(price + "" + "تومان");
        holder.txtview.setText(modelOnly1.getView());
        holder.txtname.setText(modelOnly1.getName());

        holder.linearLayout.setVisibility(View.GONE);
        holder.shimmerFrameLayout.startShimmer();

        if (modelOnly1.getImage() != null) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.shimmerFrameLayout.stopShimmer();
                    holder.shimmerFrameLayout.setVisibility(View.GONE);
                    holder.linearLayout.setVisibility(View.VISIBLE);
                    Picasso.with(context)
                            .load(modelOnly1.getImage())
                            .into(holder.imgfree);
                }
            }, 1000);

        }


        holder.card_only.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(context,free.getId()+"" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OnlyAdapter.this.context, Wait_Activity.class);
                intent.putExtra(put.id, modelOnly1.getId() + "");
                intent.putExtra(put.freeprice, "");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
                // ((Activity)context).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelOnly.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView card_only;
        ShimmerFrameLayout shimmerFrameLayout;
        LinearLayout linearLayout;
        ImageView imgfree;
        TextView txtname, txtview, txtprice, txtlable;
        Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Vazir-Medium-FD-WOL.ttf");

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgfree = itemView.findViewById(R.id.imgonly);
            txtname = itemView.findViewById(R.id.txtnameonly);
            txtview = itemView.findViewById(R.id.viewonly);
            txtprice = itemView.findViewById(R.id.priceonly);
            txtname.setTypeface(typeface);
            txtview.setTypeface(typeface);
            txtprice.setTypeface(typeface);
            card_only = itemView.findViewById(R.id.card_only);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerfram);
            linearLayout = itemView.findViewById(R.id.lnrshimer);


        }
    }

}

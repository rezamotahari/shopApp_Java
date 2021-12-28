package com.example.shopapp.Adapter;

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

import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Activity.Wait_Activity;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.LikeModel;
import com.example.shopapp.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.ViewHolder> {
    Context context;
    List<LikeModel> likeModels;

    public LikeAdapter(Context context, List<LikeModel> likeModels) {
        this.context = context;
        this.likeModels = likeModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onlylayout, parent, false);
        return new LikeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeAdapter.ViewHolder holder, int position) {
        LikeModel likeModel = likeModels.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        String price = decimalFormat.format(Integer.valueOf(likeModel.getPrice()));

        holder.txtprice.setText(price + "" + "تومان");
        holder.txtview.setText(likeModel.getView());
        holder.txtname.setText(likeModel.getName());

        holder.linearLayout.setVisibility(View.GONE);
        holder.shimmerFrameLayout.startShimmer();

        if (likeModel.getImage() != null) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.shimmerFrameLayout.stopShimmer();
                    holder.shimmerFrameLayout.setVisibility(View.GONE);
                    holder.linearLayout.setVisibility(View.VISIBLE);
                    Picasso.with(context)
                            .load(likeModel.getImage())
                            .into(holder.imgfree);
                }
            }, 1000);

        }


        holder.card_only.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(context,free.getId()+"" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LikeAdapter.this.context, Wait_Activity.class);
                intent.putExtra(put.id, likeModel.getId() + "");
                intent.putExtra(put.freeprice, "");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
                // ((Activity)context).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

    }

    @Override
    public int getItemCount() {
        return likeModels.size();
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

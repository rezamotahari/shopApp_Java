package com.example.shopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.example.shopapp.Model.FavModel;
import com.example.shopapp.Model.ItemModel;
import com.example.shopapp.Model.ModelFav;
import com.example.shopapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavAdapter  extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    Context context;
    List<ModelFav> favModels;

    public FavAdapter(Context context, List<ModelFav> itemcategoryModels) {
        this.context = context;
        this.favModels = itemcategoryModels;
    }

    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new FavAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {
        ModelFav favModel= favModels.get(position);
        holder.txt_name.setText(favModel.getTitle());
        holder.txt_freeprice.setVisibility(View.GONE);

        //  Toast.makeText(context, items.getLable()+"", Toast.LENGTH_SHORT).show();
        if (favModel.getLabel().equals("0")) {
            holder.txt_freeprice.setVisibility(View.GONE);
            holder.txt_price.setText(favModel.getFreeprice()+"تومان");
        }else {
            holder.txt_freeprice.setVisibility(View.VISIBLE);
            holder.txt_price.setText(favModel.getFreeprice()+"تومان");
            holder.txt_freeprice.setPaintFlags(holder.txt_freeprice.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.txt_freeprice.setText(favModel.getPrice()+"تومان");
        holder.txt_view.setText(favModel.getVisit());
        Picasso.with(context)
                .load(favModel.getImage())
                .into(holder.img_item);
        holder.card_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FavAdapter.this.context, Wait_Activity.class);
                intent.putExtra(put.id,favModel.getId()+"");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                int freeprice=Integer.parseInt(favModel.getFreeprice());
                int price=Integer.parseInt(favModel.getPrice());
                if(price==freeprice){
                    intent.putExtra(put.freeprice,"");
                }else {
                    intent.putExtra(put.freeprice, favModel.getFreeprice());
                }
                context.startActivity(intent);
                // Toast.makeText(context, holder.getItemId()+"", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return favModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name,txt_price,txt_freeprice,txt_view;
        ImageView img_item;
        CardView card_item;
        Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"Vazir-Medium-FD-WOL.ttf");

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name_item);
            img_item = itemView.findViewById(R.id.img_item);
            txt_freeprice=itemView.findViewById(R.id.txt_freeprice_item);
            txt_price=itemView.findViewById(R.id.txt_price_item);
            txt_view=itemView.findViewById(R.id.txt_view_item);
            card_item=itemView.findViewById(R.id.card_item);

            txt_view.setTypeface(typeface);
            txt_price.setTypeface(typeface);
            txt_freeprice.setTypeface(typeface);
            txt_name.setTypeface(typeface);
        }
    }
}




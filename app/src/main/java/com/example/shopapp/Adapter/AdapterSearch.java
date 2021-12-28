package com.example.shopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopapp.Activity.Wait_Activity;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.ModelSearch;
import com.example.shopapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.viewHolder>{


    Context context;
    List<ModelSearch> modelSearches;

    public AdapterSearch(Context context, List<ModelSearch> modelSearches) {
        this.context = context;
        this.modelSearches = modelSearches;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutsearch,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        final ModelSearch item = modelSearches.get(i);
        viewHolder.textfreeprice.setVisibility(View.GONE);
        viewHolder.texttitle.setText(item.getName());
        viewHolder.textprice.setText(item.getPrice()+" "+"تومان");


        if (item.getLable().equals("0"))
        {
            viewHolder.textfreeprice.setVisibility(View.GONE);
            viewHolder.textprice.setTextColor(Color.GREEN);
        }
        else
        {
            viewHolder.textprice.setTextColor(Color.RED);
            viewHolder.textprice.setPaintFlags(viewHolder.textprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.textfreeprice.setVisibility(View.VISIBLE);
            viewHolder.textfreeprice.setText(item.getFreeprice()+" "+"تومان");
            viewHolder.textfreeprice.setTextColor(Color.GREEN);
        }
        Picasso
                .with(context)
                .load(item.getImage())
                .into(viewHolder.imageView);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getId()+"", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdapterSearch.this.context, Wait_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(put.id,item.getId()+"");
                int freeprice= Integer.parseInt(item.getFreeprice());
                int price = Integer.parseInt(item.getPrice());
                if (freeprice==price)
                {
                    intent.putExtra(put.freeprice,"");
                }
                else {
                    intent.putExtra(put.freeprice, item.getFreeprice());
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelSearches.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView texttitle,textprice,textfreeprice;
        Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Vazir-Medium-FD-WOL.ttf");

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewsearch);
            imageView = itemView.findViewById(R.id.imagesearch);
            textfreeprice = itemView.findViewById(R.id.textFreepriceSearch);
            textfreeprice.setTypeface(typeface);
            textprice = itemView.findViewById(R.id.textPriceSearch);
            textprice.setTypeface(typeface);
            texttitle = itemView.findViewById(R.id.texttileSearch);
            texttitle.setTypeface(typeface);

        }
    }
}

package com.example.shopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Activity.Activity_Banner;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.Banermodel;
import com.example.shopapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BanerAdapter extends RecyclerView.Adapter<BanerAdapter.ViewHolder>{
    List<Banermodel>banermodels;
    Context context;

    public BanerAdapter(List<Banermodel> banermodels, Context context) {
        this.banermodels = banermodels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.banerlayout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Banermodel banermodel=banermodels.get(position);
        Picasso.with(context)
                .load(banermodel.getImage())
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BanerAdapter.this.context, Activity_Banner.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(put.id,banermodel.getId()+"");
                context.startActivity(intent);
                //Toast.makeText(context, banner.getId()+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return banermodels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView  imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card_baner);
            imageView=itemView.findViewById(R.id.img_baner);
        }
    }
}

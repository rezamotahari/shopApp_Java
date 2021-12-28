package com.example.shopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Activity.Itemcategory_Activity;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.CategoryModel;
import com.example.shopapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
   List<CategoryModel> categoryModel;
    Context context;

    public CategoryAdapter(List<CategoryModel> categoryModel, Context context) {
        this.categoryModel = categoryModel;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CategoryModel category
                =categoryModel.get(position);
        holder.txt_categort.setText(category.getTitle());
        Picasso.with(context)
                .load(category.getImage())
                .into(holder.img_category);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryAdapter.this.context, Itemcategory_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(put.id,category.getId()+"");
               context.startActivity(intent);
               // Toast.makeText(context, category.getId()+"", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView img_category;
        TextView txt_categort;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card_category);
            img_category=itemView.findViewById(R.id.img_category);
            txt_categort=itemView.findViewById(R.id.txt_category);
        }
    }
}

package com.example.shopapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Activity.Item_Activity;
import com.example.shopapp.Class.put;
import com.example.shopapp.Model.ItemcategoryModel;
import com.example.shopapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemcategoryAdapter extends RecyclerView.Adapter<ItemcategoryAdapter.ViewHolder> {

    Context context;
    List<ItemcategoryModel> itemcategoryModels;

    public ItemcategoryAdapter(Context context, List<ItemcategoryModel> itemcategoryModels) {
        this.context = context;
        this.itemcategoryModels = itemcategoryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcategory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemcategoryModel itemcategoryModel=itemcategoryModels.get(position);
        holder.txt_item.setText(itemcategoryModel.getName());
        Picasso.with(context)
                .load(itemcategoryModel.getImage())
                .into(holder.img_item);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(ItemcategoryAdapter.this.context, Item_Activity.class);
                intent.putExtra(put.id,itemcategoryModel.getId()+"");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
              //  Toast.makeText(context, itemcategoryModel.getId()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemcategoryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_item;
        CircleImageView img_item;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item = itemView.findViewById(R.id.txt_item_itemcat);
            img_item = itemView.findViewById(R.id.img_item_itemcat);
            cardView=itemView.findViewById(R.id.card_itemcategory);
        }
    }
}

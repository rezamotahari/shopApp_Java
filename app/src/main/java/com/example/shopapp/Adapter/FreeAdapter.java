package com.example.shopapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.example.shopapp.Model.ModelFree;
import com.example.shopapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import jp.shts.android.library.TriangleLabelView;

public class FreeAdapter extends RecyclerView.Adapter<FreeAdapter.ViewHolder> {
    Context context;
    List<ModelFree> modelFrees;

    public FreeAdapter(Context context, List<ModelFree> modelFrees) {
        this.context = context;
        this.modelFrees = modelFrees;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.freelayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelFree free=modelFrees.get(position);
        DecimalFormat decimalFormat=new DecimalFormat("###,###");
        String freeprice=decimalFormat.format(Integer.valueOf(free.getFreeprice()));
        String price=decimalFormat.format(Integer.valueOf(free.getPrice()));
        holder.txtprice.setText(freeprice+""+"تومان");

        holder.txtview.setText(free.getView());
        holder.txtname.setText(free.getName());
        holder.lablefree.setSecondaryText(free.getLable()+"%");
        Picasso.with(context)
                .load(free.getImage())
                .into(holder.imgfree);

        holder.price_free.setText(price+"تومان");
        holder.price_free.setPaintFlags(holder.price_free.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);





        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Toast.makeText(context,free.getId()+"" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FreeAdapter.this.context, Wait_Activity.class);
                intent.putExtra(put.id,free.getId()+"");
                intent.putExtra(put.label,free.getLable());
                intent.putExtra(put.freeprice,free.getFreeprice());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              //  ((Activity)context).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelFrees.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TriangleLabelView lablefree;
        ImageView imgfree;
        TextView txtname,txtview,txtprice,txtlable,price_free;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lablefree=itemView.findViewById(R.id.lablefree);
            imgfree=itemView.findViewById(R.id.imgfree);
            txtname=itemView.findViewById(R.id.txtnamefree);
            txtview=itemView.findViewById(R.id.viewfree);
            txtprice=itemView.findViewById(R.id.pricefree);
            cardView=itemView.findViewById(R.id.card_free);
            price_free=itemView.findViewById(R.id.price_free);

        }
    }

}

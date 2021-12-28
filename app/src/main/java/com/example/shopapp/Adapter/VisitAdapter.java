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
import com.example.shopapp.Model.VisitModel;
import com.example.shopapp.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class VisitAdapter  extends RecyclerView.Adapter<VisitAdapter.ViewHolder> {
    Context context;
    List<VisitModel> visitModels;

    public VisitAdapter(Context context, List<VisitModel> visitModels) {
        this.context = context;
        this.visitModels = visitModels;
    }

    @NonNull
    @Override
    public VisitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.visit_layout,parent,false);
        return new VisitAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitAdapter.ViewHolder holder, int position) {
        VisitModel visitModel= visitModels.get(position);
        DecimalFormat decimalFormat=new DecimalFormat("###,###");
        String price=decimalFormat.format(Integer.valueOf(visitModel.getPrice()));

        holder.txtprice.setText(price+""+"تومان");
        holder.txtview.setText(visitModel.getView());
        holder.txtname.setText(visitModel.getName());

        Picasso.with(context)
                .load(visitModel.getImage())
                .into(holder.imgfree);

        holder.card_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast.makeText(context,free.getId()+"" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VisitAdapter.this.context, Wait_Activity.class);
                intent.putExtra(put.id,visitModel.getId()+"");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(put.freeprice,"");
             //   ((Activity)context).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return visitModels.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
CardView card_visit;
        ImageView imgfree;
        TextView txtname,txtview,txtprice,txtlable;
        Typeface typeface=Typeface.createFromAsset(itemView.getContext().getAssets(),"Vazir-Medium-FD-WOL.ttf");
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgfree=itemView.findViewById(R.id.imgvisit);
            txtname=itemView.findViewById(R.id.txtnamevisit);
            txtview=itemView.findViewById(R.id.viewvisit);
            txtprice=itemView.findViewById(R.id.pricevisit);
            txtname.setTypeface(typeface);
            txtview.setTypeface(typeface);
            txtprice.setTypeface(typeface);
            card_visit=itemView.findViewById(R.id.card_visit);


        }
    }

}


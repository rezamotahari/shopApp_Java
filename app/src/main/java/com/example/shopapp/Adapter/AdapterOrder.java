package com.example.shopapp.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopapp.Model.ModelOrder;
import com.example.shopapp.R;

import java.util.List;


public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.viewholder>  {


    Context context;
    List<ModelOrder> modelOrders;

    public AdapterOrder(Context context, List<ModelOrder> modelOrders) {
        this.context = context;
        this.modelOrders = modelOrders;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutorder,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {


        ModelOrder order = modelOrders.get(i);
        viewholder.textemail.setText(order.getEmail());
        viewholder.textnumber.setText(order.getNumber());
        viewholder.textprice.setText(order.getAllprice()+" "+"تومان");
        viewholder.textdate.setText(order.getDate());
        viewholder.textrefid.setText(order.getRefid());
    }

    @Override
    public int getItemCount() {
        return modelOrders.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textrefid,textemail,textprice,textnumber,textdate;
        Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Vazir-Medium-FD-WOL.ttf");

        public viewholder(@NonNull View itemView) {

            super(itemView);

            cardView = itemView.findViewById(R.id.cardvieworder);
            textdate = itemView.findViewById(R.id.textdateorder);
            textdate.setTypeface(typeface);
            textemail = itemView.findViewById(R.id.textemailorder);
            textemail.setTypeface(typeface);
            textnumber = itemView.findViewById(R.id.textnumberorder);
            textnumber.setTypeface(typeface);
            textprice = itemView.findViewById(R.id.textpriceorder);
            textprice.setTypeface(typeface);
            textrefid = itemView.findViewById(R.id.textrefidorder);
            textrefid.setTypeface(typeface);
        }
    }
}

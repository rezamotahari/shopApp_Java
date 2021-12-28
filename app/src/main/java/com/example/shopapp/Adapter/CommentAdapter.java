package com.example.shopapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopapp.Model.CommentModel;
import com.example.shopapp.R;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    Context context;
    List<CommentModel>commentModel;

    public CommentAdapter(Context context, List<CommentModel> commentModel) {
        this.context = context;
        this.commentModel = commentModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentModel comment=commentModel.get(position);
        holder.ratingBar.setRating(comment.getRating());
        holder.txt_nagative.setText(comment.getNagative());
        holder.txt_positive.setText(comment.getPositive());
        holder.txt_comment.setText(comment.getComment());
        holder.txt_user.setText(comment.getUser());
        Picasso.with(context)
                .load(comment.getImage())
                .skipMemoryCache()
                .into(holder.img_user);


    }

    @Override
    public int getItemCount() {
        return commentModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img_user;
        TextView txt_user,txt_comment,txt_positive,txt_nagative;
        ScaleRatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_user=itemView.findViewById(R.id.img_user_com);
            txt_user=itemView.findViewById(R.id.txt_user_com);
            txt_comment=itemView.findViewById(R.id.txt_comment_com);
            txt_positive=itemView.findViewById(R.id.txt_positive_com);
            txt_nagative=itemView.findViewById(R.id.txt_nagative_com);
            ratingBar=itemView.findViewById(R.id.rating_comment);
        }
    }

}

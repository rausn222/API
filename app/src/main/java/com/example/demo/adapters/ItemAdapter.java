package com.example.demo.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.demo.Display;
import com.example.demo.MainActivity;
import com.example.demo.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.model.ImageModel;
import java.util.ArrayList;

public class ItemAdapter  extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    Context context;
    ArrayList<ImageModel> articles;

    public ItemAdapter(Context context, ArrayList<ImageModel> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new  ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Glide.with(context).load(articles.get(position).getUrl()).into(holder.ivNews);
        holder.ivNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Display.class);
                i.putExtra("index",holder.getAdapterPosition());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("data", "> Size " + articles.size());
        return articles.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView ivNews;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNews = itemView.findViewById(R.id.ivNews);
        }
    }
}
package com.example.kkavalireddy.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by kkavalireddy on 12/20/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    List <String> imageNames;
    List <Integer> images;
    Context context;

    int lastPosition ;

    public CustomAdapter(Context context,List <Integer> images,List <String> imageNames)
    {
        this.context = context;
        this.images =  images;
        this.imageNames = imageNames;

        lastPosition = images.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.imageView.setImageResource(images.get(position));
            holder.textView.setText(String.valueOf(position));//imageNames.get(position)
        if(position == 0 || position == 1)
        {
            holder.cardView.setVisibility(View.INVISIBLE);
        }
        else {
            holder.cardView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return images.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image_view);
            textView = (TextView)itemView.findViewById(R.id.text_view);
            cardView = (CardView)itemView.findViewById(R.id.card_view);
        }

    }
}

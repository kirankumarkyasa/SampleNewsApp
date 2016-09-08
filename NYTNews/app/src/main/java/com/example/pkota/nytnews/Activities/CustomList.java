package com.example.pkota.nytnews.Activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pkota.nytnews.R;
import com.example.pkota.nytnews.utils.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pkota on 07-09-2016.
 */
public class CustomList extends RecyclerView.Adapter<CustomList.MyViewHolder> {

    private List<News> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.count);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.title);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.overflow);
        }
    }

    public CustomList(List<News> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_view, parent, false);

        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getTitle());
        textViewVersion.setText(dataSet.get(listPosition).getDate());
       // imageView.setImageResource(dataSet.get(listPosition).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}



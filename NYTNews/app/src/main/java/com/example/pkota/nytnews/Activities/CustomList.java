package com.example.pkota.nytnews.Activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pkota.nytnews.R;
import com.example.pkota.nytnews.utils.News;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by pkota on 07-09-2016.
 */
public class CustomList extends RecyclerView.Adapter<CustomList.MyViewHolder> {

    private List<News> dataSet;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.title);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.count);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.thumbnail);
        }

    }

    public CustomList(List<News> data,Context context) {
        this.dataSet = data;
        this.context = context;
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
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat desiredDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
        java.util.Date dDate = null;
        try {
            dDate = currentDateFormat.parse( dataSet.get(listPosition).getDate() );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strOutput = desiredDateFormat.format( dDate );
        textViewName.setText(dataSet.get(listPosition).getTitle());
        textViewVersion.setText(strOutput);
        String image = dataSet.get(listPosition).getThumbnail();
        if (image.isEmpty())
        {
            imageView.setImageResource(R.drawable.capture); //default image if empty
        } else{
            Picasso.with(context).load(image).into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}



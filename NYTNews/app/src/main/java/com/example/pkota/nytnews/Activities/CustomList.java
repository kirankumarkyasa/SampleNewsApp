package com.example.pkota.nytnews.Activities;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
public class CustomList  extends RecyclerView.Adapter<CustomList.MyViewHolder> implements RecyclerView.OnItemTouchListener {

    private List<News> dataSet;
    Context context;
    Animation animFadeIn, animFadeOut;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;
       CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.title);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.count);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.thumbnail);
           this.cardView = (CardView) itemView.findViewById(R.id.card_view);
        }

    }

    public CustomList(Context context) {
        this.context = context;
    }

    public void setDataset(List<News> data) {
        this.dataSet = data;
        notifyDataSetChanged();
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
       CardView cardView = holder.cardView;
        Log.d("CustomLost","check");
        SimpleDateFormat currentDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat desiredDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
        java.util.Date dDate = null;
        try {
            dDate = currentDateFormat.parse( dataSet.get(listPosition).getDate() );
        } catch (ParseException e) {
            e.printStackTrace();
        }
     //   imageLoader = new ImageLoader(context);
        String strOutput = desiredDateFormat.format( dDate );
        textViewName.setText(dataSet.get(listPosition).getTitle());
        textViewVersion.setText(strOutput);
        String image = dataSet.get(listPosition).getThumbnail();
        if (image.isEmpty()) {
            imageView.setImageResource(R.drawable.capture); //default image if empty
        } else {
           // imageLoader.DisplayImage(image, imageView);
           Picasso.with(context).load(image).into(imageView);
        }
        animFadeOut = AnimationUtils.loadAnimation(context, R.anim.bounce);
        // start fade out animation
       cardView.startAnimation(animFadeOut);
    }

    @Override
    public int getItemCount() {
        if(dataSet != null) {
            if(dataSet.size() <= 0) {
                return 0;
            }
            return dataSet.size();
        } else {
            return 0;
        }
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        public void onItemClick(View view,List<News> news,int position);
    }

    GestureDetector mGestureDetector;

    public CustomList (Context context, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView,dataSet,view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}



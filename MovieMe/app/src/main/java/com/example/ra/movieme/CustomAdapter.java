package com.example.ra.movieme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ra.movieme.Retrofit.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ra on 8/8/16.
 */
public class CustomAdapter extends ArrayAdapter<Result> {
    List<Result> mResults;

    public CustomAdapter(Context context, List<Result> movieItems){
        super(context,-1);

        mResults= new ArrayList<>();
        if(movieItems != null){
            mResults.addAll(movieItems);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemLayout = inflater.inflate(R.layout.custom_movie_adapter, parent, false);

        ImageView movieImage= (ImageView)itemLayout.findViewById(R.id.movie_card_image);
        TextView movieName=(TextView)itemLayout.findViewById(R.id.movieName);
        TextView movieDescription= (TextView)itemLayout.findViewById(R.id.movieDescription);
        TextView movieRating=(TextView)itemLayout.findViewById(R.id.movieRating);

        Result movieItems = mResults.get(position);

        String name= movieItems.getTitle();
        String description= movieItems.getOverview();
        double rating= movieItems.getVoteAverage();
        String image= movieItems.getBackdropPath();
        int id= movieItems.getId();

        movieName.setText(name);
//        movieDescription.setText(description);
        if(image !=null){
            Picasso.with(parent.getContext()).load("http://image.tmdb.org/t/p/w500/"+image).into(movieImage);
        }else{
            Picasso.with(parent.getContext()).load("http://c3240dd96f54819fb6f2-90846526673b19d9a04c27097b58cb86.r6.cf2.rackcdn.com/2011/01/amc-theaters.jpg").into(movieImage);
        }

//        movieRating.setText(String.valueOf(movieItems.getPopularity()));

        return itemLayout;
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    public void setResults(List<Result> results) {
        mResults.clear();
        if(results != null){
            mResults.addAll(results);
        }
    }
}

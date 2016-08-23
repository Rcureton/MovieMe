package com.example.ra.movieme.Retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ra.movieme.MovieItems;
import com.example.ra.movieme.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {
    @BindView(R.id.movieDetailsImage)ImageView mMovieImage;
    @BindView(R.id.movieDetailsDescription)TextView mMovieDescription;
    @BindView(R.id.movieDetailsName)TextView mMovieName;
    @BindView(R.id.movieDetailsReleaseText)TextView mMovieRelease;
    @BindView(R.id.trailersButton)Button mTrailers;
    @BindView(R.id.reviewsButton)Button mReviews;
    int movieID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

//        Intent intent= getIntent();
//        MovieItems movieItems= intent.getParcelableExtra(MovieItems.MY_ITEMS);
//
//        movieID= movieItems.getMovieId();
//        mMovieName.setText(movieItems.getMovieName());
//        mMovieRelease.setText(movieItems.getMovieRelease());
//        mMovieDescription.setText(movieItems.getMovieDescription());
//        String movieImage= movieItems.getMovieImage();
//        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/"+movieImage).into(mMovieImage);



    }
}

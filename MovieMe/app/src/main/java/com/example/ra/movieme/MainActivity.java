package com.example.ra.movieme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

@BindView(R.id.now_playingButton)ImageButton mNowPlaying;
@BindView(R.id.favoritesButton)ImageButton mFavorites;
@BindView(R.id.topRatedButton)ImageButton mTopRated;
@BindView(R.id.searchButton)ImageButton mSearch;
@BindView(R.id.upcomingButton)ImageButton mComingSoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mComingSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, UpcomingActivity.class);
                startActivity(intent);
            }
        });

        mNowPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, NowPlayingActivity.class);
                startActivity(intent);
            }
        });

        mTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, TopRatedActivity.class);
                startActivity(intent);
            }
        });

        mFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, PopularActivity.class);
                startActivity(intent);
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }
}

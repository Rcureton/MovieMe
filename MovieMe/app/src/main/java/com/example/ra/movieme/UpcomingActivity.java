package com.example.ra.movieme;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ra.movieme.Retrofit.APIClient;
import com.example.ra.movieme.Retrofit.Movie;
import com.example.ra.movieme.Retrofit.MovieAPI;
import com.example.ra.movieme.Retrofit.MovieDetailsActivity;
import com.example.ra.movieme.Retrofit.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingActivity extends AppCompatActivity {

    @BindView(R.id.listviewUpcoming)ListView mMoviesList;
    private ArrayList<Result> mMovies;
    CustomAdapter mAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);
        ButterKnife.bind(this);
        setTitle("Upcoming Movies");

        mMovies= new ArrayList<>();
        mAdapter= new CustomAdapter(this, mMovies);
        context=this;

       final MovieItems movieItems= new MovieItems();

        MovieAPI apiCall= APIClient.getClient().create(MovieAPI.class);
        Call<Movie> upcomingCall= apiCall.getUpcoming(getString(R.string.movie_api_key));
        upcomingCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
               Log.d("RESPONE", response.body().toString());
                List<Result> movieList= response.body().getResults();
                mMoviesList.setAdapter(new CustomAdapter(context,movieList));
                mAdapter.setResults(movieList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

        mMoviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                final Result movieId= mMovies.get(position);


                movieItems.setMovieId(movieId.getId());
                movieItems.setMovieName(movieId.getOriginalTitle());
                movieItems.setMovieTrailer(movieId.getReleaseDate());
                movieItems.setMovieImage(movieId.getBackdropPath());
                movieItems.setMovieDescription(movieId.getOverview());

                Intent intent= new Intent(UpcomingActivity.this, MovieDetailsActivity.class);
                intent.putExtra(MovieItems.MY_ITEMS, movieItems);
                startActivity(intent);
            }
        });
    }
}

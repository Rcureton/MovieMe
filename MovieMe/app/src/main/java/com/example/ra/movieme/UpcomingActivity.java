package com.example.ra.movieme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ra.movieme.Retrofit.APIClient;
import com.example.ra.movieme.Retrofit.Movie;
import com.example.ra.movieme.Retrofit.MovieAPI;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);
        ButterKnife.bind(this);

        mMovies= new ArrayList<>();
        mAdapter= new CustomAdapter(this, mMovies);


//        mAdapter= new ArrayAdapter<Result>(this,mMovies);
        MovieAPI apiCall= APIClient.getClient().create(MovieAPI.class);
        Call<Movie> upcomingCall= apiCall.getUpcoming(getString(R.string.movie_api_key));
        upcomingCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                List<Result> something = response.body().getResults();
                for(int i=0; i < something.size(); i++){
                    Gson gson= new GsonBuilder().create();
                    Result result= gson.fromJson(String.valueOf(something.get(i)), Result.class);
                    mMovies.add(result);
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }
}

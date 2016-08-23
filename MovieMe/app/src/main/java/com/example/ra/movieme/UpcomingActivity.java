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
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        mAdapter.notifyDataSetChanged();

       final MovieItems movieItems= new MovieItems();

        MovieAPI apiCall= APIClient.getClient().create(MovieAPI.class);
        Observable<Movie> upcomingCall= apiCall.getUpcoming(getString(R.string.movie_api_key));
        upcomingCall.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Movie movie) {
                    List<Result> upMovies= movie.getResults();
                        mMoviesList.setAdapter(new CustomAdapter(context,upMovies));
                        mAdapter.setResults(upMovies);
                        mAdapter.notifyDataSetChanged();
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

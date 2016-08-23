package com.example.ra.movieme;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ra.movieme.Retrofit.APIClient;
import com.example.ra.movieme.Retrofit.Movie;
import com.example.ra.movieme.Retrofit.MovieAPI;
import com.example.ra.movieme.Retrofit.Result;

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

public class TopRatedActivity extends AppCompatActivity {

    @BindView(R.id.topRatedListview)ListView mList;
    private ArrayList<Result> mMovies;
    CustomAdapter mAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_rated);
        ButterKnife.bind(this);
        setTitle("Top Rated Movies");

        mMovies= new ArrayList<>();
        mAdapter= new CustomAdapter(this, mMovies);
        context=this;

        final MovieItems movieItems= new MovieItems();

        MovieAPI apiCall= APIClient.getClient().create(MovieAPI.class);
        Observable<Movie> topRatedCall= apiCall.getTopRated(getString(R.string.movie_api_key));
        topRatedCall.subscribeOn(Schedulers.newThread())
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
                    List<Result> topMovies= movie.getResults();
                        mList.setAdapter(new CustomAdapter(context,topMovies));
                        mAdapter.setResults(topMovies);
                        mAdapter.notifyDataSetChanged();

                    }
                });

    }
}

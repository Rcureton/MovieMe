package com.example.ra.movieme;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.editText)EditText mEditText;
    @BindView(R.id.searchListview)ListView mList;
    @BindView(R.id.searchButton)Button mSearch;
    private ArrayList<Result> mMovies;
    CustomAdapter mAdapter;
    Context context;
    String searchText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setTitle("Search Movies");
        final InputMethodManager inputManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mMovies= new ArrayList<>();
        mAdapter= new CustomAdapter(this, mMovies);
        context=this;

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEditText.getText().toString().length()==0){
                    Toast.makeText(SearchActivity.this, "please enter a movie", Toast.LENGTH_SHORT).show();
                }else{
                    searchText=mEditText.getText().toString();
                    mEditText.getText().clear();
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }

                MovieAPI apiCall= APIClient.getSearchClient().create(MovieAPI.class);
                Observable<Movie> searchCall= apiCall.getSearchMovie(getString(R.string.movie_api_key),searchText);
                searchCall.subscribeOn(Schedulers.newThread())
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
                                List<Result> searchMovies= movie.getResults();
                                mList.setAdapter(new CustomAdapter(context,searchMovies));
                                mAdapter.setResults(searchMovies);
                                mAdapter.notifyDataSetChanged();
                            }
                        });
            }
        });


    }
}

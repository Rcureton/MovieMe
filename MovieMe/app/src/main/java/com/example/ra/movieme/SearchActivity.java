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
                Call<Movie> searchCall= apiCall.getSearchMovie(getString(R.string.movie_api_key),searchText);
                searchCall.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Log.d("CALL",call.request().url().toString());
                        List<Result> movieList= response.body().getResults();

                        mList.setAdapter(new CustomAdapter(context,movieList));
                        mAdapter.setResults(movieList);
                        mAdapter.notifyDataSetChanged();
                       String message= response.message();
                        Log.d("MESSAGE", message);
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                    String fail= t.getMessage();
                        Log.d("FAIL", fail);
                    }
                });
            }
        });


    }
}

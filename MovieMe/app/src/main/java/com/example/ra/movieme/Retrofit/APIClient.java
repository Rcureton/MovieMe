package com.example.ra.movieme.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ra on 8/8/16.
 */
public class APIClient {

    //Retrofit API client setup to make the calls to server
    public static final String BASE_URL="https://api.themoviedb.org/3/movie/";
    private static Retrofit retrofit= null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder().
                    baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

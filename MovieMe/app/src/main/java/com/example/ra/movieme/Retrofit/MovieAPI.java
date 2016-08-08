package com.example.ra.movieme.Retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ra on 8/8/16.
 */
public interface MovieAPI {

    String BASE_URL = "https://api.themoviedb.org/3/movie/";

    @GET("{type}")
    Call<Movie> getMovie(@Path("type") String type, @Query("api_key") String api);
    @GET("{id}/videos")Call<Trailers> getTrailers(@Path("id") String id, @Query("api_key") String api);
    @GET("{id}/reviews")Call<Reviews> getReviews(@Path("id") String id, @Query("api_key") String api);
    @GET("top_rated")Call<Movie> getTopRated(@Query("api_key") String api);
    @GET("upcoming")Call<Movie> getUpcoming(@Query("api_key")String api);


}

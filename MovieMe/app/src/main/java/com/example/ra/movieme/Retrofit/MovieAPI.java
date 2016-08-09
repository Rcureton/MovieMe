package com.example.ra.movieme.Retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ra on 8/8/16.
 */
public interface MovieAPI {


    @GET("{type}")
    Call<Movie> getMovie(@Path("type") String type, @Query("api_key") String api);
    @GET("{id}/videos")Call<Trailers> getTrailers(@Path("id") String id, @Query("api_key") String api);
    @GET("{id}/reviews")Call<Reviews> getReviews(@Path("id") String id, @Query("api_key") String api);
    @GET("top_rated")Call<Movie> getTopRated(@Query("api_key") String api);
    @GET("upcoming")Call<Movie> getUpcoming(@Query("api_key")String api);
    @GET("now_playing")Call<Movie> getNowPlaying(@Query("api_key")String api);
    @GET("popular")Call<Movie> getPopular(@Query("api_key")String api);

    @GET("movie?")Call<Movie> getSearchMovie(@Query("api_key") String api, @Query("query") String query);


}

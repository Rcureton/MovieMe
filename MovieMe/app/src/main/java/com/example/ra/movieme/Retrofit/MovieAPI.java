package com.example.ra.movieme.Retrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ra on 8/8/16.
 */
public interface MovieAPI {


    @GET("{type}")
    Call<Movie> getMovie(@Path("type") String type, @Query("api_key") String api);
    @GET("{id}/videos")Call<Trailers> getTrailers(@Path("id") String id, @Query("api_key") String api);
    @GET("{id}/reviews")Call<Reviews> getReviews(@Path("id") String id, @Query("api_key") String api);
    //RxAndroid Observable Calls
   //Top Movies
    @GET("top_rated")
    Observable<Movie> getTopRated(@Query("api_key") String api);

    //Upcoming Movies
    @GET("upcoming")
    Observable<Movie> getUpcoming(@Query("api_key")String api);

    //Now Playing in Theatres
    @GET("now_playing")
    Observable<Movie> getNowPlaying(@Query("api_key")String api);

    //Popular Movies
    @GET("popular")
    Observable<Movie> getPopular(@Query("api_key")String api);
    //Search Movies
    @GET("movie?")
    Observable<Movie> getSearchMovie(@Query("api_key") String api, @Query("query") String query);


}

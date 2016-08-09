package com.example.ra.movieme;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ra on 8/9/16.
 */
public class MovieItems implements Parcelable {


    public final static String MY_ITEMS= "myItems";
    private int movieId;
    private String movieName;
    private String movieTrailer;
    private String movieImage;
    private String movieRelease;
    private String movieDescription;


    public MovieItems(){

    }

    protected MovieItems(Parcel in) {
        movieId = in.readInt();
        movieName = in.readString();
        movieTrailer = in.readString();
        movieImage = in.readString();
        movieRelease= in.readString();
        movieDescription= in.readString();
    }

    public static final Creator<MovieItems> CREATOR = new Creator<MovieItems>() {
        @Override
        public MovieItems createFromParcel(Parcel in) {
            return new MovieItems(in);
        }

        @Override
        public MovieItems[] newArray(int size) {
            return new MovieItems[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt((movieId));
        parcel.writeString(movieName);
        parcel.writeString(movieTrailer);
        parcel.writeString(movieImage);
        parcel.writeString(movieRelease);
        parcel.writeString(movieDescription);
    }
    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieTrailer() {
        return movieTrailer;
    }

    public void setMovieTrailer(String movieTrailer) {
        this.movieTrailer = movieTrailer;
    }

    public String getMovieRelease() {
        return movieRelease;
    }

    public void setMovieRelease(String movieRelease) {
        this.movieRelease = movieRelease;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }
}

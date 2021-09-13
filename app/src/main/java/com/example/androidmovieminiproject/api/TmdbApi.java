package com.example.androidmovieminiproject.api;

import com.example.androidmovieminiproject.model.Movie.MovieDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TmdbApi {

    @GET("movie/{id}?api_key=c14e24f2d5dbc36cecb8a98e82a9a3d6")
    Call<MovieDetail> getMovieDetail(@Path("id") int id);

}

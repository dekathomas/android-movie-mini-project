package com.example.androidmovieminiproject.api;

import com.example.androidmovieminiproject.model.MovieDetail.MovieDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TmdbApi {

    @GET("movie/{id}")
    Call<MovieDetail> getMovieDetail(@Path("id") int id);

}

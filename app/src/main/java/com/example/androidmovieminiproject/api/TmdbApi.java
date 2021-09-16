package com.example.androidmovieminiproject.api;

import com.example.androidmovieminiproject.model.Movie.MovieList;
import com.example.androidmovieminiproject.model.TV.TvList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbApi {

    @GET("movie/{category}?api_key=c14e24f2d5dbc36cecb8a98e82a9a3d6")
    Call<MovieList> getHomeMovies(
            @Path("category") String sCategory
//            ,@Query("page") int sPage
    );


    @GET("tv/{category}?api_key=c14e24f2d5dbc36cecb8a98e82a9a3d6")
    Call<TvList> getTvList(@Path("category") String category, @Query("page") int page);

    @GET("search/movie?api_key=c14e24f2d5dbc36cecb8a98e82a9a3d6&query={query}")
    Call<MovieList> getSearchMovies(
            @Query("query") String sQuery
    );

    @GET("search/tv?api_key=c14e24f2d5dbc36cecb8a98e82a9a3d6&query={query}")
    Call<TvList> getSearchTv(
            @Query("query") String query
    );



}

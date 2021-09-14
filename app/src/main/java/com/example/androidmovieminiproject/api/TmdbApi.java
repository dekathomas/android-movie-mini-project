package com.example.androidmovieminiproject.api;

import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvPopularList;
import com.example.androidmovieminiproject.model.User.UserLogin;

import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbApi {

    @GET("movie/{id}?api_key=c14e24f2d5dbc36cecb8a98e82a9a3d6")
    Call<MovieDetail> getMovieDetail(@Path("id") int id);

    @GET("/movie/{category}")
    Call<List<MovieDetail>> getMovies(
            @Path("category") String sCategory,
            @Query("api_key") String sApiKey,
            @Query("language") String sLanguage,
            @Query("page") int sPage
    );

    @GET("tv/{category}?api_key=c14e24f2d5dbc36cecb8a98e82a9a3d6")
    Call<TvPopularList> getTvList(@Path("category") String category, @Query("page") int page);

}

package com.example.androidmovieminiproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.api.TmdbApi;
import com.example.androidmovieminiproject.database.ListAPI;
import com.example.androidmovieminiproject.model.MovieDetail.MovieDetail;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        getSupportActionBar().hide();

        // TODO must me moved to ViewModel->Adapter->Repository->DAO/Service
        // TODO there is still error onFailure, dont have solution yet
        Retrofit retrofit = RetrofitService.getInstance();
        TmdbApi tmdbApi = retrofit.create(TmdbApi.class);

        Call<MovieDetail> movieDetailCall = tmdbApi.getMovieDetail(566525);
        movieDetailCall.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, retrofit2.Response<MovieDetail> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DetailMovieActivity.this, "There is something wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*MovieDetail movieDetail = response.body();
                TextView name = findViewById(R.id.movieDetailName);
                name.setText(movieDetail.getMovieName());*/
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast.makeText(DetailMovieActivity.this, "System Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
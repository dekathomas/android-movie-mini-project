package com.example.androidmovieminiproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.api.TmdbApi;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.viewmodel.MovieDetailViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DetailMovieActivity extends AppCompatActivity {

    private MovieDetailViewModel viewModel;
    private ProgressBar progressBar;
    private ImageView posterUrl;
    private TextView movieName;
    private TextView releaseDate;
    private TextView genre;
    private TextView rating;
    private TextView websiteUrl;
    private TextView imdbId;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        getSupportActionBar().hide();

        setInitialValue();
    }

    private void setInitialValue() {
        progressBar.setVisibility(View.VISIBLE);

        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        viewModel.findById(566525).observe(this, movieDetail -> {
            if (movieDetail != null) {
                progressBar.setVisibility(View.INVISIBLE);
                setMovieDetailToView(movieDetail);
                return;
            }
        });
    }

    private void setMovieDetailToView(MovieDetail movieDetail) {
        /*posterUrl = findViewById(R.id.movieDetailBackdrop);
        movieName = findViewById(R.id.movieDetailName)
        releaseDate = findViewById(R.id.movieDetailReleaseDate)
        genre = findViewById(R.id.movieDetailGenre)
        rating = findViewById(R.id.movieDetailRating)
        websiteUrl = findViewById(R.id.)
        imdbId = findViewById(R.id.)
        description = findViewById(R.id.)*/
    }
}
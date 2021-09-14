package com.example.androidmovieminiproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.utility.LoadingDialog;
import com.example.androidmovieminiproject.viewmodel.MovieDetailViewModel;

public class DetailMovieActivity extends BaseActivity {

    private MovieDetailViewModel viewModel;
    private ImageView posterUrl;
    private TextView movieName;
    private TextView releaseDate;
    private TextView genre;
    private TextView rating;
    private TextView description;
    private String websiteUrl;
    private String imdbId;
    private LoadingDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();

        loading = new LoadingDialog(DetailMovieActivity.this);
        posterUrl = findViewById(R.id.movieDetailBackdrop);
        movieName = findViewById(R.id.movieDetailName);
        releaseDate = findViewById(R.id.movieDetailReleaseDate);
        genre = findViewById(R.id.movieDetailGenre);
        rating = findViewById(R.id.movieDetailRating);
        description = findViewById(R.id.movieDetailDescription);

        setContentToView();
    }

    private void setContentToView() {
        loading.show();

        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        viewModel.findById(566525).observe(this, movieDetail -> {
            loading.hide();
            if (movieDetail != null) {
                setMovieDetailToView(movieDetail);
                return;
            }
        });
    }

    private void setMovieDetailToView(MovieDetail movieDetail) {
        movieName.setText(movieDetail.getMovieName());
        releaseDate.setText(movieDetail.getReleaseDate());
        genre.setText(movieDetail.getGenre());
        rating.setText(movieDetail.getRating().toString());
        description.setText(movieDetail.getDescription());
    }
}
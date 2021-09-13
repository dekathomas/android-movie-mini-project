package com.example.androidmovieminiproject.adapters;

import android.view.View;

import com.example.androidmovieminiproject.model.Movie.MovieDetail;

public interface MovieClickableCallback {
    void onClick(View view, MovieDetail movieDetail);
}

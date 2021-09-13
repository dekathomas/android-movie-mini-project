package com.example.androidmovieminiproject.model.Movie;

import com.google.gson.annotations.SerializedName;

public class RecommendationMovie {
    @SerializedName("id")
    private int movieId;
    @SerializedName("poster_path")
    private String poster;
}

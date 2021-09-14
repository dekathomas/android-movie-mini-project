package com.example.androidmovieminiproject.model.Home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeMovieList {
    @SerializedName("results")
    private List<HomeDetail> homeDetailList;
}

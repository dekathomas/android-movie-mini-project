package com.example.androidmovieminiproject.model.Home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class HomeMovieList {
    @SerializedName("results")
    private List<HomeDetail> homeDetailList;
}

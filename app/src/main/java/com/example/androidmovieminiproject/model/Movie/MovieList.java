package com.example.androidmovieminiproject.model.Movie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class MovieList {
    @SerializedName("results")
    private List<MovieDetail> movieDetailList;
}

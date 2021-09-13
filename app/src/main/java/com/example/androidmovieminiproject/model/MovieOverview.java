package com.example.androidmovieminiproject.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class MovieOverview {
    private String id;
    @SerializedName("title")
    private String name;
    @SerializedName("poster_path")
    private String img;
    @SerializedName("backdrop_path")
    private String backdrop;
}

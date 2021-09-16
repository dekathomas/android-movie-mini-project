package com.example.androidmovieminiproject.model.TV;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class TvList {
    @SerializedName("results")
    private List<TvDetail> tvPopularList;
}

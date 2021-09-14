package com.example.androidmovieminiproject.model.Home;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Entity(tableName = "home_detail")
@Data
public class HomeDetail {
    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("name")
    private String name;

    @SerializedName("number_of_episodes")
    @ColumnInfo(name = "number_of_episodes")
    private Integer numberOfEpisodes;

    @SerializedName("number_of_seasons")
    @ColumnInfo(name = "number_of_seasons")
    private Integer numberOfSeasons;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    private Double voteAverage;
}

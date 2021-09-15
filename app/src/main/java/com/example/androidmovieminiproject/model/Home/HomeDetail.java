package com.example.androidmovieminiproject.model.Home;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    private String voteAverage;

    @SerializedName("adult")
    public Boolean adult;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("popularity")
    public String popularity;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("title")
    public String title;

    @SerializedName("video")
    public Boolean video;

    @SerializedName("vote_count")
    public String voteCount;
}

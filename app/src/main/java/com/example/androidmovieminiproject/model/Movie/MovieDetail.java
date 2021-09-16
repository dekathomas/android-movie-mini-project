package com.example.androidmovieminiproject.model.Movie;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Entity(tableName = "movie_detail")
@Data
public class MovieDetail {
    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    private String voteAverage;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("popularity")
    public String popularity;

    @SerializedName("title")
    public String title;

    @SerializedName("vote_count")
    public String voteCount;
}

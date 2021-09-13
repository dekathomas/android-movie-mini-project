package com.example.androidmovieminiproject.model.Movie;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
@Entity(tableName = "movie_detail")
public class MovieDetail {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int movieId;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String posterUrl;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String movieName;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String releaseDate;

    private String genre;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private Double rating;

    @ColumnInfo(name = "homepage")
    @SerializedName("homepage")
    private String websiteUrl;

    @ColumnInfo(name = "imdb_id")
    @SerializedName("imdb_id")
    private String imdbId;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String description;

    /*private List<RecommendationMovie> recommendationMovies;

    private List<RecommendationMovie> similarMovies;*/
}

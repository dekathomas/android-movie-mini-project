package com.example.androidmovieminiproject.model.TV;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Entity(tableName = "tv_detail")
@Data
public class TvDetail {
    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    private String language;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    private String voteAverage;

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    private String voteCount;

    @SerializedName("overview")
    private String overview;

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    // TODO gatau bakal berguna gak nanti
    @SerializedName("homepage")
    private String homepage;

    @SerializedName("number_of_episodes")
    @ColumnInfo(name = "number_of_episodes")
    private Integer numberOfEpisodes;

    @SerializedName("number_of_seasons")
    @ColumnInfo(name = "number_of_seasons")
    private Integer numberOfSeasons;

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    private String posterPath;
}

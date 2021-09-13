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

    private List<RecommendationMovie> recommendationMovies;

    private List<RecommendationMovie> similarMovies;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RecommendationMovie> getRecommendationMovies() {
        return recommendationMovies;
    }

    public void setRecommendationMovies(List<RecommendationMovie> recommendationMovies) {
        this.recommendationMovies = recommendationMovies;
    }

    public List<RecommendationMovie> getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(List<RecommendationMovie> similarMovies) {
        this.similarMovies = similarMovies;
    }
}

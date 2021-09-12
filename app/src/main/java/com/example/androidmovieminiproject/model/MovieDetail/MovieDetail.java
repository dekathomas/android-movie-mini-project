package com.example.androidmovieminiproject.model.MovieDetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetail {
    @SerializedName("id")
    private int movieId;
    @SerializedName("backdrop_path")
    private String posterUrl;
    @SerializedName("title")
    private String movieName;
    @SerializedName("release_date")
    private String releaseDate;
    private String genre;
    @SerializedName("vote_average")
    private Double rating;
    @SerializedName("homepage")
    private String websiteUrl;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("overview")
    private String description;
    private List<RecommendationMovie> recommendationMovies;
    private List<RecommendationMovie> similarMovies;

    public MovieDetail(int movieId, String posterUrl, String releaseDate,
                       String genre, Double rating, String websiteUrl, String imdbId,
                       String description, List<RecommendationMovie> recommendationMovies,
                       List<RecommendationMovie> similarMovies
    ) {
        this.movieId = movieId;
        this.posterUrl = posterUrl;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.rating = rating;
        this.websiteUrl = websiteUrl;
        this.imdbId = imdbId;
        this.description = description;
        this.recommendationMovies = recommendationMovies;
        this.similarMovies = similarMovies;
    }

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

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}

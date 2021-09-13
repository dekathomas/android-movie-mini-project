package com.example.androidmovieminiproject.database;

public class ListAPI {
//    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=c14e24f2d5dbc36cecb8a98e82a9a3d6";
    public static String BASE_URL = "http://api.themoviedb.org/3/";
    public static String APIKEY = "c14e24f2d5dbc36cecb8a98e82a9a3d6";
//    ?api_key=
    public static String CATEGORY = "popular";
    public static String LANGUAGE = "en-US";
    public static int PAGE = 1;
    public static String SEARCH_MOVIE = "search/movie?";
    public static String SEARCH_TV = "search/tv?";
    public static String QUERY = "&query=";
    public static String MOVIE_PLAYNOW = "movie/now_playing?";
    public static String MOVIE_POPULAR = "discover/movie?";
    public static String TV_PLAYNOW = "tv/on_the_air?";
    public static String TV_POPULAR = "discover/tv?";
    public static String URLIMAGE = "https://image.tmdb.org/t/p/w780/";
    public static String URLFILM = "https://www.themoviedb.org/movie/";
    public static String NOTIF_DATE = "&primary_release_date.gte=";
    public static String REALESE_DATE = "&primary_release_date.lte=";
    public static String MOVIE_VIDEO = "movie/{id}/videos?";
    public static String TV_VIDEO = "tv/{id}/videos?";

    public static String ACCESS_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhN2QzMTM0ZDY5OTgwNWYyZTc0N2QzMTM4Y2UzNzk4MiIsInN1YiI6IjVkMWYzZmI2OTRkOGE4MDQ5YTNlMTEzZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QZ8VWxJx3DBo-0Wtbptrn2zKfGNwGv1tqz6BQjUU1CE";
}

package com.example.androidmovieminiproject.api;

import com.example.androidmovieminiproject.database.ListAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static TmdbApi tmdbApi;
    public OneApi oneApi;

    public RetrofitService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        tmdbApi = new Retrofit.Builder()
                .baseUrl(ListAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(TmdbApi.class);

        oneApi = new Retrofit.Builder()
                .baseUrl(ListAPI.ONE_INDONESIA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(OneApi.class);
    }

    public static TmdbApi getAPI() {
        return tmdbApi;
    }

    public OneApi getOneApi() {
        return oneApi;
    }

}

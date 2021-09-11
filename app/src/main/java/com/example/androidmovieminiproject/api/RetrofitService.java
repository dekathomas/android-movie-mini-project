package com.example.androidmovieminiproject.api;

import com.example.androidmovieminiproject.database.ListAPI;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public static Retrofit getInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .header("Authorization", ListAPI.ACCESS_TOKEN)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        return new Retrofit.Builder()
                .baseUrl(ListAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

}

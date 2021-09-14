package com.example.androidmovieminiproject.api;

import com.example.androidmovieminiproject.model.User.UserLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OneApi {

    @FormUrlEncoded
    @Headers("X-API-KEY: 454041184B0240FBA3AACD15A1F7A8BB")
    @POST("user/login")
    Call<UserLogin> login(@Field("username") String email, @Field("password") String password);

}

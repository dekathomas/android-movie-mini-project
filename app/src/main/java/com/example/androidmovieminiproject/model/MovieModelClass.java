package com.example.androidmovieminiproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.androidmovieminiproject.api.RetrofitService;

public class MovieModelClass extends AndroidViewModel {
    private RetrofitService retrofitService;

    public MovieModelClass(@NonNull Application application) {
        super(application);
        retrofitService = new RetrofitService();
    }


    public RetrofitService getRetrofitService(){
        return  retrofitService;
    }

}
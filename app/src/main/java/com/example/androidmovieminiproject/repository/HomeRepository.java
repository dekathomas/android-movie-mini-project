package com.example.androidmovieminiproject.repository;

import android.app.Application;

import com.example.androidmovieminiproject.dao.HomeDao;
import com.example.androidmovieminiproject.database.AppDatabase;
import com.example.androidmovieminiproject.model.Home.HomeDetail;
import com.example.androidmovieminiproject.model.Home.HomeMovieList;
import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.model.TV.TvDetail;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private final HomeDao homeDao;

    public HomeRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        homeDao = database.homeDao();
    }

    public void getMovieList(requestCallback callback) {
        RetrofitService retrofitService = new RetrofitService();
        retrofitService.getAPI()
            .getHomeMovies("popular")
            .enqueue(new Callback<HomeMovieList>() {
                @Override
                public void onResponse(Call<HomeMovieList> call, Response<HomeMovieList> response) {
                    callback.onSuccess(response.body().getHomeDetailList());
                }

                @Override
                public void onFailure(Call<HomeMovieList> call, Throwable t) {
                    callback.onFailed(t.getMessage());
                }
            });
    }

    public void insertHomeDetail(HomeDetail homeDetail) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                homeDao.insert(homeDetail);
            }
        });
    }

    public interface requestCallback {
        void onSuccess(List<HomeDetail> homeDetail);
        void onFailed(String message);
    }
}

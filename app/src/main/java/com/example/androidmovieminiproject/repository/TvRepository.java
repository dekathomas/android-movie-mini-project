package com.example.androidmovieminiproject.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.dao.TvDao;
import com.example.androidmovieminiproject.database.AppDatabase;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.model.TV.TvPopularList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvRepository {
    private TvDao tvDao;

    public TvRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        tvDao = database.tvDao();
    }

    public void getAllPopularTv(requestCallback callback) {
        // Get data from db

        // Get data from API
        RetrofitService.getAPI()
            .getTvList("popular", 1)
            .enqueue(new Callback<TvPopularList>() {
                @Override
                public void onResponse(Call<TvPopularList> call, Response<TvPopularList> response) {
                    callback.onSuccess(response.body().getTvPopularList());
                }

                @Override
                public void onFailure(Call<TvPopularList> call, Throwable t) {
                    callback.onFailed(t.getMessage());
                }
            });
    }

    public interface requestCallback {
        void onSuccess(List<TvDetail> tvDetail);
        void onFailed(String message);
    }
}

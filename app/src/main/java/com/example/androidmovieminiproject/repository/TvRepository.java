package com.example.androidmovieminiproject.repository;

import android.app.Application;

import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.dao.TvDao;
import com.example.androidmovieminiproject.database.AppDatabase;
import com.example.androidmovieminiproject.model.Movie.MovieList;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.model.TV.TvList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvRepository {
    private final TvDao tvDao;

    public TvRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        tvDao = database.tvDao();
    }

    public interface requestCallback {
        void onSuccess(List<TvDetail> tvDetail);
        void onFailed(String message);
    }

    public void getAllPopularTv(requestCallback callback) {
        RetrofitService retrofitService = new RetrofitService();
        retrofitService.getAPI()
            .getTvList("popular", 1)
            .enqueue(new Callback<TvList>() {
                @Override
                public void onResponse(Call<TvList> call, Response<TvList> response) {
                    callback.onSuccess(response.body().getTvPopularList());
                }

                @Override
                public void onFailure(Call<TvList> call, Throwable t) {
                    callback.onFailed(t.getMessage());
                }
            });
    }

    public void insertTvDetail(TvDetail tvDetail) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                tvDao.insert(tvDetail);
            }
        });
    }

    public void searchTv(String name, requestCallback callback) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<TvDetail> listTvDetail = tvDao.searchByName(name);

                if (listTvDetail.size() > 0) {
                    callback.onSuccess(listTvDetail);
                } else {
//                    callback.onSuccess(null);
                    searchTvByNameFromAPI(name, callback);
                }
            }
        });
    }

    private void searchTvByNameFromAPI(String name, requestCallback callback) {
        RetrofitService retrofitService = new RetrofitService();
        retrofitService.getAPI()
                .searchTvByName(name)
                .enqueue(new Callback<TvList>() {
                    @Override
                    public void onResponse(Call<TvList> call, Response<TvList> response) {
                        if (response.body().getTvPopularList().size() > 0) {
                            callback.onSuccess(response.body().getTvPopularList());
                        } else {
                            callback.onSuccess(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<TvList> call, Throwable t) {
                        callback.onFailed(t.getMessage());
                    }
                });
    }

    public void getTvListFromDB(requestCallback callback) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<TvDetail> listTvDetail = tvDao.findAll();

                if (listTvDetail.size() > 0) {
                    callback.onSuccess(listTvDetail);
                } else {
                    // TODO if there is no data, search through API
                    callback.onSuccess(null);
                }
            }
        });
    }
}

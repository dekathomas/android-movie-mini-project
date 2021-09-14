package com.example.androidmovieminiproject.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.dao.MovieDao;
import com.example.androidmovieminiproject.database.AppDatabase;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailRepository {
    private final MovieDao movieDao;
    private LiveData<MovieDetail> movieDetail;
    private final RetrofitService retrofit;

    public MovieDetailRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        movieDao = database.movieDetailDao();
        retrofit = new RetrofitService();
    }

    public void findById(int id, requestCallback callback) {
        movieDetail = movieDao.findById(id);

        if (movieDetail.getValue() == null) {
            retrofit.getAPI()
                    .getMovieDetail(566525)
                    .enqueue(new Callback<MovieDetail>() {
                        @Override
                        public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                            callback.onSuccess(response.body());
                        }

                        @Override
                        public void onFailure(Call<MovieDetail> call, Throwable t) {
                            callback.onFailed(t.getMessage());
                        }
                    });
        } else {
            callback.onSuccess(movieDetail.getValue());
        }
    }

    public void insert(MovieDetail movieDetail) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDao.insert(movieDetail);
            }
        });
    }

    public void update(MovieDetail movieDetail) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDao.update(movieDetail);
            }
        });
    }

    public void delete(MovieDetail movieDetail) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDao.delete(movieDetail);
            }
        });
    }

    public interface requestCallback {
        void onSuccess(MovieDetail movieDetail);
        void onFailed(String message);
    }
}

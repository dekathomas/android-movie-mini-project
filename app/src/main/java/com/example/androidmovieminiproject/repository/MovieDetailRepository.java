package com.example.androidmovieminiproject.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.dao.MovieDetailDao;
import com.example.androidmovieminiproject.database.Database;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieDetailRepository {
    private MovieDetailDao movieDetailDao;
    private LiveData<MovieDetail> movieDetail;
    private RetrofitService retrofit;

    public MovieDetailRepository(Application application) {
        Database database = Database.getInstance(application);
        movieDetailDao = database.movieDetailDao();
        retrofit = new RetrofitService();
    }

    public void findById(int id, requestCallback callback) {
        movieDetail = movieDetailDao.findById(id);

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
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDetailDao.insert(movieDetail);
            }
        });
    }

    public void update(MovieDetail movieDetail) {
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDetailDao.update(movieDetail);
            }
        });
    }

    public void delete(MovieDetail movieDetail) {
        Database.executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDetailDao.delete(movieDetail);
            }
        });
    }

    public interface requestCallback {
        void onSuccess(MovieDetail movieDetail);
        void onFailed(String message);
    }
}

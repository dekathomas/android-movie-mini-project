package com.example.androidmovieminiproject.repository;

import android.app.Application;

import com.example.androidmovieminiproject.dao.MovieDao;
import com.example.androidmovieminiproject.database.AppDatabase;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.Movie.MovieList;
import com.example.androidmovieminiproject.api.RetrofitService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private final MovieDao movieDao;

    public MovieRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        movieDao = database.homeDao();
    }

    public void getPopularMovieList(requestCallback callback) {
        RetrofitService retrofitService = new RetrofitService();
        retrofitService.getAPI()
            .getHomeMovies("popular")
            .enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                    callback.onSuccess(response.body().getMovieDetailList());
                }

                @Override
                public void onFailure(Call<MovieList> call, Throwable t) {
                    callback.onFailed(t.getMessage());
                }
            });
    }

    public void getUpcomingMovieList(requestCallback callback) {
        RetrofitService retrofitService = new RetrofitService();
        retrofitService.getAPI()
            .getHomeMovies("upcoming")
            .enqueue(new Callback<MovieList>() {
                @Override
                public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                    callback.onSuccess(response.body().getMovieDetailList());
                }

                @Override
                public void onFailure(Call<MovieList> call, Throwable t) {
                    callback.onFailed(t.getMessage());
                }
            });
    }

    public void insertMovieDetail(MovieDetail movieDetail) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDao.insert(movieDetail);
            }
        });
    }

    public interface requestCallback {
        void onSuccess(List<MovieDetail> movieDetail);
        void onFailed(String message);
    }

    public void getMovieListFromDB(requestCallback callback) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<MovieDetail> listMovieDetail = movieDao.findAll();

                if (listMovieDetail.size() > 0) {
                    callback.onSuccess(listMovieDetail);
                } else {
                    // TODO if there is no data, search through API
                    callback.onSuccess(null);
                }
            }
        });
    }
}

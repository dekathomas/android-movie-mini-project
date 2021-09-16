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

    public interface requestCallback {
        void onSuccess(List<MovieDetail> movieDetail);
        void onFailed(String message);
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

    public void searchMovieByName(String name, requestCallback callback) {
        // Search movie from DB first
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<MovieDetail> listMovieDetail = movieDao.searchByName(name);
                System.out.println("===============================================");
                System.out.println("SEARCH FROM DB");
                if (listMovieDetail.size() > 0) {
                    callback.onSuccess(listMovieDetail);
                } else {
                    System.out.println("gaada nih, cari di API DONG");
                    // If not found, find via API
                    searchMovieByNameFromAPI(name, callback);
                }
            }
        });
    }

    private void searchMovieByNameFromAPI(String name, requestCallback callback) {
        RetrofitService retrofitService = new RetrofitService();
        retrofitService.getAPI()
                .searchMovieByName(name)
                .enqueue(new Callback<MovieList>() {
                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                        if (response.body().getMovieDetailList().size() > 0) {
                            System.out.println("Nah ketemu nih di API");
                            callback.onSuccess(response.body().getMovieDetailList());
                        } else {
                            System.out.println("di API juga kagak ada COK");
                            callback.onSuccess(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieList> call, Throwable t) {
                        callback.onFailed(t.getMessage());
                    }
                });
    }
}
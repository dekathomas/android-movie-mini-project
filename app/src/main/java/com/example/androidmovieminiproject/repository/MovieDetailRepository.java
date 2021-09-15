package com.example.androidmovieminiproject.repository;

import android.app.Application;
import android.graphics.Movie;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.dao.HomeDao;
import com.example.androidmovieminiproject.dao.MovieDao;
import com.example.androidmovieminiproject.dao.TvDao;
import com.example.androidmovieminiproject.database.AppDatabase;
import com.example.androidmovieminiproject.model.Home.HomeDetail;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;

public class MovieDetailRepository {
    private MovieDao movieDao;
    private TvDao tvDao;
    private HomeDao homeDao;
    private LiveData<MovieDetail> movieDetail;
    private LiveData<TvDetail> tvDetail;
    private RetrofitService retrofit;

    public MovieDetailRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        movieDao = database.movieDetailDao();
        tvDao = database.tvDao();
        retrofit = new RetrofitService();
    }

    private LiveData<MovieDetail> getMovieDetail(int id, requestCallback callback) {
        // Only get data from DB
        movieDetail = movieDao.findById(id);
        // TODO check this one, i think its alway null
        if (movieDetail.getValue() == null) {
            return null;
        }

        return movieDetail;

        /*if (movieDetail.getValue() == null) {
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
        }*/
    }

    public LiveData<TvDetail> getTvDetailById(int id) {
        return tvDao.findTvDetailById(id);
    }

    public LiveData<HomeDetail> getHomeDetailById(int id) {
        return homeDao.findHomeDetailById(id);
    }

    public interface requestCallback {
        void onSuccessTv(LiveData<TvDetail> tvDetail);
        void onSuccessMovie(MovieDetail movieDetail);
        void onFailed(String message);

    }
}

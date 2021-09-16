package com.example.androidmovieminiproject.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.androidmovieminiproject.dao.MovieDao;
import com.example.androidmovieminiproject.dao.TvDao;
import com.example.androidmovieminiproject.database.AppDatabase;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;

public class FilmDetailRepository {
    private TvDao tvDao;
    private MovieDao movieDao;

    public FilmDetailRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        tvDao = database.tvDao();
        movieDao = database.homeDao();
    }

    public LiveData<TvDetail> getTvDetailById(int id) {
        return tvDao.findTvDetailById(id);
    }

    public LiveData<MovieDetail> getHomeDetailById(int id) {
        return movieDao.findHomeDetailById(id);
    }
}

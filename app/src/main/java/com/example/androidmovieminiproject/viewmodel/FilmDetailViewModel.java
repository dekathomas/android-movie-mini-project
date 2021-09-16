package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.repository.FilmDetailRepository;

public class FilmDetailViewModel extends AndroidViewModel {
    private FilmDetailRepository repository;

    public FilmDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new FilmDetailRepository(application);
    }

    public LiveData<TvDetail> findTvDetailById(int id) {
        return repository.getTvDetailById(id);
    }

    public LiveData<MovieDetail> findHomeDetailById(int id) {
        return repository.getHomeDetailById(id);
    }
}

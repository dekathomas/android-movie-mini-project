package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.Home.HomeDetail;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.repository.MovieDetailRepository;

public class MovieDetailViewModel extends AndroidViewModel {
    private MovieDetailRepository repository;
    public MutableLiveData<MovieDetail> movieDetailLiveData = new MutableLiveData<>();
    public LiveData<TvDetail> tvDetailMutableLiveData;

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieDetailRepository(application);
    }

    public LiveData<TvDetail> findTvDetailById(int id, String type) {
        return repository.getTvDetailById(id);
    }

    public LiveData<HomeDetail> findHomeDetailById(int id, String type) {
        return repository.getHomeDetailById(id);
    }
}

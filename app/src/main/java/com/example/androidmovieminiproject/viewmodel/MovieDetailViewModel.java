package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.repository.MovieDetailRepository;

public class MovieDetailViewModel extends AndroidViewModel {
    private MovieDetailRepository repository;
    private MutableLiveData<MovieDetail> movieDetailLiveData;

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieDetailRepository(application);
    }

    public LiveData<MovieDetail> findById(int id) {
        repository.findById(id, new MovieDetailRepository.requestCallback() {
            @Override
            public void onSuccess(MovieDetail movieDetail) {
                movieDetailLiveData.setValue(movieDetail);
            }

            @Override
            public void onFailed(String message) {
                Log.d(String.valueOf(R.string.logger_retrofit), message);
                movieDetailLiveData.setValue(null);
            }
        });

        return movieDetailLiveData;
    }

    public void insert(MovieDetail movieDetail) {
        repository.insert(movieDetail);
    }

    public void update(MovieDetail movieDetail) {
        repository.update(movieDetail);
    }

    public void delete(MovieDetail movieDetail) {
        repository.delete(movieDetail);
    }
}

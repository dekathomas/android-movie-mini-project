package com.example.androidmovieminiproject.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.repository.MovieRepository;
import com.example.androidmovieminiproject.utility.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private final MovieRepository movieRepository;
    public MutableLiveData<List<MovieDetail>> mutablePopularMovieList;
    public MutableLiveData<List<MovieDetail>> mutableUpcomingMovieList;
    public List<MovieDetail> movieList = new ArrayList<>();

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        mutablePopularMovieList = new MutableLiveData<>();
        mutableUpcomingMovieList = new MutableLiveData<>();
    }

    public void getPopularMovieList() {
        movieRepository.getPopularMovieList(new MovieRepository.requestCallback() {
            @Override
            public void onSuccess(List<MovieDetail> movieDetailList) {
                if (movieDetailList.size() != 0) {
                    movieList.addAll(movieDetailList);
                    mutablePopularMovieList.setValue(movieDetailList);
                }
                else {
                    mutablePopularMovieList.setValue(null);
                }
            }

            @Override
            public void onFailed(String message) {
                AlertDialog.error(getApplication(), String.valueOf(R.string.error_general));
                mutablePopularMovieList.setValue(null);
            }
        });
    }

    public void getUpcomingMovieList() {
        movieRepository.getUpcomingMovieList(new MovieRepository.requestCallback() {
            @Override
            public void onSuccess(List<MovieDetail> movieDetailList) {
                if (movieDetailList.size() != 0) {
                    movieList.addAll(movieDetailList);
                    mutableUpcomingMovieList.setValue(movieDetailList);
                }
                else {
                    mutableUpcomingMovieList.setValue(null);
                }
            }

            @Override
            public void onFailed(String message) {
                AlertDialog.error(getApplication(), String.valueOf(R.string.error_general));
                mutableUpcomingMovieList.setValue(null);
            }
        });
    }

    public MovieDetail getHome(int position) {
        if (movieList.size() == 0) {
            return null;
        }

        return movieList.get(position);
    }

    public void insertHomeDetail(MovieDetail movieDetail) {
        movieRepository.insertHomeDetail(movieDetail);
    }

}

package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.Movie.MovieDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;
import com.example.androidmovieminiproject.repository.MovieRepository;
import com.example.androidmovieminiproject.repository.TvRepository;
import com.example.androidmovieminiproject.utility.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {
    private TvRepository tvRepository;
    private MovieRepository movieRepository;
    public MutableLiveData<List<TvDetail>> tvList = new MutableLiveData<>();
    public MutableLiveData<List<MovieDetail>> movieList = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application) {
        super(application);
        tvRepository = new TvRepository(application);
        movieRepository = new MovieRepository(application);
    }

    public TvDetail getDetailTvFromDB(int position) {
        if (tvList == null && tvList.getValue().size() == 0) {
            return null;
        }

        return tvList.getValue().get(position);
    }

    public MovieDetail getDetailMovieFromDB(int position) {
        if (movieList == null || movieList.getValue().size() == 0) {
            return null;
        }

        return movieList.getValue().get(position);
    }

    public void searchMovieByName(String name) {
        movieRepository.searchMovieByName(name, new MovieRepository.requestCallback() {
            @Override
            public void onSuccess(List<MovieDetail> movieDetailList) {
                movieList.postValue(movieDetailList);
            }

            @Override
            public void onFailed(String message) {
                movieList.postValue(new ArrayList<>());
            }
        });
    }

    public void searchTvByName(String name){
        tvRepository.searchTv(name, new TvRepository.requestCallback() {
            @Override
            public void onSuccess(List<TvDetail> tvDetail) {
                tvList.postValue(tvDetail);
            }

            @Override
            public void onFailed(String message) {
                tvList.postValue(new ArrayList<>());
            }
        });
    }

    public void insertMovieToDB(MovieDetail movieDetail) {
        movieRepository.insertMovieDetail(movieDetail);
    }

    public void insertTvToDB(TvDetail tvDetail) {
        tvRepository.insertTvDetail(tvDetail);
    }
}

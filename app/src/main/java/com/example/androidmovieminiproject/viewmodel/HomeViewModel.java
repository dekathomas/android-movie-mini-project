package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.androidmovieminiproject.R;
import com.example.androidmovieminiproject.model.Home.HomeDetail;
import com.example.androidmovieminiproject.repository.HomeRepository;

import java.util.List;

public class HomeViewModel {
    private HomeRepository homeRepository;
    public MutableLiveData<List<HomeDetail>> movieList;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeRepository = new HomeRepository(application);
        movieList = new MutableLiveData<>();
    }

    public void getMovieList() {
        homeRepository.getMovieList(new HomeRepository.requestCallback() {
            @Override
            public void onSuccess(List<HomeDetail> movieDetailList) {
                if (movieDetailList.size() != 0) {
                    movieList.setValue(moveDetailList);
                }
                else {
                    movieList.setValue(null);
                }
            }

            @Override
            public void onFailed(String message) {
                AlertDialog.error(getApplication(), String.valueOf(R.string.error_general));
                movieList.setValue(null);
            }
        });
    }

}

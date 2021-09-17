package com.example.androidmovieminiproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidmovieminiproject.model.Favourite;
import com.example.androidmovieminiproject.repository.FavouriteRepository;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {
    private FavouriteRepository repository;

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        repository = new FavouriteRepository(application);
    }

    public LiveData<List<Favourite>> getAll() {
        return repository.getAll();
    }

    public void insert(Favourite favourite) {
        repository.insert(favourite);
    }

    public void delete(Favourite favourite) {
        repository.delete(favourite);
    }

}

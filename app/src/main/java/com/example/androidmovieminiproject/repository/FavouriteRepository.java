package com.example.androidmovieminiproject.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.androidmovieminiproject.api.RetrofitService;
import com.example.androidmovieminiproject.dao.FavouriteDao;
import com.example.androidmovieminiproject.database.AppDatabase;
import com.example.androidmovieminiproject.model.Favourite;

import java.util.List;

public class FavouriteRepository {
    private FavouriteDao favouriteDao;

    public FavouriteRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        favouriteDao = database.favouriteDao();
    }

    public LiveData<List<Favourite>> getAll() {
        return favouriteDao.getAll();
    }

    public void insert(Favourite favourite) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                favouriteDao.insert(favourite);
            }
        });
    }

    public void delete(Favourite favourite) {
        AppDatabase.executorService.execute(new Runnable() {
            @Override
            public void run() {
                favouriteDao.delete(favourite);
            }
        });
    }
}

package com.example.androidmovieminiproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.androidmovieminiproject.model.Home.HomeDetail;

import java.util.List;

@Dao
public interface HomeDao {
    @Query("SELECT * FROM home_detail")
    public LiveData<List<HomeDetail>> getMovieList();

    @Insert
    public void insert(HomeDetail homeDetail);
}

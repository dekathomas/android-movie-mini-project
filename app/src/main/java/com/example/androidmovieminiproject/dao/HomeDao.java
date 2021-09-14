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
    LiveData<List<HomeDetail>> getMovieList();

    @Insert
    void insert(HomeDetail homeDetail);
}

package com.example.androidmovieminiproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.androidmovieminiproject.model.Home.HomeDetail;
import com.example.androidmovieminiproject.model.TV.TvDetail;

import java.util.List;

@Dao
public interface HomeDao {
    @Query("SELECT * FROM home_detail")
    public LiveData<List<HomeDetail>> getMovieList();

    @Query("SELECT * FROM home_detail WHERE id = :id")
    public LiveData<HomeDetail> findHomeDetailById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(HomeDetail homeDetail);
}

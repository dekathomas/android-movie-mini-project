package com.example.androidmovieminiproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.androidmovieminiproject.model.TV.TvDetail;

import java.util.List;

@Dao
public interface TvDao {

    @Query("SELECT * FROM tv_detail")
    public List<TvDetail> getPopularList();

    @Query("SELECT * FROM tv_detail WHERE id = :id")
    public LiveData<TvDetail> findTvDetailById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(TvDetail tvDetail);


}

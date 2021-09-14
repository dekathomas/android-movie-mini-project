package com.example.androidmovieminiproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.androidmovieminiproject.model.TV.TvDetail;

import java.util.List;

@Dao
public interface TvDao {

    @Query("SELECT * FROM tv_detail")
    LiveData<List<TvDetail>> getPopularList();

    @Insert
    void insert(TvDetail tvDetail);

}

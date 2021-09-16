package com.example.androidmovieminiproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.androidmovieminiproject.model.Movie.MovieDetail;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie_detail")
    public List<MovieDetail> findAll();

    @Query("SELECT * FROM movie_detail WHERE id = :id")
    public LiveData<MovieDetail> findHomeDetailById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(MovieDetail movieDetail);
}

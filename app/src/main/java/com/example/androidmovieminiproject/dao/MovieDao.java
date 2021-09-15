package com.example.androidmovieminiproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidmovieminiproject.model.Movie.MovieDetail;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie_detail WHERE id = :id")
    LiveData<MovieDetail> findById(int id);

    @Insert
    void insert(MovieDetail movieDetail);

    @Update
    void update(MovieDetail movieDetail);

    @Delete
    void delete(MovieDetail movieDetail);
}

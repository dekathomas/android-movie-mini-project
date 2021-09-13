package com.example.androidmovieminiproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidmovieminiproject.model.Movie.MovieDetail;

@Dao
public interface MovieDetailDao {

    @Query("SELECT * FROM movie_detail WHERE id = :id")
    public LiveData<MovieDetail> findById(int id);

    @Insert
    public void insert(MovieDetail movieDetail);

    @Update
    public void update(MovieDetail movieDetail);

    @Delete
    public void delete(MovieDetail movieDetail);

}

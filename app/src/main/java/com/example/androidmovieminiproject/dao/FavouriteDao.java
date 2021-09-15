package com.example.androidmovieminiproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.androidmovieminiproject.model.Favourite;

import java.util.List;

@Dao
public interface FavouriteDao {

    @Query("SELECT * FROM favourite")
    public LiveData<List<Favourite>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Favourite favourite);

    @Delete
    public void delete(Favourite favourite);

}

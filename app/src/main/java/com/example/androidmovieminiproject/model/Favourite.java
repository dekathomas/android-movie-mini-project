package com.example.androidmovieminiproject.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity(tableName = "favourite")
@Data
public class Favourite {
    @PrimaryKey()
    @ColumnInfo(name = "item_id")
    private int itemId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "backrop_url")
    private String backdropUrl;

    @ColumnInfo(name = "poster_url")
    private String posterUrl;

    @ColumnInfo(name = "vote_average")
    private String voteAverage;

    @ColumnInfo(name = "vote_count")
    private String voteCount;

    @ColumnInfo(name = "type")
    private String type;

}

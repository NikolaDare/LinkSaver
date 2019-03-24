package com.nikola.linkbar.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nikola.linkbar.data.db.model.Links;

import java.util.List;

@Dao
public interface LinkDao {
    @Insert
    void insert(Links link);

    @Update
    void update(Links link);

    @Delete
    void delete(Links link);

    @Query("Delete From list")
    void deleteAllLinks();

    @Query("Select * from list")
    LiveData<List<Links>> getAllLinks();

    @Query("Select * from list where favorite=1")
    LiveData<List<Links>> getAllFav();

    @Query("Select * from list where viewed > 0 Order by viewed DESC limit 10")
    LiveData<List<Links>> getAllViewed();




}

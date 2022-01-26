package com.example.inventory2.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventory2.model.Dependency;

import java.util.List;

@Dao
public interface DependencyDAO {
    @Insert
    long insert (Dependency dependency);

    @Update
    void update (Dependency dependency);

    @Delete
    void delete (Dependency dependency);

    @Query("DELETE FROM DEPENDENCY")
    void deleteAll();

    @Query("SELECT * FROM DEPENDENCY ORDER BY shortName ASC")
    List<Dependency> select();

    @Query("SELECT * FROM DEPENDENCY WHERE shortName =:shortname")
    Dependency findByShortName(String shortname);
}

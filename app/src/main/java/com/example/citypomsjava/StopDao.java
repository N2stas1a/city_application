package com.example.citypomsjava;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface StopDao {
    @Insert
    void insertStop(MySQLiteData.Stop stop);

    @Query("SELECT * FROM Stop WHERE transportNumber = :transportNumber")
    List<MySQLiteData.Stop> getStopsForTransport(int transportNumber);
}

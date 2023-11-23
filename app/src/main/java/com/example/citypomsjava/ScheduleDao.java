package com.example.citypomsjava;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ScheduleDao {
    @Insert
    void insertSchedule(MySQLiteData.Schedule schedule);

    @Query("SELECT * FROM Schedule WHERE stopId = :stopId")
    List<MySQLiteData.Schedule> getScheduleForStop(long stopId);
}


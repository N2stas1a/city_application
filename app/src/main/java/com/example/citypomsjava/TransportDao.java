package com.example.citypomsjava;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TransportDao {
    @Insert
    void insertTransport(MySQLiteData.Transport transport);
    @Query("SELECT * FROM Transport")
    List<MySQLiteData.Transport> getAllTransport();
}

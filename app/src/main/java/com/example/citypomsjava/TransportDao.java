package com.example.citypomsjava;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

public interface TransportDao {

    void insertTransport(MySQLiteData.Transport transport);
    @Query("SELECT * FROM Transport")
    List<MySQLiteData.Transport> getAllTransport();
}

package com.example.citypomsjava;

import com.example.citypomsjava.MySQLiteData;
import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {MySQLiteData.Transport.class, MySQLiteData.Stop.class, MySQLiteData.Schedule.class}, version = 1)
public abstract class TransportDB extends RoomDatabase {

    public abstract TransportDao transportDao();
    public abstract StopDao stopDao();
    public abstract ScheduleDao scheduleDao();
    private static volatile TransportDB INSTANCE;

    private static void insertTestData(TransportDB db) {
        TransportDao transportDao = db.transportDao();
        MySQLiteData.Transport transport1 = new MySQLiteData.Transport("Tram 1");
        MySQLiteData.Transport transport2 = new MySQLiteData.Transport("Bus 2");
        MySQLiteData.Transport transport3 = new MySQLiteData.Transport("subway");

        transportDao.insertTransport(transport1);
        transportDao.insertTransport(transport2);
    }

    public static TransportDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TransportDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TransportDB.class, "cityTransportDB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}



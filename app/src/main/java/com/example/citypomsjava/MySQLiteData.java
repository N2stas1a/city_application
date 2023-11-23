package com.example.citypomsjava;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

public class MySQLiteData {
    @Entity
    public static class Transport {
        @PrimaryKey
        public int transportNumber;
    }

    @Entity(foreignKeys = @ForeignKey(entity = Stop.class,
            parentColumns = "transportNumber",
            childColumns = "transportNumber",
            onDelete = ForeignKey.CASCADE))
    public static class Stop {
        @PrimaryKey(autoGenerate = true)
        public long stopId;
        public int transportNumber;
        public String stopName;
    }

    @Entity(foreignKeys = @ForeignKey(entity = Stop.class,
            parentColumns = "stopId",
            childColumns = "stopId",
            onDelete = ForeignKey.CASCADE))
    public static class Schedule {
        @PrimaryKey(autoGenerate = true)
        public long scheduleId;
        public long stopId;
        public String departureTime;
    }
}


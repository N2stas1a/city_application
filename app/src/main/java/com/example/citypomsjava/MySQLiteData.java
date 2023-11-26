package com.example.citypomsjava;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Index;

public class MySQLiteData {
    @Entity
    public static class Transport {
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "transport_number")
        private String transportNumber;

        public Transport(@NonNull String transportNumber){
            this.transportNumber = transportNumber;
        }

        @NonNull
        public String getTransportNumber() {
            return transportNumber;
        }
    }

    @Entity(indices = {@Index(name="index_transportNumber",value="transportNumber",unique = true)},
            foreignKeys = @ForeignKey(entity = Stop.class,
            parentColumns = "transportNumber",
            childColumns = "transportNumber",
            onDelete = ForeignKey.CASCADE))
    public static class Stop {
        @PrimaryKey(autoGenerate = true)
        public long stopId;
        @NonNull
        public String transportNumber;
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


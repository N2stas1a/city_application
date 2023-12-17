package com.example.citypomsjava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME_TRAMS = "TRAMS";
    public static final String _TRAM_ID = "_tram_id";
    public static final String TRAM_NUMBER = "tram_number";

    // Table Name for the second table
    public static final String TABLE_NAME_TRAM_STOPS = "TRAM_STOPS";
    public static final String _STOP_ID = "_stop_id";
    public static final String STOP_NAME = "stop_name";

    public static final String TABLE_NAME_TRAM_ROUTES = "TRAM_ROUTES";
    public static final String _ENTRY_ID = "entry_id";
    public static final String TRAM_ID = "tram_id";
    public static final String STOP_ID = "stop_id";
    public static final String ROUTE_STOP_NAME = "route_stop_name";

    public static final String TABLE_NAME_TRAM_TIMETABLE = "TRAM_TIMETABLE";
    public static final String _ARRIVAL_ID = "_arrival_id";
    public static final String TIME_TRAM_ID = "time_tram_id";
    public static final String TIME_TRAM_STOP_ID = "time_tram_stop_id";
    public static final String ARRIVAL_TIME = "arrival_time";

    // Table Name for buses
    public static final String TABLE_NAME_BUS = "BUS";
    public static final String _BUS_ID = "_bus_id";
    public static final String BUS_NUMBER = "bus_number";

    // Table Name for the stops
    public static final String TABLE_NAME_BUS_STOPS = "BUS_STOPS";
    public static final String _STOP_ID_BUS = "_stop_id_bus";
    public static final String STOP_NAME_BUS = "stop_name_bus";

    // Table Name for the routes
    public static final String TABLE_NAME_BUS_ROUTES = "BUS_ROUTES";
    public static final String _ENTRY_ID_BUS = "entry_id_bus";
    public static final String TRAM_ID_BUS = "tram_id_bus";
    public static final String STOP_ID_BUS = "stop_id_bus";
    public static final String ROUTE_STOP_NAME_BUS = "route_stop_name_bus";

    // Table Name for the timetable
    public static final String TABLE_NAME_BUS_TIMETABLE = "BUS_TIMETABLE";
    public static final String _ARRIVAL_ID_BUS = "_arrival_id_bus";
    public static final String TIME_BUS_ID = "time_bus_id";
    public static final String TIME_BUS_STOP_ID = "time_bus_stop_id";
    public static final String ARRIVAL_TIME_BUS = "arrival_time_bus";

    // Database Information
    static final String DB_NAME = "CITY_APP.DB";
    // database version
    static final int DB_VERSION = 8;

    // Creating table query
    private static final String CREATE_TABLE_TRAM_STOPS = "create table " + TABLE_NAME_TRAM_STOPS + "("
            + _STOP_ID + " INTEGER PRIMARY KEY, "
            + STOP_NAME + " TEXT NOT NULL);";
    private static final String CREATE_TABLE_TRAMS = "create table " + TABLE_NAME_TRAMS + "(" + _TRAM_ID
            + " INTEGER PRIMARY KEY, " + TRAM_NUMBER + " INTEGER NOT NULL);";

    private static final String CREATE_TABLE_TRAM_ROUTES = "CREATE TABLE " + TABLE_NAME_TRAM_ROUTES + "("
            + _ENTRY_ID + " INTEGER PRIMARY KEY, "
            + TRAM_ID + " INTEGER REFERENCES " + TABLE_NAME_TRAMS + "(" + _TRAM_ID + "), "
            + STOP_ID + " INTEGER REFERENCES " + TABLE_NAME_TRAM_STOPS + "(" + _STOP_ID + "), "
            + ROUTE_STOP_NAME + " TEXT, "
            + "FOREIGN KEY (" + TRAM_ID + ") REFERENCES " + TABLE_NAME_TRAMS + "(" + _TRAM_ID + "), "
            + "FOREIGN KEY (" + STOP_ID + ") REFERENCES " + TABLE_NAME_TRAM_STOPS + "(" + _STOP_ID + "), "
            + "FOREIGN KEY (" + ROUTE_STOP_NAME + ") REFERENCES " + TABLE_NAME_TRAM_STOPS + "(" + STOP_NAME + "));";

    private static final String CREATE_TABLE_TRAM_TIMETABLE = "CREATE TABLE " + TABLE_NAME_TRAM_TIMETABLE + "("
            + _ARRIVAL_ID + " INTEGER PRIMARY KEY, "
            + TIME_TRAM_ID + " INTEGER REFERENCES " + TABLE_NAME_TRAMS + "(" + _TRAM_ID + "), "
            + TIME_TRAM_STOP_ID + " INTEGER REFERENCES " + TABLE_NAME_TRAM_STOPS + "(" + _STOP_ID + "), "
            + ARRIVAL_TIME + " TEXT, "
            + "FOREIGN KEY (" + TIME_TRAM_ID + ") REFERENCES " + TABLE_NAME_TRAMS + "(" + _TRAM_ID + "), "
            + "FOREIGN KEY (" + TIME_TRAM_STOP_ID + ") REFERENCES " + TABLE_NAME_TRAM_STOPS + "(" + _STOP_ID + "));";

    // Create the tables for the buses
    private static final String CREATE_TABLE_BUS_STOPS = "create table " + TABLE_NAME_BUS_STOPS + "("
            + _STOP_ID_BUS + " INTEGER PRIMARY KEY, "
            + STOP_NAME_BUS + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_BUS = "create table " + TABLE_NAME_BUS + "(" + _BUS_ID
            + " INTEGER PRIMARY KEY, " + BUS_NUMBER + " INTEGER NOT NULL);";

    private static final String CREATE_TABLE_BUS_ROUTES = "CREATE TABLE " + TABLE_NAME_BUS_ROUTES + "("
            + _ENTRY_ID_BUS + " INTEGER PRIMARY KEY, "
            + TRAM_ID_BUS + " INTEGER REFERENCES " + TABLE_NAME_BUS + "(" + _BUS_ID + "), "
            + STOP_ID_BUS + " INTEGER REFERENCES " + TABLE_NAME_BUS_STOPS + "(" + _STOP_ID_BUS + "), "
            + ROUTE_STOP_NAME_BUS + " TEXT, "
            + "FOREIGN KEY (" + TRAM_ID_BUS + ") REFERENCES " + TABLE_NAME_BUS + "(" + _BUS_ID + "), "
            + "FOREIGN KEY (" + STOP_ID_BUS + ") REFERENCES " + TABLE_NAME_BUS_STOPS + "(" + _STOP_ID_BUS + "), "
            + "FOREIGN KEY (" + ROUTE_STOP_NAME_BUS + ") REFERENCES " + TABLE_NAME_BUS_STOPS + "(" + STOP_NAME_BUS + "));";

    private static final String CREATE_TABLE_BUS_TIMETABLE = "CREATE TABLE " + TABLE_NAME_BUS_TIMETABLE + "("
            + _ARRIVAL_ID_BUS + " INTEGER PRIMARY KEY, "
            + TIME_BUS_ID + " INTEGER REFERENCES " + TABLE_NAME_BUS + "(" + _BUS_ID + "), "
            + TIME_BUS_STOP_ID + " INTEGER REFERENCES " + TABLE_NAME_BUS_STOPS + "(" + _STOP_ID_BUS + "), "
            + ARRIVAL_TIME_BUS + " TEXT, "
            + "FOREIGN KEY (" + TIME_BUS_ID + ") REFERENCES " + TABLE_NAME_BUS + "(" + _BUS_ID + "), "
            + "FOREIGN KEY (" + TIME_BUS_STOP_ID + ") REFERENCES " + TABLE_NAME_BUS_STOPS + "(" + _STOP_ID_BUS + "));";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TRAMS);
        db.execSQL(CREATE_TABLE_TRAM_STOPS);
        db.execSQL(CREATE_TABLE_TRAM_ROUTES);
        db.execSQL(CREATE_TABLE_TRAM_TIMETABLE);

        // Create the tables for the buses
        db.execSQL(CREATE_TABLE_BUS);
        db.execSQL(CREATE_TABLE_BUS_STOPS);
        db.execSQL(CREATE_TABLE_BUS_ROUTES);
        db.execSQL(CREATE_TABLE_BUS_TIMETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRAM_STOPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRAM_ROUTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRAM_TIMETABLE);

        // Drop the tables for the buses
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BUS_STOPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BUS_ROUTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BUS_TIMETABLE);
        onCreate(db);
    }
}
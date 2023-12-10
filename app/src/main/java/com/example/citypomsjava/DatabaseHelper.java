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
    public static final String TABLE_NAME_STOPS = "STOPS";
    public static final String _STOP_ID = "_stop_id";
    public static final String STOP_NAME = "stop_name";

    public static final String TABLE_NAME_ROUTES = "ROUTES";
    public static final String _ENTRY_ID = "entry_id";
    public static final String TRAM_ID = "tram_id";
    public static final String STOP_ID = "stop_id";
    public static final String ROUTE_STOP_NAME = "route_stop_name";
    // Database Information
    static final String DB_NAME = "CITY_APP.DB";

    // database version
    static final int DB_VERSION = 3;

    // Creating table query
    private static final String CREATE_TABLE_STOPS = "create table " + TABLE_NAME_STOPS + "("
            + _STOP_ID + " INTEGER PRIMARY KEY, "
            + STOP_NAME + " TEXT NOT NULL);";
    private static final String CREATE_TABLE_TRAMS = "create table " + TABLE_NAME_TRAMS + "(" + _TRAM_ID
            + " INTEGER PRIMARY KEY, " + TRAM_NUMBER + " INTEGER NOT NULL);";

    private static final String CREATE_TABLE_ROUTES = "CREATE TABLE " + TABLE_NAME_ROUTES + "("
            + _ENTRY_ID + " INTEGER PRIMARY KEY, "
            + TRAM_ID + " INTEGER REFERENCES " + TABLE_NAME_TRAMS + "(" + _TRAM_ID + "), "
            + STOP_ID + " INTEGER REFERENCES " + TABLE_NAME_STOPS + "(" + _STOP_ID + "), "
            + ROUTE_STOP_NAME + " TEXT, "
            + "FOREIGN KEY (" + TRAM_ID + ") REFERENCES " + TABLE_NAME_TRAMS + "(" + _TRAM_ID + "), "
            + "FOREIGN KEY (" + STOP_ID + ") REFERENCES " + TABLE_NAME_STOPS + "(" + _STOP_ID + "), "
            + "FOREIGN KEY (" + ROUTE_STOP_NAME + ") REFERENCES " + TABLE_NAME_STOPS + "(" + STOP_NAME + "));";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TRAMS);
        db.execSQL(CREATE_TABLE_STOPS);
        db.execSQL(CREATE_TABLE_ROUTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STOPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ROUTES);
        onCreate(db);
    }
}
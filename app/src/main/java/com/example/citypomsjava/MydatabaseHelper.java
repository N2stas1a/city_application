package com.example.citypomsjava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MydatabaseHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABSE_NAME = "CityTransport.db";
    private static final int DATABSE_VERSION = 1;
    public static final String TABLE_NAME = "routes";
    public MydatabaseHelper(@Nullable Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

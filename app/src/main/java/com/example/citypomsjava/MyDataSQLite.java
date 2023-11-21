package com.example.citypomsjava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

 class MyDataSQLite extends SQLiteOpenHelper {

     private Context context;
     private static final String DATABASE_NAME="citytransport.db";
     private static final int DATABASE_VERSION=1;
     private static final String TABLE_NAME="my_citytransport";
     private static final String COLUMN_ID= "_id";
     private static final String COLUMN_TITLE = "tramNumber";
     private static final String COLUMN_STOPS = "tramStops";
     private static final String COLUMN_TIME = "tramTime";

    public MyDataSQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query =
                "CREATE TABLE" + TABLE_NAME +
                        "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + "INTEGER, " +
                COLUMN_STOPS + "TEXT, " +
                COLUMN_TIME + "INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    onCreate(db);
    }
}

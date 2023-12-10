package com.example.citypomsjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert_trams(int _tram_id, int tram_number) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._TRAM_ID, _tram_id);
        contentValue.put(DatabaseHelper.TRAM_NUMBER, tram_number);
        database.insert(DatabaseHelper.TABLE_NAME_TRAMS, null, contentValue);
    }
    public void insert_stops(int _stop_id, String stop_name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._STOP_ID, _stop_id);
        contentValue.put(DatabaseHelper.STOP_NAME, stop_name);
        database.insert(DatabaseHelper.TABLE_NAME_TRAM_STOPS, null, contentValue);
    }
    public void insert_routes(int _entry_id, int _tram_id, int _stop_id, String route_stop_name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ENTRY_ID, _entry_id);
        contentValue.put(DatabaseHelper.TRAM_ID, _tram_id);
        contentValue.put(DatabaseHelper.STOP_ID, _stop_id);
        contentValue.put(DatabaseHelper.ROUTE_STOP_NAME, route_stop_name);
        database.insert(DatabaseHelper.TABLE_NAME_TRAM_ROUTES, null, contentValue);
    }

    public void insert_timetable(int _arrival_id, int time_tram_id, int time_stop_id, String arrival_time) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ARRIVAL_ID, _arrival_id);
        contentValue.put(DatabaseHelper.TIME_TRAM_ID, time_tram_id);
        contentValue.put(DatabaseHelper.TIME_TRAM_STOP_ID, time_stop_id);
        contentValue.put(DatabaseHelper.ARRIVAL_TIME, arrival_time);
        database.insert(DatabaseHelper.TABLE_NAME_TRAM_TIMETABLE, null, contentValue);
    }

    public void populate(){
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_TRAMS);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_TRAM_STOPS);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_TRAM_ROUTES);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_TRAM_TIMETABLE);
        int[] tramsArray = {24, 50, 11};
        int i = 0;
        for(i = 1; i<4; i++) { insert_trams(i, tramsArray[i-1]); }
        String[] stopsArray = {"Kurdwanow P+R","Witosa","Nowosadecka","Piaski Nove","Dauna","Biezanovska","Kabel","Dvorcowa","Plaszow","Lipska","Gromadzka" };
        for(i = 1; i<12; i++) { insert_stops(i, stopsArray[i-1]); }

        insert_routes(1, 1, 1, "Kurdwanow P+R");
        insert_routes(2, 1, 2, "Witosa");
        insert_routes(3, 1, 3, "Nowosadecka");
        insert_routes(4, 1, 4, "Piaski Nove");
        insert_routes(5, 1, 5, "Dauna");
        insert_routes(6, 1, 6, "Biezanovska");
        insert_routes(7, 1, 7, "Kabel");
        insert_routes(8, 1, 8, "Dvorcowa");
        insert_routes(9, 2, 1, "Kurdwanow P+R");
        insert_routes(10, 2, 2, "Witosa");
        insert_routes(11, 2, 3, "Nowosadecka");
        insert_routes(12, 2, 4, "Piaski Nove");
        insert_routes(13, 2, 5, "Dauna");
        insert_routes(14, 2, 6, "Biezanovska");
        insert_routes(15, 2, 7, "Kabel");
        insert_routes(16, 2, 9, "Plaszow");
        insert_routes(17, 2, 10, "Lipska");
        insert_routes(18, 2, 11, "Gromadzka");
        insert_routes(19, 3, 7, "Kabel");
        insert_routes(20,3, 3, "Nowosadecka");

        insert_timetable(1, 1,1, "4:37");
    }

    public Cursor fetch_trams() {
        String[] columns = new String[] { DatabaseHelper._TRAM_ID + " AS _id", DatabaseHelper.TRAM_NUMBER };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_TRAMS, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch_stops() {
        String[] columns = new String[] { DatabaseHelper._STOP_ID + " AS _id", DatabaseHelper.STOP_NAME };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_TRAM_STOPS, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch_routes(int selected_tram_id) {
        String[] columns = new String[] { DatabaseHelper._ENTRY_ID + " AS _id", DatabaseHelper.TRAM_ID, DatabaseHelper.STOP_ID, DatabaseHelper.ROUTE_STOP_NAME };

        String selection = DatabaseHelper.TRAM_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(selected_tram_id)};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_TRAM_ROUTES, columns, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch_timetable() {
        String[] columns = new String[] { DatabaseHelper._ARRIVAL_ID + " AS _id", DatabaseHelper.TIME_TRAM_ID, DatabaseHelper.TIME_TRAM_STOP_ID, DatabaseHelper.ARRIVAL_TIME };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_TRAM_TIMETABLE, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(int _tram_id, int tram_number) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper._TRAM_ID, _tram_id);
        contentValues.put(DatabaseHelper.TRAM_NUMBER, tram_number);
        int i = database.update(DatabaseHelper.TABLE_NAME_TRAMS, contentValues, DatabaseHelper._TRAM_ID + " = " + _tram_id, null);
        return i;
    }

    public void delete(long _tram_id) {
        database.delete(DatabaseHelper.TABLE_NAME_TRAMS, DatabaseHelper._TRAM_ID + "=" + _tram_id, null);
    }

}
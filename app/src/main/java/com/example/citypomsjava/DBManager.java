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
        database.insert(DatabaseHelper.TABLE_NAME_STOPS, null, contentValue);
    }
    public void insert_routes(int _entry_id, int _tram_id, int _stop_id, String route_stop_name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ENTRY_ID, _entry_id);
        contentValue.put(DatabaseHelper.TRAM_ID, _tram_id);
        contentValue.put(DatabaseHelper.STOP_ID, _stop_id);
        contentValue.put(DatabaseHelper.ROUTE_STOP_NAME, route_stop_name);
        database.insert(DatabaseHelper.TABLE_NAME_ROUTES, null, contentValue);
    }

    public void populate(){
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_TRAMS);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_STOPS);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_ROUTES);
        insert_trams(1, 24);
        insert_trams(2, 50);
        insert_trams(3, 11);
        insert_stops(1, "Kurdwanow P+R");
        insert_stops(2, "Witosa");
        insert_stops(3, "Nowosadecka");
        insert_stops(4, "Piaski Nove");
        insert_stops(5, "Dauna");
        insert_stops(6, "Biezanovska");
        insert_stops(7, "Kabel");
        insert_stops(8, "Dvorcowa");
        insert_stops(9, "Plaszow");
        insert_stops(10, "Lipska");
        insert_stops(11, "Gromadzka");
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
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_STOPS, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch_routes(int selected_tram_id) {
        String[] columns = new String[] { DatabaseHelper._ENTRY_ID + " AS _id", DatabaseHelper.TRAM_ID, DatabaseHelper.STOP_ID, DatabaseHelper.ROUTE_STOP_NAME };

        String selection = DatabaseHelper.TRAM_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(selected_tram_id)};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_ROUTES, columns, selection, selectionArgs, null, null, null);

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
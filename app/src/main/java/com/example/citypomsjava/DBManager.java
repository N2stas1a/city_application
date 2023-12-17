package com.example.citypomsjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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

    public void insert_trams(int _tram_id, int tram_number) { //trams
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._TRAM_ID, _tram_id);
        contentValue.put(DatabaseHelper.TRAM_NUMBER, tram_number);
        database.insert(DatabaseHelper.TABLE_NAME_TRAMS, null, contentValue);
    }
        public void insert_buses(int _bus_id, int bus_number) { //buses
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._BUS_ID, _bus_id);
        contentValue.put(DatabaseHelper.BUS_NUMBER, bus_number);
        database.insert(DatabaseHelper.TABLE_NAME_BUS, null, contentValue);
    }
    public void insert_stops(int _stop_id, String stop_name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._STOP_ID, _stop_id);
        contentValue.put(DatabaseHelper.STOP_NAME, stop_name);
        database.insert(DatabaseHelper.TABLE_NAME_TRAM_STOPS, null, contentValue);
    }
    public void insert_stops_bus(int _stop_id_bus, String stop_name_bus) { //buses
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._STOP_ID_BUS, _stop_id_bus);
        contentValue.put(DatabaseHelper.STOP_NAME_BUS, stop_name_bus);
        database.insert(DatabaseHelper.TABLE_NAME_BUS_STOPS, null, contentValue);
    }
    public void insert_routes(int _entry_id, int _tram_id, int _stop_id, String route_stop_name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ENTRY_ID, _entry_id);
        contentValue.put(DatabaseHelper.TRAM_ID, _tram_id);
        contentValue.put(DatabaseHelper.STOP_ID, _stop_id);
        contentValue.put(DatabaseHelper.ROUTE_STOP_NAME, route_stop_name);
        database.insert(DatabaseHelper.TABLE_NAME_TRAM_ROUTES, null, contentValue);
    }
    public void insert_routes_bus(int _entry_id_bus, int _bus_id, int _stop_id_bus, String route_stop_name_bus) { //buses
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ENTRY_ID_BUS, _entry_id_bus);
        contentValue.put(DatabaseHelper.TRAM_ID_BUS, _bus_id);
        contentValue.put(DatabaseHelper.STOP_ID_BUS, _stop_id_bus);
        contentValue.put(DatabaseHelper.ROUTE_STOP_NAME_BUS, route_stop_name_bus);
        database.insert(DatabaseHelper.TABLE_NAME_BUS_ROUTES, null, contentValue);
    }
    public void insert_timetable(int _arrival_id, int time_tram_id, int time_stop_id, String arrival_time) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ARRIVAL_ID, _arrival_id);
        contentValue.put(DatabaseHelper.TIME_TRAM_ID, time_tram_id);
        contentValue.put(DatabaseHelper.TIME_TRAM_STOP_ID, time_stop_id);
        contentValue.put(DatabaseHelper.ARRIVAL_TIME, arrival_time);
        database.insert(DatabaseHelper.TABLE_NAME_TRAM_TIMETABLE, null, contentValue);
    }

    public void insert_timetable_bus(int _arrival_id_bus, int time_bus_id, int time_stop_id_bus, String arrival_time_bus) { //buses
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper._ARRIVAL_ID_BUS, _arrival_id_bus);
        contentValue.put(DatabaseHelper.TIME_BUS_ID, time_bus_id);
        contentValue.put(DatabaseHelper.TIME_BUS_STOP_ID, time_stop_id_bus);
        contentValue.put(DatabaseHelper.ARRIVAL_TIME_BUS, arrival_time_bus);
        database.insert(DatabaseHelper.TABLE_NAME_BUS_TIMETABLE, null, contentValue);
    }

    public void populate(){
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_TRAMS);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_TRAM_STOPS);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_TRAM_ROUTES);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_TRAM_TIMETABLE);

        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_BUS);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_BUS_STOPS);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_BUS_ROUTES);
        database.execSQL("DELETE FROM " + DatabaseHelper.TABLE_NAME_BUS_TIMETABLE);

        int i = 0;
        int[] tramsArray = {24, 50, 11};
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

        int[] busesArray = {111, 502, 112};
        for(i = 1; i<4; i++) { insert_buses(i, busesArray[i-1]); }
        String[] Busstops = {"Vorzal","Dworcow","Nowosadecka","Piaski","Daun","Biezanov","Kabel","Dvorcowa","Plaszow","Lipska","Gromadzka" };
        for(i = 1; i<12; i++) { insert_stops_bus(i, Busstops[i-1]); }
        insert_routes_bus(22, 3, 1, "Vorzal");
        insert_routes_bus(23, 3, 2, "Dworcow");
        insert_routes_bus(24, 3, 3, "Nowosadecka");
        insert_routes_bus(25, 3, 4, "Piaski");
        insert_routes_bus(26, 3, 5, "Daun");
        insert_routes_bus(27, 3, 6, "Biezanov");

        insert_timetable(1, 1,1, "4:37");
        insert_timetable(2, 1,1, "4:39");
        insert_timetable(3, 1,1, "4:40");
        insert_timetable(4, 1,1, "4:43");
        insert_timetable(5, 1,1, "4:46");
        insert_timetable(6, 1,1, "4:50");
        insert_timetable(7, 1,1, "4:55");
        insert_timetable(8, 2,1, "4:59");
        insert_timetable(9, 2,1, "4:58");
        insert_timetable(10, 2,1, "4:57");

    }

    public Cursor fetch_trams() {
        String[] columns = new String[] { DatabaseHelper._TRAM_ID + " AS _id", DatabaseHelper.TRAM_NUMBER };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_TRAMS, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch_buses() { //buses
        String[] columns = new String[] { DatabaseHelper._BUS_ID + " AS _id", DatabaseHelper.BUS_NUMBER };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_BUS, columns, null, null, null, null, null);
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

    public Cursor fetch_stops_bus() { //buses
        String[] columns = new String[] { DatabaseHelper._STOP_ID_BUS + " AS _id", DatabaseHelper.STOP_NAME_BUS };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_BUS_STOPS, columns, null, null, null, null, null);
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

    public Cursor fetch_routes_bus(int selected_bus_id) { //buses
        String[] columns = new String[] { DatabaseHelper._ENTRY_ID_BUS + " AS _id", DatabaseHelper.TRAM_ID_BUS, DatabaseHelper.STOP_ID_BUS, DatabaseHelper.ROUTE_STOP_NAME_BUS };

        String selection = DatabaseHelper.TRAM_ID_BUS + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(selected_bus_id)};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_BUS_ROUTES, columns, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor fetch_timetable(int selected_tram_id, int selected_stop_id) {
        String[] columns = new String[] { DatabaseHelper._ARRIVAL_ID + " AS _id", DatabaseHelper.TIME_TRAM_ID, DatabaseHelper.TIME_TRAM_STOP_ID, DatabaseHelper.ARRIVAL_TIME };

        String selection = DatabaseHelper.TIME_TRAM_ID + " = ? AND " + DatabaseHelper.TIME_TRAM_STOP_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(selected_tram_id), String.valueOf(selected_stop_id)};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_TRAM_TIMETABLE, columns,selection, selectionArgs, null, null, null);

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

    public int update_bus(int _bus_id, int bus_number) { //buses
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper._BUS_ID, _bus_id);
        contentValues.put(DatabaseHelper.BUS_NUMBER, bus_number);
        int i = database.update(DatabaseHelper.TABLE_NAME_BUS, contentValues, DatabaseHelper._BUS_ID + " = " + _bus_id, null);
        return i;
    }
    public void delete(long _tram_id) {
        database.delete(DatabaseHelper.TABLE_NAME_TRAMS, DatabaseHelper._TRAM_ID + "=" + _tram_id, null);
    }

    public void delete_bus(long _bus_id) { //buses
        database.delete(DatabaseHelper.TABLE_NAME_BUS, DatabaseHelper._BUS_ID + "=" + _bus_id, null);
    }

}
package com.example.citypomsjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.example.citypomsjava.databinding.FragmentAllRoutesBinding;

public class Timetable extends AppCompatActivity {
    private DBManager dbManager;
    //  private ListView listView;
    private SimpleCursorAdapter adapter;
    private FragmentAllRoutesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        int stop_id = getIntent().getIntExtra("stop_id", -1);
        int selected_tram_id = getIntent().getIntExtra("tram_id", -1);
        String title = getIntent().getStringExtra("title");

        dbManager = new DBManager(this);
        dbManager.open();

        Cursor cursor = dbManager.fetch_timetable();

        final String[] from = new String[] {"_id",  DatabaseHelper.ARRIVAL_TIME};
        final int[] to = new int[] { R.id.arrival_id, R.id.arrival_time};

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_item_timetable,
                cursor,
                from,
                to,
                0
        );


        ListView listView = findViewById(R.id.timetable_list);
        listView.setEmptyView(findViewById(R.id.empty_timetable));

        listView.setAdapter(adapter);

        adapter.changeCursor(cursor);
    }
}
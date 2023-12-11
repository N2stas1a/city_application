package com.example.citypomsjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

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

        TextView tramIDTextView = findViewById(R.id.notEmpty_timetable);
        // Set a new text value programmatically
        String newIDNumber = String.valueOf(selected_tram_id); // Replace this with the desired text
        tramIDTextView.setText(newIDNumber);

        TextView stopIDTextView = findViewById(R.id.timetable_id);
        String newStopIDNumber = String.valueOf(stop_id); // Replace this with the desired text
        stopIDTextView.setText(newStopIDNumber);

        dbManager = new DBManager(this);
        dbManager.open();

        Cursor cursor = dbManager.fetch_timetable(selected_tram_id, stop_id);

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
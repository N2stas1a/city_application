package com.example.citypomsjava;

import com.example.citypomsjava.DBManager;
import com.example.citypomsjava.DatabaseHelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.citypomsjava.databinding.FragmentAllRoutesBinding;

public class StopsActivity extends AppCompatActivity {
    private DBManager dbManager;
    //  private ListView listView;
    private SimpleCursorAdapter adapter;
    private FragmentAllRoutesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops);

        int selected_tram_id = getIntent().getIntExtra("id", -1);
        String title = getIntent().getStringExtra("title");

        // Find the TextView by its ID
        TextView tramNumberTextView = findViewById(R.id.tram_id_transfer);
        // Set a new text value programmatically
        String newTramNumber = title; // Replace this with the desired text
        tramNumberTextView.setText(newTramNumber);

        TextView tramIDTextView = findViewById(R.id.tram_id_id);
        // Set a new text value programmatically
        String newIDNumber = String.valueOf(selected_tram_id); // Replace this with the desired text
        tramIDTextView.setText(newIDNumber);

        dbManager = new DBManager(this);
        dbManager.open();

        Cursor cursor = dbManager.fetch_routes(selected_tram_id);

        final String[] from = new String[] {"_id", DatabaseHelper.STOP_ID, DatabaseHelper.ROUTE_STOP_NAME};
        final int[] to = new int[] { R.id.entry_id, R.id.stop_id,R.id.stop_title};

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_item_stops,
                cursor,
                from,
                to,
                0
        );

        ListView listView = findViewById(R.id.tram_stops_list);
        listView.setEmptyView(findViewById(R.id.empty_stops));

        listView.setAdapter(adapter);

        adapter.changeCursor(cursor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.stop_id);
                TextView titleTextView = (TextView) view.findViewById(R.id.stop_title);

                String stop_id = idTextView.getText().toString();
                String stop_title = titleTextView.getText().toString();
                int stopId = Integer.parseInt(stop_id);
                Intent timetable_intent = new Intent(StopsActivity.this, Timetable.class);

                timetable_intent.putExtra("stop_id", stopId);
                timetable_intent.putExtra("tram_id", selected_tram_id);
                timetable_intent.putExtra("title", stop_title);

                startActivity(timetable_intent);
            }
        });

    }

}
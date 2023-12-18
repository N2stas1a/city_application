package com.example.citypomsjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.citypomsjava.databinding.FragmentAllRoutesBinding;

public class StopsActivityBus extends AppCompatActivity {
    private DBManager dbManager;
    //  private ListView listView;
    private SimpleCursorAdapter adapter;
    private FragmentAllRoutesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops_bus);

        int selected_bus_id = getIntent().getIntExtra("id", -1);
        String title = getIntent().getStringExtra("title");

        // Find the TextView by its ID
        TextView busNumberTextView = findViewById(R.id.bus_id_transfer);
        // Set a new text value programmatically
        String newBusNumber = title; // Replace this with the desired text
        busNumberTextView.setText(newBusNumber);

        TextView busIDTextView = findViewById(R.id.bus_id_id);
        // Set a new text value programmatically
        String newIDNumber = String.valueOf(selected_bus_id); // Replace this with the desired text
        busIDTextView.setText(newIDNumber);

        dbManager = new DBManager(this);
        dbManager.open();

        Cursor cursor = dbManager.fetch_routes_bus(selected_bus_id);

        final String[] from = new String[] {"_id", DatabaseHelper.STOP_ID_BUS, DatabaseHelper.ROUTE_STOP_NAME_BUS};
        final int[] to = new int[] { R.id.entry_id_bus, R.id.stop_id_bus,R.id.stop_title_bus};

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_item_stopsbus,
                cursor,
                from,
                to,
                0
        );

        ListView listView = findViewById(R.id.bus_stops_list);
        listView.setEmptyView(findViewById(R.id.empty_stops_bus));

        listView.setAdapter(adapter);

        adapter.changeCursor(cursor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.stop_id_bus);
                TextView titleTextView = (TextView) view.findViewById(R.id.stop_title_bus);

                String stop_id = idTextView.getText().toString();
                String stop_title = titleTextView.getText().toString();
                int stopId = Integer.parseInt(stop_id);
                Intent timetable_intent = new Intent(StopsActivityBus.this, Timetable.class);

                timetable_intent.putExtra("stop_id", stopId);
                timetable_intent.putExtra("bus_id", selected_bus_id);
                timetable_intent.putExtra("title", stop_title);

                startActivity(timetable_intent);
            }
        });

    }

}
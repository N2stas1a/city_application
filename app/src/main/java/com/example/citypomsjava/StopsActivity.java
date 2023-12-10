package com.example.citypomsjava;

import com.example.citypomsjava.DBManager;
import com.example.citypomsjava.DatabaseHelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.os.Bundle;
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
        int id = getIntent().getIntExtra("id", 1);
        String title = getIntent().getStringExtra("title");

        // Find the TextView by its ID
        TextView tramNumberTextView = findViewById(R.id.tram_id_transfer);
        // Set a new text value programmatically
        String newTramNumber = title; // Replace this with the desired text
        tramNumberTextView.setText(newTramNumber);

        dbManager = new DBManager(this);
        dbManager.open();

        dbManager.populate();

        Cursor cursor = dbManager.fetch_routes();

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
    }

}
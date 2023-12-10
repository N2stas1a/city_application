package com.example.citypomsjava;

import com.example.citypomsjava.DBManager;
import com.example.citypomsjava.DatabaseHelper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

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
        long itemId = getIntent().getLongExtra("title", -1);

        dbManager = new DBManager(this);
        dbManager.open();

        dbManager.populate();

        Cursor cursor = dbManager.fetch_routes();

        final String[] from = new String[] {"_id", DatabaseHelper.ROUTE_STOP_NAME};
        final int[] to = new int[] { R.id.id, R.id.title};

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_item,
                cursor,
                from,
                to,
                0
        );

/*
        Cursor cursor = dbManager.fetch_trams();

        final String[] from = new String[] {"_id", DatabaseHelper.TRAM_NUMBER};
        final int[] to = new int[] { R.id.id, R.id.title};

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.list_item,
                cursor,
                from,
                to,
                0
        );
*/
        ListView listView = findViewById(R.id.tram_stops_list);
        listView.setEmptyView(findViewById(R.id.empty_stops));

        listView.setAdapter(adapter);

        adapter.changeCursor(cursor);
    }

}
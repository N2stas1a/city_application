package com.example.citypomsjava;

import androidx.annotation.Nullable;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BusActivity extends AppCompatActivity {
    private DBManager dbManager;
    //  private ListView listView;
    private SimpleCursorAdapter adapter;
    private FragmentAllRoutesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        dbManager = new DBManager(BusActivity.this);
        dbManager.open();

        Cursor cursor = dbManager.fetch_buses();
        
        final String[] from = new String[] {"_id", DatabaseHelper.BUS_NUMBER};
        final int[] to = new int[] { R.id.id_buses, R.id.title_buses};

        adapter = new SimpleCursorAdapter(
                BusActivity.this,
                R.layout.list_item_buses,
                cursor,
                from,
                to,
                0
        );

        ListView listView = this.findViewById(R.id.listViewBus);
        listView.setEmptyView(this.findViewById(R.id.emptyBuses));

        listView.setAdapter(adapter);

        adapter.changeCursor(cursor);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTextView = (TextView) view.findViewById(R.id.id_buses);
                TextView titleTextView = (TextView) view.findViewById(R.id.title_buses);

                String string_id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                int tramId = Integer.parseInt(string_id);
                Intent modify_intent = new Intent(BusActivity.this, UpdateActivity.class);

                modify_intent.putExtra("id", tramId);
                modify_intent.putExtra("title", title);

                startActivityForResult(modify_intent, 1);
                return true;
            }
        });

        listView.setOnItemClickListener((parent, view, position, viewId) -> {
            TextView idTextView = (TextView) view.findViewById(R.id.id_buses);
            TextView titleTextView = (TextView) view.findViewById(R.id.title_buses);

            String string_id = idTextView.getText().toString();
            String title = titleTextView.getText().toString();
            int tramId = Integer.parseInt(string_id);
            Intent modify_intent = new Intent(BusActivity.this, StopsActivityBus.class);

            modify_intent.putExtra("id", tramId);
            modify_intent.putExtra("title", title);

            startActivity(modify_intent);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

}
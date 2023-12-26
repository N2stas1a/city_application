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
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.BaseColumns;
import android.widget.TextView;
import android.content.Intent;
import android.database.Cursor;

import androidx.annotation.Nullable;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.citypomsjava.R;
import com.example.citypomsjava.databinding.FragmentAllRoutesBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TramsList extends AppCompatActivity {
    private DBManager dbManager;
    //  private ListView listView;
    private SimpleCursorAdapter adapter;
    private FragmentAllRoutesBinding binding;
    private FloatingActionButton floatingAddButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trams_list);

        floatingAddButton = this.findViewById(R.id.floatingAddButton);
        floatingAddButton.setOnClickListener(view -> {
            Intent intent = new Intent(TramsList.this, AddActivity.class);
            intent.putExtra("FishText", "trams");
            startActivityForResult(intent, 1);
        });

        dbManager = new DBManager(TramsList.this);
        dbManager.open();

        //dbManager.clean();
        //dbManager.populate();

        Cursor cursor = dbManager.fetch_trams();

        final String[] from = new String[] {"_id", DatabaseHelper.TRAM_NUMBER};
        final int[] to = new int[] { R.id.id_routes, R.id.title_routes};

        adapter = new SimpleCursorAdapter(
                TramsList.this,
                R.layout.list_item,
                cursor,
                from,
                to,
                0
        );

        ListView listView = this.findViewById(R.id.listviewTrams);
//        listView.setEmptyView(root.findViewById(R.id.emptyTrams));

        listView.setAdapter(adapter);

        adapter.changeCursor(cursor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id_routes);
                TextView titleTextView = (TextView) view.findViewById(R.id.title_routes);

                String string_id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                int tramId = Integer.parseInt(string_id);
                Intent modify_intent = new Intent(TramsList.this, StopsActivity.class);

                modify_intent.putExtra("id", tramId);
                modify_intent.putExtra("title", title);

                startActivity(modify_intent);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

}
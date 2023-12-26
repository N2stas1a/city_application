package com.example.citypomsjava;

import static androidx.core.app.ActivityCompat.recreate;

import com.example.citypomsjava.DBManager;
import com.example.citypomsjava.DatabaseHelper;

import android.app.Activity;
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

public class AllRouteFragment extends Fragment {
    private DBManager dbManager;
    //  private ListView listView;
    private SimpleCursorAdapter adapter;
    private FragmentAllRoutesBinding binding;
    private FloatingActionButton floatingAddButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AllRouteViewModel galleryViewModel =
                new ViewModelProvider(this).get(AllRouteViewModel.class);

        binding = FragmentAllRoutesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        floatingAddButton = root.findViewById(R.id.floatingAddButton);
        floatingAddButton.setOnClickListener(view -> {
            Intent intent = new Intent(requireContext(), AddActivity.class);
            intent.putExtra("FishText", "trams");
            startActivity(intent);
        });

        dbManager = new DBManager(requireContext());
        dbManager.open();

        //dbManager.clean();
        //dbManager.populate();

        Cursor cursor = dbManager.fetch_trams();

        final String[] from = new String[] {"_id", DatabaseHelper.TRAM_NUMBER};
        final int[] to = new int[] { R.id.id_routes, R.id.title_routes};

        adapter = new SimpleCursorAdapter(
                requireContext(),
                R.layout.list_item,
                cursor,
                from,
                to,
                0
        );

        ListView listView = root.findViewById(R.id.listviewTrams);
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
                Intent modify_intent = new Intent(requireContext(), StopsActivity.class);

                modify_intent.putExtra("id", tramId);
                modify_intent.putExtra("title", title);

                startActivity(modify_intent);
            }
        });
        return root;
    }

    public void updateTramsList() {
        Cursor newCursor = dbManager.fetch_trams();
        if (adapter != null) {
            Cursor oldCursor = adapter.swapCursor(newCursor);
            if (oldCursor != null && !oldCursor.isClosed()) {
                oldCursor.close();
            }
            adapter.notifyDataSetChanged(); // Уведомление об изменении данных
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == AddActivity.RESULT_OK) {
            updateTramsList();
        }
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dbManager.close();
        binding = null;
    }
}
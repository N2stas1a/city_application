package com.example.citypomsjava;

import com.example.citypomsjava.DBManager;
import com.example.citypomsjava.DatabaseHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.BaseColumns;
import android.widget.TextView;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.citypomsjava.databinding.FragmentAllSubwayBinding;

public class AllSubwayFragment extends Fragment {
    private DBManager dbManager;
    //  private ListView listView;
    private SimpleCursorAdapter adapter;
    private FragmentAllSubwayBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AllRouteViewModel galleryViewModel =
                new ViewModelProvider(this).get(AllRouteViewModel.class);

        binding = FragmentAllSubwayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        dbManager = new DBManager(requireContext());
        dbManager.open();

        dbManager.populate();
        /*
        Cursor cursor = dbManager.fetch_stops();

        final String[] from = new String[] {"_id", DatabaseHelper.STOP_NAME};
        final int[] to = new int[] { R.id.id, R.id.title};

        adapter = new SimpleCursorAdapter(
                requireContext(),
                R.layout.list_item,
                cursor,
                from,
                to,
                0
        );
        */

        Cursor cursor = dbManager.fetch_trams();

        final String[] from = new String[]{"_id", DatabaseHelper.TRAM_NUMBER};
        final int[] to = new int[]{R.id.id, R.id.title};

        adapter = new SimpleCursorAdapter(
                requireContext(),
                R.layout.list_item,
                cursor,
                from,
                to,
                0
        );

        ListView listView = root.findViewById(R.id.listViewSubway);
        listView.setEmptyView(root.findViewById(R.id.emptySubways));

        listView.setAdapter(adapter);

        adapter.changeCursor(cursor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();

                Intent modify_intent = new Intent(requireContext(), StopsActivity.class);

                startActivity(modify_intent);
                modify_intent.putExtra("title", title);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dbManager.close();
        binding = null;
    }
}
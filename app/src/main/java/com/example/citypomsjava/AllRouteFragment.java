package com.example.citypomsjava;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.citypomsjava.MySQLiteData;
import com.example.citypomsjava.TransportDB;
import com.example.citypomsjava.TransportDao;
import com.example.citypomsjava.StopDao;
import com.example.citypomsjava.ScheduleDao;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class AllRouteFragment extends Fragment {

    private TransportDao transportDao;
    private AllRoutesViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TransportDB db = TransportDB.getDatabase(requireContext());
        transportDao = db.transportDao();
        viewModel = new ViewModelProvider(this).get(AllRoutesViewModel.class);

        // Observing LiveData for transport list
        viewModel.getTransportList().observe(this, transportList -> {
            if (transportList != null && !transportList.isEmpty()) {
                int[] buttonIds = {R.id.transportButton1, R.id.transportButton2, R.id.transportButton3, R.id.transportButton4,
                        R.id.transportButton5, R.id.transportButton6, R.id.transportButton7, R.id.transportButton8,
                        R.id.transportButton9, R.id.transportButton10, R.id.transportButton11, R.id.transportButton12,
                        R.id.transportButton13, R.id.transportButton14, R.id.transportButton15, R.id.transportButton16,
                        R.id.transportButton17, R.id.transportButton18, R.id.transportButton19, R.id.transportButton21,
                        R.id.transportButton20};

                for (int i = 0; i < Math.min(transportList.size(), buttonIds.length); i++) {
                    TextView transportButton = requireView().findViewById(buttonIds[i]);
                    transportButton.setText(String.valueOf(transportList.get(i).getTransportNumber()));
                }
            }
        });
        loadTransportData();
    }

    private void loadTransportData() {
        // You can replace AsyncTask with any other method to load data asynchronously
        new AsyncTask<Void, Void, List<MySQLiteData.Transport>>() {
            @Override
            protected List<MySQLiteData.Transport> doInBackground(Void... voids) {
                return transportDao.getAllTransport();
            }

            @Override
            protected void onPostExecute(List<MySQLiteData.Transport> transportList) {
                super.onPostExecute(transportList);
                viewModel.setTransportList(transportList);
            }
        }.execute();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_routes, container, false);
        // No need to reinitialize TransportDB and transportDao here

        // You can load data here as well if needed, or it can be done in onCreate
        // loadTransportData();

        return view;
    }
}

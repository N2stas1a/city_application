package com.example.citypomsjava;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.citypomsjava.databinding.ActivityMainBinding;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;

    // Remove the binding field
    // ActivityMainBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove the following line as it leads to a null binding
        // requireActivity().setContentView(binding.getRoot());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // Remove the initListView and FragmentTransaction here
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initListView(rootView);

        return rootView;
    }

    private void initListView(View rootView) {
        int[] imageList = {R.drawable.minsk, R.drawable.vitebsk, R.drawable.grodno, R.drawable.mogilev, R.drawable.gomel, R.drawable.brest};
        int[] descListResourceIds = {R.string.MinskDesc, R.string.VitebskDesc, R.string.GrodnoDesc, R.string.GomelDesc, R.string.BrestDesc, R.string.MogilevDesc};
        String[] nameList = {
                getString(R.string.Minsk),
                getString(R.string.Vitebsk),
                getString(R.string.Mogilev),
                getString(R.string.Grodno),
                getString(R.string.Gomel),
                getString(R.string.Brest)
        };

        String[] descList = {
                getString(R.string.MinskDesc),
                getString(R.string.VitebskDesc),
                getString(R.string.MogilevDesc),
                getString(R.string.GrodnoDesc),
                getString(R.string.GomelDesc),
                getString(R.string.BrestDesc)
        };

        ListView listView = rootView.findViewById(R.id.listview);

        for (int i = 0; i < imageList.length; i++) {
            listData = new ListData(nameList[i], descList[i], imageList[i]);
            dataArrayList.add(listData);
        }

        listAdapter = new ListAdapter(requireContext(), dataArrayList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            ListData selectedData = dataArrayList.get(i);

            DetailedFragment detailedFragment = new DetailedFragment();

            Bundle args = new Bundle();
            args.putString("name", selectedData.getName());
            args.putString("desc", selectedData.getDesc());
            args.putInt("image", selectedData.getImage());
            detailedFragment.setArguments(args);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, detailedFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}

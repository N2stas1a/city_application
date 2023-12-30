package com.example.citypomsjava;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    ActivityMainBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        requireActivity().setContentView(binding.getRoot());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        initListView();
        DetailedFragment detailedFragment = new DetailedFragment();
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, detailedFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void initListView() {
        int[] imageList = {R.drawable.minsk, R.drawable.vitebsk, R.drawable.grodno, R.drawable.mogilev, R.drawable.gomel, R.drawable.brest};
        int[] DescList = {R.string.MinskDesc, R.string.VitebskDesc, R.string.GrodnoDesc, R.string.GomelDesc, R.string.BrestDesc, R.string.MogilevDesc};
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


        for (int i = 0; i < imageList.length; i++) {
            listData = new ListData(nameList[i], descList[i], imageList[i]);
            dataArrayList.add(listData);
        }

        listAdapter = new ListAdapter(requireContext(), dataArrayList);
        binding.listview.setAdapter(listAdapter);

        binding.listview.setOnItemClickListener((adapterView, view, i, l) -> {
            ListData selectedData = dataArrayList.get(i);

            // Создаем фрагмент DetailedFragment
            DetailedFragment detailedFragment = new DetailedFragment();

            // Передаем данные в фрагмент
            Bundle args = new Bundle();
            args.putString("name", selectedData.getName());
            args.putString("desc", selectedData.getDesc());
            args.putInt("image", selectedData.getImage());
            detailedFragment.setArguments(args);

            // Заменяем текущий фрагмент на DetailedFragment
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, detailedFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
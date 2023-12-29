package com.example.citypomsjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.citypomsjava.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setBackground(null);
        initBottomNavigationView();
        initListView();
        replaceFragment(new HomeFragment());
    }

    private void initBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int selectedItemId = item.getItemId();
            if (selectedItemId == R.id.home) {
                replaceFragment(new HomeFragment());
                return true;
            } else if (selectedItemId == R.id.route) {
                replaceFragment(new RouteFragment());
                return true;
            } else if (selectedItemId == R.id.dashboard) {
                replaceFragment(new DashboardFragment());
                return true;
            } else if (selectedItemId == R.id.info) {
                replaceFragment(new InfoFragment());
                return true;
            }
            return false;
        });
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

        listAdapter = new ListAdapter(MainActivity.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setOnItemClickListener((adapterView, view, i, l) -> {
            ListData selectedData = dataArrayList.get(i);
            Intent intent = new Intent(MainActivity.this, DetailedFragment.class);
            intent.putExtra("desc", selectedData.getDesc());
            intent.putExtra("image", selectedData.getImage());
            startActivity(intent);
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}

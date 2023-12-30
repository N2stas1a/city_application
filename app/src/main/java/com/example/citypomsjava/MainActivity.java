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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setBackground(null);
        initBottomNavigationView();
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



    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}

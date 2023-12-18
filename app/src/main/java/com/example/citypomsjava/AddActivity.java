package com.example.citypomsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        String title = getIntent().getStringExtra("FishText");
        TextView fishIDTextView = findViewById(R.id.FishText);
        fishIDTextView.setText(title);
    }
}
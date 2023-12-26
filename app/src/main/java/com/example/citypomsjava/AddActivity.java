package com.example.citypomsjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.citypomsjava.DBManager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.citypomsjava.DatabaseHelper;
import androidx.fragment.app.FragmentManager;


public class AddActivity extends AppCompatActivity {

    EditText TramNumberID, TramNumber;
    Button add_button;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        String title = getIntent().getStringExtra("FishText");
        TextView fishIDTextView = findViewById(R.id.FishText);
        fishIDTextView.setText(title);

        TramNumberID = findViewById(R.id.TramNumberID);
        TramNumber = findViewById(R.id.TramNumber);
        add_button = findViewById(R.id.add_button);
        dbManager = new DBManager(this);
        dbManager.open();

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tramN = Integer.parseInt(TramNumber.getText().toString().trim());
                int tramID = Integer.parseInt(TramNumberID.getText().toString().trim());
                dbManager.insert_trams(tramID, tramN);

                FragmentManager fragmentManager = getSupportFragmentManager(); // Use getSupportFragmentManager() if you're using AppCompatActivity
                fragmentManager.popBackStack(); // P
            }
        });
    }
}

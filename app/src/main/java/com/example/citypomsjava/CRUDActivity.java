package com.example.citypomsjava;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.LayoutInflater;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


public class CRUDActivity extends AppCompatActivity {

    EditText TramNumberID, TramNumber;
    Button add_button;

    private DBManager dbManager;

    public class CRUDButtonAdapters extends BaseAdapter {

        @Override
        public int getCount() {
            //return yourDataList.size();
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Object context;
            View listItemView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item, parent, false);
            return listItemView;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        TramNumberID = findViewById(R.id.TramNumberID);
        TramNumber = findViewById(R.id.TramNumber);
        add_button = findViewById(R.id.add_button);
        dbManager = new DBManager(this);
        dbManager.open();


        add_button.setOnClickListener(view -> {
            int tramN = Integer.parseInt(TramNumber.getText().toString().trim());
            int tramID = Integer.parseInt(TramNumberID.getText().toString().trim());
            dbManager.insert_trams(tramID, tramN);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.popBackStack();
            finish();
        });
    }
}

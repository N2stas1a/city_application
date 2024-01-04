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
    Button add_button,update_button,delete_button;

    String id;
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

            //Button deleteButton = listItemView.findViewById(R.id.delete_button);
            //Button updateButton = listItemView.findViewById(R.id.update_button);
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
//        update_button = findViewById(R.id.update_button);
//        delete_button = findViewById(R.id.delete_button);
        dbManager = new DBManager(this);
        dbManager.open();

//        delete_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                confirmDialog();
//            }
//            private void confirmDialog() {
//                AlertDialog.Builder builder = new AlertDialog.Builder(CRUDActivity.this);
//                builder.setTitle("Delete " + TramNumberID.getText().toString() + " ?");
//                builder.setMessage("Are you sure you want to delete " + TramNumberID.getText().toString() + " ?");
//                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
//                    DatabaseHelper myDB = new DatabaseHelper(CRUDActivity.this);
//                    myDB.deleteOneRow(id);
//                    finish();
//                });
//                builder.setNegativeButton("No", (dialogInterface, i) -> {
//                    builder.show();
//                });
//            }
//        });

//        update_button.setOnClickListener(view -> {
//            DatabaseHelper db = new DatabaseHelper(CRUDActivity.this);
//            int tramNumber = Integer.parseInt(TramNumber.getText().toString().trim());
//            String tramNumberID = TramNumberID.getText().toString().trim();
//            db.updateData(id, tramNumber, tramNumberID);
//        });
//
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

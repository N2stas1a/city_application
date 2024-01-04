package com.example.citypomsjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText RecordName, RecordID;
    private DBManager dbManager;
    Button update_button, delete_button;

    String id, tramN, tramNId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        int selected_record_id = getIntent().getIntExtra("id", -1);
        String selected_record_title = getIntent().getStringExtra("title");

        RecordName = findViewById(R.id.name_input2);
        RecordID = findViewById(R.id.id_input2);
        String selected_record_id_string = String.valueOf(selected_record_id);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        RecordName.setText(selected_record_title);
        RecordID.setText(selected_record_id_string);

        dbManager = new DBManager(this);
        dbManager.open();

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }

            private void confirmDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setTitle("Delete " + RecordName.getText().toString() + " ?");
                builder.setMessage("Are you sure you want to delete " + RecordName.getText().toString() + " ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                        myDB.deleteOneRow(id);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
            }
        });

        update_button.setOnClickListener(view -> {
            int tramNumberID = Integer.parseInt(RecordID.getText().toString().trim());
            int tramNumber = Integer.parseInt(RecordName.getText().toString().trim());
            dbManager.update(tramNumberID, tramNumber);
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
        });
    }
}
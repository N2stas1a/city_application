package com.example.citypomsjava;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText TramNumberID, TramNumber;
    Button update_button, delete_button;

    String id, tramN, tramNId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        TramNumberID = findViewById(R.id.name_input2);
        TramNumber = findViewById(R.id.id_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }

            private void confirmDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setTitle("Delete " + TramNumberID.getText().toString() + " ?");
                builder.setMessage("Are you sure you want to delete " + TramNumberID.getText().toString() + " ?");
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
            onUpdateButtonClick(view);  // Вызываем метод onUpdateButtonClick

            // Ваш код после вызова onUpdateButtonClick
            DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
            int tramNumber = Integer.parseInt(TramNumber.getText().toString().trim());
            String tramNumberID = TramNumberID.getText().toString().trim();
            db.updateData(id, tramNumber, tramNumberID);

            // Выводим сообщение в лог
            Log.d("UpdateActivity", "onUpdateButtonClick is called!");
        });

    }
    public void onUpdateButtonClick(View view) {
        DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
        int tramNumber = Integer.parseInt(TramNumber.getText().toString().trim());
        String tramNumberID = TramNumberID.getText().toString().trim();
        db.updateData(id, tramNumber, tramNumberID);
    }
}
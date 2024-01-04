//package com.example.citypomsjava;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class UDActivity extends AppCompatActivity {
//
//    EditText TramNumberID, TramNumber;
//    Button update_button, delete_button;
//
//    String id, tramN, tramNId;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.list_item);
//
////        TramNumberID = findViewById(R.id.TramNumberID);
////        TramNumber = findViewById(R.id.TramNumber);
//        update_button = findViewById(R.id.update_button);
//        delete_button = findViewById(R.id.delete_button);
//
//        delete_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                confirmDialog();
//            }
//
//            private void confirmDialog() {
//                AlertDialog.Builder builder = new AlertDialog.Builder(UDActivity.this);
//                builder.setTitle("Delete " + TramNumberID.getText().toString() + " ?");
//                builder.setMessage("Are you sure you want to delete " + TramNumberID.getText().toString() + " ?");
//                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
//                    DatabaseHelper myDB = new DatabaseHelper(UDActivity.this);
//                    myDB.deleteOneRow(id);
//                    finish();
//                });
//                builder.setNegativeButton("No", (dialogInterface, i) -> {
//
//                });
//            }
//        });
//
//        update_button.setOnClickListener(view -> {
//            DatabaseHelper db = new DatabaseHelper(UDActivity.this);
//            int tramNumber = Integer.parseInt(TramNumber.getText().toString().trim());
//            String tramNumberID = TramNumberID.getText().toString().trim();
//            db.updateData(id, tramNumber, tramNumberID);
//        });
//    }
//}
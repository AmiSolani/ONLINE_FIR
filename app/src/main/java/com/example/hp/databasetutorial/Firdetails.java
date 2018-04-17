package com.example.hp.databasetutorial;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Firdetails extends AppCompatActivity {
    DatabaseHelper myDb;
    Button addFirData;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    EditText victimname, crimetype, crimedetails, witnessdetails, place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firdetails);
        myDb=new DatabaseHelper(this);
        victimname = (EditText) findViewById(R.id.editText_victimname);
        crimetype = (EditText) findViewById(R.id.editcrimetype);
        crimedetails = (EditText) findViewById(R.id.editText_crimedetails);
        witnessdetails = (EditText) findViewById(R.id.editText_witnessdetails);
        place = (EditText) findViewById(R.id.editText_place);
        addFirData = (Button) findViewById(R.id.btnsubmitfir);
        //listener for next activity
        addFirData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s1 = victimname.getText().toString();
                String s2 = crimetype.getText().toString();
                String s3 = crimedetails.getText().toString();
                String s4 = place.getText().toString();
                String s5 = witnessdetails.getText().toString();
                if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("")) {
                    Toast.makeText(Firdetails.this, "None of the field should be empty", Toast.LENGTH_LONG).show();
                } else {
                    boolean isInserted1 = myDb.insertFirData(s1, s2, s3, s4, s5);
                    if (isInserted1 == true) {
                        int id=myDb.get_Id();
                        //Toast.makeText(Firdetails.this, "Id found yeyyyyyyy"+ id , Toast.LENGTH_LONG).show();
                        Intent i=new Intent(Firdetails.this,Editstatus.class);
                        i.putExtra("id",id);
                        startActivity(i);
                    } else
                        Toast.makeText(Firdetails.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                    victimname.setText("");
                    crimetype.setText("");
                    crimedetails.setText("");
                    place.setText("");
                    witnessdetails.setText("");
                }
            }
        });//function for validating data

    }


    // public void validateData() {
    //  addFirData.setOnClickListener(new View.OnClickListener(){
    //public void onClick(View v) {

    //}


}




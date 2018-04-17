package com.example.hp.databasetutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid);
        Button b1 = (Button) findViewById(R.id.btnfir);
        Button b2 = (Button) findViewById(R.id.btnviewfir);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mid.this, Firdetails.class);
                startActivity(i);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mid.this, Editstatus.class);
                i.putExtra("tv","Enter id which is already given to you");
                startActivity(i);
            }
        });

    }
}
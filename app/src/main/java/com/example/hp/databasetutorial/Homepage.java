package com.example.hp.databasetutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        TextView t1= (TextView)findViewById(R.id.textView);
        Button b1= (Button)findViewById(R.id.button);
        Button b2= (Button)findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(Homepage.this,MainActivity.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(Homepage.this,Login.class);
                startActivity(i);
            }
        });
    }
}

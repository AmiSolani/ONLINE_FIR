package com.example.hp.databasetutorial;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Editstatus extends AppCompatActivity {
    Button view;
    EditText victimid;
    TextView tview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editstatus);
        view=(Button)findViewById(R.id.viewfir);
        victimid=(EditText)findViewById(R.id.edittext_victimid);
        tview=(TextView)findViewById(R.id.textfir);
        Intent i=getIntent();
        String tv=i.getExtras().getString("tv");
        if(tv!="") {
            tview.setText(tv);
        }else
        {
            tview.setText("Remember this ID to view your FIR");
        }
        int id=i.getIntExtra("id",0);
        victimid.setText(String.valueOf(id));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Editstatus.this,Status.class);
                i.putExtra("victimid1",victimid.getText().toString());
                startActivity(i);
            }
        });
    }


}

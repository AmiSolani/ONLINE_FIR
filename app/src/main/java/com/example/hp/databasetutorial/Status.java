package com.example.hp.databasetutorial;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Status extends AppCompatActivity {
    public DatabaseHelper DB;
    public ListView lv;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        lv=(ListView)findViewById(R.id.viewData);
        DB=new DatabaseHelper(this);
        Intent i=getIntent();
        String vi=i.getExtras().getString("victimid1");
        populateListView(vi);
    }
    public void populateListView(String id)
    {
        Cursor c=DB.getUserData(id);
        ArrayList<String> listData=new ArrayList<>();
        while(c.moveToNext())
        {
            listData.add(c.getString(1));
            listData.add(c.getString(2));
            listData.add(c.getString(3));
            listData.add(c.getString(4));
            listData.add(c.getString(5));
        }
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        lv.setAdapter(adapter);
    }
}

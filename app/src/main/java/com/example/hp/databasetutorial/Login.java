package com.example.hp.databasetutorial;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

public class Login extends AppCompatActivity {
        EditText email1;
        EditText pwd;
        Button login;
        Cursor cursor;
        SQLiteDatabase db;
        SQLiteOpenHelper openHelper;
        //DatabaseHelper db;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            email1 = (EditText) findViewById(R.id.editText_login_email);
            pwd = (EditText) findViewById(R.id.editText_login_pwd);
            login = (Button) findViewById(R.id.btnlogin);
            openHelper=new DatabaseHelper(this);
            db = openHelper.getReadableDatabase();
            //db=new DatabaseHelper(this);
            login.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String s1 = email1.getText().toString();
                    String s2 = pwd.getText().toString();
                    cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_3 + "=? AND " + DatabaseHelper.COL_5+ "=?", new String[]{s1,s2});
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            email1.setText("");pwd.setText("");
                            Intent i=new Intent(Login.this,Mid.class);
                            startActivity(i);
                            //Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login error  "+cursor.getCount(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
}

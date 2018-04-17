package com.example.hp.databasetutorial;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP on 03-04-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "amib.db";
    public static final String TABLE_NAME = "registration_table";
    public static final String TABLE_NAME1 ="firdata_table";


    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "NUMBER";
    public static final String COL_5 = "PASSWORD";

    public static final String COL1 = "ID";
    public static final String COL2 = "VICTIMNAME";
    public static final String COL3 = "CRIMETYPE";
    public static final String COL4 = "CRIMEDETAILS";
    public static final String COL5 = "PLACE";
    public static final String COL6 = "WITNESSDETAILS";



    private SQLiteDatabase db;
    private int oldVersion;
    private int newVersion;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,NUMBER INTEGER,PASSWORD TEXT)");
        db.execSQL("create table " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,VICTIMNAME TEXT,CRIMETYPE TEXT,CRIMEDETAILS TEXT,PLACE TEXT,WITNESSDETAILS TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
       // db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT PRIMARY KEY,NUMBER INTEGER,PASSWORD TEXT)");
        onCreate(db);
    }

    public boolean insertData(String name, String email, String number, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, number);
        contentValues.put(COL_5, pwd);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertFirData(String name,String crimetype,String crimedetails,String place,String witnessdetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, name);
        cv.put(COL3, crimetype);
        cv.put(COL4, crimedetails);
        cv.put(COL5, place);
        cv.put(COL6, witnessdetails);
        long result = db.insert(TABLE_NAME1, null, cv);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

   /* public boolean updateData(String name, String crimedetails,String place,String witnessdetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,"dot");
        contentValues.put(COL4,crimedetails);
        contentValues.put(COL5,place);
        contentValues.put(COL6,witnessdetails);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { name });
        return true;
    } */

    public Cursor getUserData(String id)
    {
       SQLiteDatabase db=this.getWritableDatabase();
       Cursor data= db.rawQuery("select * from firdata_table where id=?",new String[]{id});
        return data;
    }

    public boolean emailpwd(String  email,String pwd)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME+"where "+ COL_3+"=? and "+COL_5+"=?",new String[]{email,pwd});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public int get_Id()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(TABLE_NAME1,null,"ID=(SELECT MAX(ID) FROM "+TABLE_NAME1+ ")",null,null,null,null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            return id;
        }
        else
        {
            return 0;
        }
    }



}

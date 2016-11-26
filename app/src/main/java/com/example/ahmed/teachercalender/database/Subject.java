package com.example.ahmed.teachercalender.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ahmed on 06/11/2016.
 */
public class Subject {

   public static String SUBJECT_ID="subject_id";
    public static String SUBJECT_NAME="subject_name";
    public static String TABLE_NAME="subject";
    private String name;
    private int id;

    public Subject() {
    }

    public Subject(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static void createSubject(String name, SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues=new ContentValues();


        contentValues.put(SUBJECT_NAME,name);

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

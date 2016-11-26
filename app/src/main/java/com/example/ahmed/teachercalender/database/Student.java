package com.example.ahmed.teachercalender.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ahmed on 06/11/2016.
 */
public class Student {
    public static String TABLE_NAME="student";
    public static String STUDENT_ID="student_id";
    public static String STUDENT_NAME="student_name";
     public Student(){}
    private String name;
    private int id;

    public static void  createStudent(int student_id, String student_name, SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues=new ContentValues();

        contentValues.put(STUDENT_ID,student_id);
        contentValues.put(STUDENT_NAME,student_name);

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

    }
    public static void  createStudent( String student_name, SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues=new ContentValues();
          contentValues.put(STUDENT_NAME,student_name);
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }


    public Student(String name, int id) {
        this.name = name;
        this.id = id;
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

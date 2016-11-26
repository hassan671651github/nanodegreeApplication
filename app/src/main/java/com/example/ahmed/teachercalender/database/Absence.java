package com.example.ahmed.teachercalender.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ahmed on 06/11/2016.
 */
public class Absence {

    public Absence() {
    }

    public Absence(int id, Date date) {
        this.id = id;
        this.date = date;

    }
    public static String TABLE_NAME="absence";
    public static String ID="absence_id";
    public static String DATE="absence_date";


    private int id;
    private Date date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void createAbsence(int id, Date date, SQLiteDatabase sqLiteDatabase){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues contentValues=new ContentValues();
        contentValues.put(Absence.ID,id);
        contentValues.put(Absence.DATE,dateFormat.format(date));
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);



    }
    public static void createAbsence( Date date, SQLiteDatabase sqLiteDatabase){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues contentValues=new ContentValues();

        contentValues.put(Absence.DATE,dateFormat.format(date));

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);


    }





}

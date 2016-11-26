package com.example.ahmed.teachercalender.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ahmed on 06/11/2016.
 */
public class Quizz {
    public Quizz() {
    }

    public Quizz(int id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public static String TABLE_NAME="quizz";
    public static String QUIZZ_ID="quizz_id";
    public static String QUIZZ_NAME="quizz_name";
    public static String QUIZZ_DATE="quizz_date";

    private int id;
    private String name;
    private Date date;
    public static void createQuizz(String name, Date date, SQLiteDatabase sqLiteDatabase){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues contentValues=new ContentValues();

        contentValues.put(Quizz.QUIZZ_DATE,dateFormat.format(date));
        contentValues.put(QUIZZ_NAME,name);

        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

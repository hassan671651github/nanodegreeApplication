package com.example.ahmed.teachercalender.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.DOMStringList;

/**
 * Created by Ahmed on 06/11/2016.
 */
public class Section {
    public Section() {
    }

    public Section(String name, int id, String day, String time, String location) {
        this.name = name;
        this.id = id;
        this.day = day;
        this.time = time;
        this.location = location;
    }

    public static String SECTION_ID="section_id";
    public static String SECTION_NAME="section_name";
    public static String SECTION_DAY="section_day";
    public static String SECTION_TIME="section_time";
    public static String SECTION_LOCATION="section_location";
    public static String SUBJECT_ID="subject_id";
    public static String TABLE_NAME="section";

    private String name;
    private int id;
    private String day;
    private String time;
    private String location;
    private int subject_id;

public static void createSection(String name,  String day, String time, String location, int subject_id, SQLiteDatabase sqLiteDatabase){

    ContentValues contentValues=new ContentValues();
    contentValues.put(Section.SECTION_NAME,name);
    contentValues.put(SECTION_DAY,day);
    contentValues.put(SECTION_TIME,time);
    contentValues.put(SECTION_LOCATION ,location);
    contentValues.put(SUBJECT_ID,subject_id);

    sqLiteDatabase.insert(TABLE_NAME,null,contentValues);


}
    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }





}

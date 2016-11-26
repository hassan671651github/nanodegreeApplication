package com.example.ahmed.teachercalender.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ahmed on 09/11/2016.
 */
public class Student_absence {

    public Student_absence() {
    }

    public Student_absence(int student_id, int absence_id, int subject_id) {
        this.student_id = student_id;
        this.absence_id = absence_id;
        this.subject_id = subject_id;
    }

    public static String STUDENT_ID="student_id";
    public static String SUBJECT_ID="subject_id";
    public static String ABSENCE_ID="absence_id";
    public static String VALUE="student_value";
    public static String TABLE_NAME="student_absence";

    private boolean value;
    private int student_id;
    private int absence_id;
    private int subject_id;
public static void cretae_student_absence(int student_id, int absence_id, int subject_id, boolean value, SQLiteDatabase sqLiteDatabase){
    ContentValues contentValues=new ContentValues();

    contentValues.put(STUDENT_ID,student_id);
    contentValues.put(SUBJECT_ID,subject_id);
    contentValues.put(ABSENCE_ID,absence_id);
    contentValues.put(VALUE,value);

    sqLiteDatabase.insert(TABLE_NAME,null,contentValues);





}
    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getAbsence_id() {
        return absence_id;
    }

    public void setAbsence_id(int absence_id) {
        this.absence_id = absence_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}

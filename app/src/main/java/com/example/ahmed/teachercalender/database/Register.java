package com.example.ahmed.teachercalender.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ahmed on 09/11/2016.
 */
public class Register {

    public Register() {
    }

    public Register(int secion_id, int student_id, int subject_id) {
        this.secion_id = secion_id;
        this.student_id = student_id;
        this.subject_id = subject_id;
    }
    public static String TABLE_NAME="register";
    public static String  STUDENT_ID="student_id";
    public static String SUBJECT_ID="subject_id";
    public static String SECTION_ID="section_id";

    private int  secion_id;
    private int student_id;
    private  int subject_id;
public static void createRegister(int stu_id, int sub_id, int sec_id, SQLiteDatabase sqLiteDatabase)
{
    ContentValues contentValues=new ContentValues();
    contentValues.put(STUDENT_ID,stu_id);
    contentValues.put(SUBJECT_ID,sub_id);
    contentValues.put(SECTION_ID,sec_id);
    sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

}

    public int getSecion_id() {
        return secion_id;
    }

    public void setSecion_id(int secion_id) {
        this.secion_id = secion_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }
}

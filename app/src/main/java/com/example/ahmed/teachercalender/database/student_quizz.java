package com.example.ahmed.teachercalender.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ahmed on 09/11/2016.
 */
public class student_quizz {

    public student_quizz() {
    }

    public student_quizz(int student_id, int subject_id, int quizz_id, float value) {
        this.student_id = student_id;
        this.subject_id = subject_id;
        this.quizz_id = quizz_id;
        this.value = value;
    }

    public static String STUDENT_ID="student_id";
    public static String SUBJECT_ID="subject_id";
    public static String QUIZZ_ID="quizz_id";
    public static String DEGREE="degree";
    public static String TABLE_NAME="student_quizz";



    private int student_id;
    private int subject_id;
    private int quizz_id;
    private float value;

public static void create_student_quizz(int subject_id, int student_id, int quizz_id, float value, SQLiteDatabase sqLiteDatabase){
    ContentValues contentValues=new ContentValues();

    contentValues.put(STUDENT_ID,student_id);
    contentValues.put(SUBJECT_ID ,subject_id);
    contentValues.put(QUIZZ_ID,quizz_id);
    contentValues.put(DEGREE,value);
    sqLiteDatabase.insert(TABLE_NAME,null,contentValues);




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

    public int getQuizz_id() {
        return quizz_id;
    }

    public void setQuizz_id(int quizz_id) {
        this.quizz_id = quizz_id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}

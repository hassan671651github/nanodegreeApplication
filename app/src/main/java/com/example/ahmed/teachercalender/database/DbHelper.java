package com.example.ahmed.teachercalender.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmed on 09/11/2016.
 */


public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);


    }
Context context;
    @Override
    public void onCreate(SQLiteDatabase db) {

       db.execSQL("create table "+Student.TABLE_NAME+"("+Student.STUDENT_ID +" integer primary key AUTOINCREMENT ,"+Student.STUDENT_NAME +" char(50));");
        db.execSQL("create table "+Subject.TABLE_NAME+"("+Subject.SUBJECT_ID +" integer primary key AUTOINCREMENT,"+Subject.SUBJECT_NAME +" char(50));");
        db.execSQL("create table "+Section.TABLE_NAME+"("+Section.SECTION_ID +" integer primary key AUTOINCREMENT,"+Section.SECTION_NAME+" char(50),"
        +Section.SECTION_LOCATION+" char(50),"+Section.SECTION_DAY+" char(20),"+Section.SECTION_TIME +" char(20),"+Section.SUBJECT_ID+" int " +
                "references "+Subject.TABLE_NAME+"("+Subject.SUBJECT_ID+"));");
       db.execSQL("create table "+Quizz.TABLE_NAME+" (quizz_id integer primary key AUTOINCREMENT  ,quizz_name char(50) ,quizz_date date );");
       db.execSQL("create table register (student_id int references student(student_id),subject_id int references subject(subject_id),section_id int references section(section_id),primary key(student_id,subject_id));");

        db.execSQL("create table absence (absence_id integer primary key AUTOINCREMENT ,absence_date date);");
        db.execSQL("create table student_absence(student_id int  references student(student_id),subject_id int references subject(subject_id),absence_id int references absence(absence_id),value boolean,primary key(student_id,absence_id,subject_id),unique(student_id,absence_id));");
       db.execSQL("create table student_quizz(student_id int references student(student_id),subject_id int references subject(subject_id),degree double,quizz_id int references quizz(quizz_id),primary key(student_id,quizz_id ,subject_id),unique(student_id,quizz_id));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




}

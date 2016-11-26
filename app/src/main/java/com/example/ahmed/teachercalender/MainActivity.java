package com.example.ahmed.teachercalender;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.ahmed.teachercalender.database.*;
import com.example.ahmed.teachercalender.Interfaces.*;

public class MainActivity extends AppCompatActivity implements CoursesFragment.OnFragmentInteractionListener ,SectionFragment.OnFragmentInteractionListener ,connection{
    public boolean  isTowPane;
    SQLiteDatabase sqLiteDatabase;
    private int courseId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frame1=(FrameLayout)findViewById(R.id.Frame1Main);

        if(frame1==null)
            isTowPane=false;

        else
            isTowPane=true;

         DbHelper v=new DbHelper(this,"fci",null,1);
         sqLiteDatabase=v.getWritableDatabase();

        Subject.createSubject("JAVA",sqLiteDatabase);
        Subject.createSubject("C#",sqLiteDatabase);

        Section.createSection("section1 ","monday","1:3","lap 4 A",2,sqLiteDatabase);
        Section.createSection("section2 ","monday","1:3","lap 4 A",2,sqLiteDatabase);
        Section.createSection("section3 ","monday","1:3","lap 4 A",2,sqLiteDatabase);
        Section.createSection("section1 ","monday","1:3","lap 4 A",1,sqLiteDatabase);
        Section.createSection("section2 ","monday","1:3","lap 4 A",2,sqLiteDatabase);
        Section.createSection("section3 ","monday","1:3","lap 4 A",1,sqLiteDatabase);

        Student.createStudent("mohamed aboali moslem salem",sqLiteDatabase);
        Student.createStudent("ahmed aboali moslem salem",sqLiteDatabase);
        Student.createStudent("hassan aboali moslem salem",sqLiteDatabase);
        Student.createStudent("ahmed mohamed hassan",sqLiteDatabase);
        Register.createRegister(1,1,1,sqLiteDatabase);
        Register.createRegister(2,2,2,sqLiteDatabase);
        Register.createRegister(3,2,2,sqLiteDatabase);
        Register.createRegister(4,1,1,sqLiteDatabase);



       setUI(sqLiteDatabase);

    }



    public void setUI(SQLiteDatabase sqLiteDatabase){


        if(isTowPane){
            CoursesFragment coursesFragment =new CoursesFragment();
            SectionFragment sectionFragment=new SectionFragment();
            sectionFragment.setConnection(this);
            coursesFragment.setConnnection(this);

            coursesFragment.setSqLiteDatabase(sqLiteDatabase);
            sectionFragment.setSqLiteDatabase(sqLiteDatabase);
            getFragmentManager().beginTransaction().replace(R.id.Frame1Main, coursesFragment).commit();
            getFragmentManager().beginTransaction().replace(R.id.Frame2Main, sectionFragment).commit();
        }
        else {
            CoursesFragment coursesFragment =new CoursesFragment();
            coursesFragment.setSqLiteDatabase(sqLiteDatabase);
            coursesFragment.setConnnection(this);
            getFragmentManager().beginTransaction().replace(R.id.activity_main, coursesFragment).commit();

        }



    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void connect(int id, String sender) {
        if(sender=="CoursesFragment")
        {
           if(isTowPane)
           {
             courseId=id;
             Bundle bundle=new Bundle();
             SectionFragment sectionFragment=new SectionFragment();
             sectionFragment.setSqLiteDatabase(sqLiteDatabase);
               sectionFragment.setConnection(this);
             bundle.putInt("courseId",id);
             sectionFragment.setArguments(bundle);
             getFragmentManager().beginTransaction().replace(R.id.Frame2Main, sectionFragment).commit();

           }


            else
           {


               Intent intent = new Intent(this, SectionActivity.class);
                intent.putExtra("courseId", id);
                   startActivity(intent);

           }
        }

        if (sender == "section") {
          Intent intent=new Intent(this,SectionDetails.class);
            intent.putExtra("sectionId",id);
            intent.putExtra("courseId",courseId);
            startActivity(intent);


        }













    }


}

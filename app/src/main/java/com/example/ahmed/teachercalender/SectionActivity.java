package com.example.ahmed.teachercalender;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ahmed.teachercalender.Interfaces.connection;
import com.example.ahmed.teachercalender.database.DbHelper;

public class SectionActivity extends AppCompatActivity implements connection,SectionFragment.OnFragmentInteractionListener {

    private SQLiteDatabase sqLiteDatabase;
    private  int CourseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);


        try {
           sqLiteDatabase=new DbHelper(this,"fci",null,1).getWritableDatabase();
            Bundle bundle = getIntent().getExtras();
            CourseId=bundle.getInt("courseId");
            SectionFragment sectionFragment = new SectionFragment();

            sectionFragment.setSqLiteDatabase(sqLiteDatabase);
            sectionFragment.setConnection(this);
            sectionFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.activitySection, sectionFragment).commit();

        }
        catch (Exception e) {
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void connect(int id, String sender) {

       if(sender=="section"){

    Intent intent=new Intent(this,SectionDetails.class);
    intent.putExtra("sectionId",id);
    intent.putExtra("courseId",CourseId);
    startActivity(intent);

}




    }
}
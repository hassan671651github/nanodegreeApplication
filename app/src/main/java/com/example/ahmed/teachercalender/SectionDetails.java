package com.example.ahmed.teachercalender;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.ahmed.teachercalender.Interfaces.connection;

public class SectionDetails extends AppCompatActivity implements connection,StudentDetails.OnFragmentInteractionListener,StudentNames.OnFragmentInteractionListener {
boolean isTowPane=false;
    int subject_id;
    int section_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_details);

        FrameLayout Frame1=(FrameLayout)findViewById(R.id.frame1SectionDetails);
        FrameLayout Frame2=(FrameLayout)findViewById(R.id.frame2SectionDetails);
        if(Frame1!=null)
            isTowPane=true;

            setUi();

    }
    private  void setUi() {

        if(isTowPane)
        {
            section_id=getIntent().getExtras().getInt("sectionId");
            subject_id=getIntent().getExtras().getInt("courseId");
            StudentNames studentNames=new StudentNames();
            studentNames.setConnection(this);
            studentNames.setArguments(getIntent().getExtras());
            StudentDetails studentDetails=new StudentDetails();
            studentDetails.setConnection(this);
            getFragmentManager().beginTransaction().replace(R.id.frame1SectionDetails, studentNames).commit();
            getFragmentManager().beginTransaction().replace(R.id.frame2SectionDetails, studentDetails).commit();


        }
        else
        {
            section_id=getIntent().getExtras().getInt("sectionId");
            subject_id=getIntent().getExtras().getInt("courseId");
            StudentNames studentNames=new StudentNames();
            studentNames.setConnection(this);
            studentNames.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().replace(R.id.sectionDetailsLayout, studentNames).commit();

        }
    }

    @Override
    public void connect(int id, String sender) {
        if(sender=="studentNames")
        {
               if(isTowPane)
               {
                   Bundle bundle=new Bundle();
                   bundle.putInt("sectionId",section_id);
                   bundle.putInt("courseId",subject_id);
                   bundle.putInt("student_id",id);
                   StudentDetails studentDetails=new StudentDetails();
                   studentDetails.setConnection(this);
                   studentDetails.setArguments(bundle);
                   getFragmentManager().beginTransaction().replace(R.id.frame2SectionDetails, studentDetails).commit();
               }
               else
               {

                   Intent intent=new Intent(this,StudentDetailsActivity.class);

                   intent.putExtra("sectionId",section_id);
                   intent.putExtra("courseId",subject_id);
                   intent.putExtra("student_id",id);

                       startActivity(intent);


               }


        }

        if(sender=="studentDetails")
        {





        }

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

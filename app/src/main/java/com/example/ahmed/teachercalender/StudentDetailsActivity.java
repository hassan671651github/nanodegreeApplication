package com.example.ahmed.teachercalender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ahmed.teachercalender.Interfaces.connection;

public class StudentDetailsActivity extends AppCompatActivity  implements connection {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_student_details);
         StudentDetails studentDetails=new StudentDetails();
         studentDetails.setConnection(this);

         studentDetails.setArguments(getIntent().getExtras());
         getFragmentManager().beginTransaction().replace(R.id.activity_student_details, studentDetails).commit();





    }

    @Override
    public void connect(int id, String sender) {




    }
}

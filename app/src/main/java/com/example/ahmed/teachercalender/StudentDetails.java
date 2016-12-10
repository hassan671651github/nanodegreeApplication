package com.example.ahmed.teachercalender;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.teachercalender.Adapters.AbsenceAdapter;
import com.example.ahmed.teachercalender.Adapters.QuizzAdapter;
import com.example.ahmed.teachercalender.Interfaces.connection;
import com.example.ahmed.teachercalender.database.Absence;
import com.example.ahmed.teachercalender.database.DbHelper;
import com.example.ahmed.teachercalender.database.Quizz;
import com.example.ahmed.teachercalender.database.Student_absence;
import com.example.ahmed.teachercalender.database.student_quizz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentDetails extends Fragment implements connection {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StudentDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentDetails newInstance(String param1, String param2) {
        StudentDetails fragment = new StudentDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    /*************************************************************************************/
       private connection con;
        public void setConnection(connection con){
            this.con=con;
        }
        private SQLiteDatabase sqLiteDatabase;
        private int SubjectId;
        private  int StudentId;
    private String query;
    private Cursor cursor;
    ArrayList<CustomQuizz>customQuizzs;
    ListView quizzListView;
    ListView absenceListView;
    ArrayList<CustomAbsence>customAbsenceArrayList;
    CustomAbsence customAbsence;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_student_details, container, false);


        SubjectId = getArguments().getInt("courseId");
        StudentId = getArguments().getInt("student_id");
        quizzListView =(ListView) view.findViewById(R.id.quizzesGridView) ;
        absenceListView=(ListView)view.findViewById(R.id.absenceListView) ;
        DbHelper v=new DbHelper(getActivity(),"fci",null,1);
        sqLiteDatabase=v.getWritableDatabase();
        LoadQuizzesData();
        LoadAbsenceData();
        return view;
    }



    public void LoadQuizzesData(){
        customQuizzs=new ArrayList<>();
      query  = "select " + Quizz.QUIZZ_NAME + "," + Quizz.QUIZZ_DATE + "," + student_quizz.DEGREE+","+Quizz.TABLE_NAME+"."+Quizz.QUIZZ_ID + " from " + Quizz.TABLE_NAME + "," +
                student_quizz.TABLE_NAME + " where " + Quizz.TABLE_NAME + "." + Quizz.QUIZZ_ID + "=" + student_quizz.TABLE_NAME + "." +
                student_quizz.QUIZZ_ID + " and " + student_quizz.STUDENT_ID+ "=" + StudentId + " and " + student_quizz.SUBJECT_ID + "=" + SubjectId + ";";
     cursor   = sqLiteDatabase.rawQuery(query, null);

        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){

            CustomQuizz quizz=new CustomQuizz();
            quizz.setName(cursor.getString(cursor.getColumnIndex(Quizz.QUIZZ_NAME)));
            try {
                quizz.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(cursor.getString(cursor.getColumnIndex(Quizz.QUIZZ_DATE))));


            }catch (Exception ex){}
            quizz.setValue(cursor.getFloat(cursor.getColumnIndex(student_quizz.DEGREE)));
            quizz.setId(cursor.getInt(cursor.getColumnIndex(Quizz.QUIZZ_ID)));
            customQuizzs.add(quizz);

        }
        QuizzAdapter adapter=new QuizzAdapter(getActivity(),customQuizzs,this);
        quizzListView.setAdapter(adapter);
    }
    public void LoadAbsenceData(){
        query = "select " + Absence.NAME + "," +Absence.TABLE_NAME + "." + Absence.ID +","+ Absence.DATE + "," + Student_absence.VALUE+ " from " + Absence.TABLE_NAME + "," +
                Student_absence.TABLE_NAME + " where " + Absence.TABLE_NAME + "." + Absence.ID + "=" + Student_absence.TABLE_NAME  + "." +
                Student_absence.ABSENCE_ID+ " and " + Student_absence.STUDENT_ID+ "=" + StudentId + " and " + Student_absence.SUBJECT_ID+ "=" + SubjectId + ";";
        cursor = sqLiteDatabase.rawQuery(query, null);
        customAbsenceArrayList=new ArrayList<>();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){

            CustomAbsence absence=new CustomAbsence();
            absence.setName(cursor.getString(cursor.getColumnIndex(Absence.NAME)));
            try {

                absence.setDate(new SimpleDateFormat("dd-MM-yyyy").parse(cursor.getString(cursor.getColumnIndex(Absence.DATE))));
                }catch (Exception ex){}
            absence.setValue(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(Student_absence.VALUE))));
            absence.setId(cursor.getInt(cursor.getColumnIndex(Absence.ID)));
            customAbsenceArrayList.add(absence);


        }
        AbsenceAdapter absenceAdapter =new AbsenceAdapter(getActivity(),customAbsenceArrayList,this);
        absenceListView.setAdapter(absenceAdapter);

    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void connect(int id, String sender) {
        if(sender=="AbsenceAdapter")
        {
            CustomAbsence customAbsence=customAbsenceArrayList.get(id);
           Student_absence.updateValue(StudentId,SubjectId,customAbsence.getId(),!customAbsence.getValue(),sqLiteDatabase);
           LoadAbsenceData();
        }
        if (sender == "QuizzAdapter") {

            changeDegreeFunc(id);
        }
    }
    private void changeDegreeFunc(final  int id )

    {

        LayoutInflater linf = LayoutInflater.from(getActivity());
        final View inflator = linf.inflate(R.layout.change_degree_layouut, null);
        final   EditText deg = (EditText) inflator.findViewById(R.id.change_degree_layouut_edit_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!deg.getText().toString().isEmpty())
                {
                    try
                    {

                  CustomQuizz c =customQuizzs.get(id);
                    student_quizz.updateDegree(SubjectId,StudentId,c.getId(),Float.parseFloat(deg.getText().toString()),sqLiteDatabase);
                    LoadQuizzesData();
                    }catch (Exception ex){Toast.makeText(getActivity(),"Error Equre",Toast.LENGTH_LONG).show();}
                }
            }
        });
        builder.setNegativeButton("cancel", null);

        builder.setTitle("Change Degree");
        builder.setView(inflator);
        builder.show();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package com.example.ahmed.teachercalender;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ahmed.teachercalender.Adapters.StudentNamesAdapter;
import com.example.ahmed.teachercalender.Interfaces.connection;
import com.example.ahmed.teachercalender.database.DbHelper;
import com.example.ahmed.teachercalender.database.Section;
import com.example.ahmed.teachercalender.database.Student;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StudentNames.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StudentNames#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentNames extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StudentNames() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentNames.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentNames newInstance(String param1, String param2) {
        StudentNames fragment = new StudentNames();
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
/*********************************************** my functions and global variables*********************/
    SQLiteDatabase sqLiteDatabase;
    connection con;
    int sectionId;
    int subjectId;
    ListView listView;
    ArrayList<Student>studentArrayList;
    public  void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase){
        this.sqLiteDatabase=sqLiteDatabase;
    }
     public  void setConnection(connection con){

         this.con=con;
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View fragment=   inflater.inflate(R.layout.fragment_student_names, container, false);


            studentArrayList=new ArrayList<>();
            listView=(ListView)fragment.findViewById(R.id.studentNamesList);
            DbHelper v = new DbHelper(getActivity(), "fci", null, 1);
            sqLiteDatabase = v.getWritableDatabase();
            Bundle bundle = getArguments();
            sectionId = bundle.getInt("sectionId");
            subjectId = bundle.getInt("courseId");
        Toast.makeText(getActivity(),sectionId+"",Toast.LENGTH_LONG).show();
            String query = "select student.student_name, student.student_id from student,register " +
          "where student.student_id=register.student_id and register.subject_id= " + subjectId + " and register.section_id="+sectionId+";";

            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
               Student student=new Student();
                student.setName(cursor.getString(cursor.getColumnIndex(Student.STUDENT_NAME)));
                student.setId(cursor.getInt(cursor.getColumnIndex(Student.STUDENT_ID)));
                 studentArrayList.add(student);
            }

        Student vdd=new Student();
        vdd.setName("ffffffffffffffffffff");
        vdd.setId(5);
        studentArrayList.add(vdd);
            StudentNamesAdapter studentNamesAdapter = new StudentNamesAdapter(studentArrayList, getActivity());
            listView.setAdapter(studentNamesAdapter);


listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        con.connect(studentArrayList.get(position).getId(),"studentNames");

    }
});


        return fragment;
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

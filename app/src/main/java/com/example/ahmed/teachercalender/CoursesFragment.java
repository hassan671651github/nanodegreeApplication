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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ahmed.teachercalender.Adapters.StudentNamesAdapter;
import com.example.ahmed.teachercalender.Adapters.coursesAdapter;
import com.example.ahmed.teachercalender.database.Subject;
import com.example.ahmed.teachercalender.Interfaces.*;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CoursesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoursesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
/********************************************my functions and variables   *******************************/
    private SQLiteDatabase sqLiteDatabase;
    public void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }
    private connection con;
    public void setConnnection(connection con) {
        this.con = con;
    }

    private GridView gridView;
    private ArrayList<Subject> subjects;


    public void LoadData() {


        try {



            subjects  = new ArrayList<Subject>();
            coursesAdapter adapter = new coursesAdapter(subjects, getActivity());
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + Subject.TABLE_NAME, null);

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                Subject subject = new Subject();
                subject.setName(cursor.getString(cursor.getColumnIndex(Subject.SUBJECT_NAME)));
                subject.setId(cursor.getInt(cursor.getColumnIndex(Subject.SUBJECT_ID)));
                subjects.add(subject);

            }


            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    con.connect(subjects.get(position).getId(), "CoursesFragment");

                }
            });

        } catch (Exception n) {
        }
    }



    /******************************************************************************************/
    private OnFragmentInteractionListener mListener;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


       getActivity(). getMenuInflater().inflate(R.menu.main_activity_menu, menu);



    }

    public CoursesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoursesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
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

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    View fragment = inflater.inflate(R.layout.fragment_courses, container, false);

        gridView = (GridView) fragment.findViewById(R.id.fragment_gridView);


LoadData();
        return fragment;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId()==R.id.create_course){

            LayoutInflater linf = LayoutInflater.from(getActivity());
            final View inflator = linf.inflate(R.layout.create_course_layout, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("Create Course");
            builder.setView(inflator);

            final EditText name = (EditText) inflator.findViewById(R.id.CourseName_createCourse);
            final Button save = (Button) inflator.findViewById(R.id.Save_CreateCourse);
            final Button changeImg = (Button) inflator.findViewById(R.id.ChangeImg_createCourse);
            final ImageView img = (ImageView) inflator.findViewById(R.id.CourseImg_CreateCourse);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!name.getText().toString().trim().isEmpty()) {
                        Subject.createSubject(name.getText().toString(), sqLiteDatabase);
                        Toast.makeText(getActivity(),"course created succesfuly",Toast.LENGTH_SHORT).show();
                        name.setText("");
                    }
                }
            });

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton)
                {
                    if(!name.getText().toString().trim().isEmpty()) {
                        Subject.createSubject(name.getText().toString(), sqLiteDatabase);
                        Toast.makeText(getActivity(),"course created succesfuly",Toast.LENGTH_SHORT).show();
                        name.setText("");
                    }
                    LoadData();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    LoadData();
                }
            });

            builder.show();

        }




        return super.onOptionsItemSelected(item);
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

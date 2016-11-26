package com.example.ahmed.teachercalender;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.ahmed.teachercalender.Adapters.sectionAdapter;
import com.example.ahmed.teachercalender.Interfaces.connection;
import com.example.ahmed.teachercalender.database.Section;
import com.example.ahmed.teachercalender.database.Subject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SectionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SectionFragment newInstance(String param1, String param2) {
        SectionFragment fragment = new SectionFragment();
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



    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        getActivity(). getMenuInflater().inflate(R.menu.section_menu, menu);



    }
    /*************************************my functions and global variables*******************************************************/
    ArrayList<Section> sectionArrayList;
    GridView gridView;
   private connection sectionConnection;
    private SQLiteDatabase sqLiteDatabase;
private     int courseId  =0;
    public void setSqLiteDatabase(SQLiteDatabase sqLiteDatabase){
        this.sqLiteDatabase=sqLiteDatabase;
    }

    public  void setConnection(connection sectionConnection){

        this.sectionConnection=sectionConnection;
    }


    private  void LoadData(){

        try {
            sectionArrayList = new ArrayList<>();
            String query = "select * from " + Section.TABLE_NAME + " where " + Subject.SUBJECT_ID + " = " + courseId + " ;";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                Section section = new Section();
                section.setName(cursor.getString(cursor.getColumnIndex(Section.SECTION_NAME)));
                section.setDay(cursor.getString(cursor.getColumnIndex(Section.SECTION_DAY)));
                section.setLocation(cursor.getString(cursor.getColumnIndex(Section.SECTION_LOCATION)));
                section.setTime(cursor.getString(cursor.getColumnIndex(Section.SECTION_TIME)));
                section.setId(cursor.getInt(cursor.getColumnIndex(Section.SECTION_ID)));
                sectionArrayList.add(section);
            }
        }catch (Exception ex){}

        sectionAdapter sectionAdapter = new sectionAdapter(sectionArrayList, getActivity(),false);

        gridView.setAdapter(sectionAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getActivity(),SectionDetails.class);

                sectionConnection.connect(sectionArrayList.get(position).getId(),"section");

            }
        });
    }



    /*************************************************************************************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_section, container, false);


        gridView = (GridView) fragment.findViewById(R.id.sectionGridView);

        try {
            courseId = getArguments().getInt("courseId");
        } catch (Exception ex) {
        }

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
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.showdetails){
            sectionAdapter sectionAdapter = new sectionAdapter(sectionArrayList, getActivity(),true);

            gridView.setAdapter(sectionAdapter);
        }

        if(item.getItemId()==R.id.create_section)
        {

            LayoutInflater linf = LayoutInflater.from(getActivity());
            final View inflator = linf.inflate(R.layout.create_section_layout, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setTitle("Create Section");
            builder.setView(inflator);

            final EditText name = (EditText) inflator.findViewById(R.id.name_createSection);
            final EditText day = (EditText) inflator.findViewById(R.id.day_createSection);
            final EditText from = (EditText) inflator.findViewById(R.id.from_createSection);
            final EditText to = (EditText) inflator.findViewById(R.id.to_createSection);
            final EditText location = (EditText) inflator.findViewById(R.id.location_createSection);

            final Button save = (Button) inflator.findViewById(R.id.save_createSection);
            final Button changeImg = (Button) inflator.findViewById(R.id.changeImg_createSection);
            final ImageView img = (ImageView) inflator.findViewById(R.id.CourseImg_CreateCourse);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!name.getText().toString().trim().isEmpty()&&!day.getText().toString().trim().isEmpty()
                            &&!from.getText().toString().trim().isEmpty()&&!to.getText().toString().trim().isEmpty()
                            &&!location.getText().toString().trim().isEmpty()) {

                        Section.createSection(name.getText().toString(),day.getText().toString(),
                                from.getText()+":"+to.getText(),location.getText().toString(),courseId,sqLiteDatabase);
                        Toast.makeText(getActivity(),"section created succesfuly",Toast.LENGTH_SHORT).show();
                        name.setText(""); from.setText("");
                        day.setText("");to.setText(""); location.setText("");
                    }
                    else{Toast.makeText(getActivity(),"you must fill all fields",Toast.LENGTH_SHORT).show();}

                }
            });

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton)
                {
                    if(!name.getText().toString().trim().isEmpty()&&!day.getText().toString().trim().isEmpty()
                            &&!from.getText().toString().trim().isEmpty()&&!to.getText().toString().trim().isEmpty()
                            &&!location.getText().toString().trim().isEmpty()) {

                        Section.createSection(name.getText().toString(),day.getText().toString(),
                                from.getText()+":"+to.getText(),location.getText().toString(),courseId,sqLiteDatabase);
                        Toast.makeText(getActivity(),"section created succesfuly",Toast.LENGTH_SHORT).show();
                        name.setText(""); from.setText("");
                        day.setText("");to.setText(""); location.setText("");
                    }
                    else{Toast.makeText(getActivity(),"you must fill all fields",Toast.LENGTH_SHORT).show();}
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

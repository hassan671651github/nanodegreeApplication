package com.example.ahmed.teachercalender;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.teachercalender.Adapters.StudentNamesAdapter;
import com.example.ahmed.teachercalender.Interfaces.connection;
import com.example.ahmed.teachercalender.database.Absence;
import com.example.ahmed.teachercalender.database.DbHelper;
import com.example.ahmed.teachercalender.database.Quizz;
import com.example.ahmed.teachercalender.database.Register;
import com.example.ahmed.teachercalender.database.Student;
import com.example.ahmed.teachercalender.database.Student_absence;
import com.example.ahmed.teachercalender.database.student_quizz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


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
private void loadData(){
    String query = "select student.student_name, student.student_id from student,register " +
            "where student.student_id=register.student_id and register.subject_id= " + subjectId + " and register.section_id="+sectionId+";";
    studentArrayList=new ArrayList<>();
    Cursor cursor = sqLiteDatabase.rawQuery(query, null);
    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
        Student student=new Student();
        student.setName(cursor.getString(cursor.getColumnIndex(Student.STUDENT_NAME)));
        student.setId(cursor.getInt(cursor.getColumnIndex(Student.STUDENT_ID)));
        studentArrayList.add(student);
    }


    StudentNamesAdapter studentNamesAdapter = new StudentNamesAdapter(studentArrayList, getActivity());
    listView.setAdapter(studentNamesAdapter);


    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            con.connect(studentArrayList.get(position).getId(),"studentNames");

        }
    });
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View fragment=   inflater.inflate(R.layout.fragment_student_names, container, false);

               setHasOptionsMenu(true)   ;

            listView=(ListView)fragment.findViewById(R.id.studentNamesList);
            DbHelper v = new DbHelper(getActivity(), "fci", null, 1);
            sqLiteDatabase = v.getWritableDatabase();
            Bundle bundle = getArguments();
            sectionId = bundle.getInt("sectionId");
            subjectId = bundle.getInt("courseId");
            loadData();


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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        getActivity(). getMenuInflater().inflate(R.menu.student_names, menu);

    }
    CalendarView calendarView;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.Create_quizz) {
           MenuFunc(true,false);
        }

        if(item.getItemId()==R.id.Create_absence) {
            MenuFunc(false,true);

        }
        if (item.getItemId() == R.id.select_name) {
            LayoutInflater linf = LayoutInflater.from(getActivity());
            final View inflator = linf.inflate(R.layout.select_or_addnew, null);
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            Button select = (Button) inflator.findViewById(R.id.select_exit);
            Button add = (Button) inflator.findViewById(R.id.add_new);
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectStudentFun();
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addStudentFunc();
                }
            });
            builder.setTitle("Add Student");
            builder.setView(inflator);
            builder.show();

        }



        return super.onOptionsItemSelected(item);
    }
    private void MenuFunc(final boolean quizz, final boolean absence){


        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.create_quizz_layout, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        calendarView=(CalendarView)view.findViewById(R.id.calendarView);
        calendarView.setVisibility(View.GONE);
        final TextView Name=(TextView)view.findViewById(R.id.creat_quizzName);
        final  TextView date=(TextView) view.findViewById(R.id.QuizzDate);
        Button chosseDate=(Button)view.findViewById(R.id.chosse);
        final Date dateNow=new Date();
        date.setText(new SimpleDateFormat("dd-MM-yyyy").format(dateNow));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Name.getText().toString().trim().isEmpty())
                    Toast.makeText(getActivity(), "Name Can Not Be Empty", Toast.LENGTH_LONG).show();
                else {
                    if(!studentArrayList.isEmpty()) {
                        if(quizz)
                        {
                            Quizz.createQuizz(Name.getText().toString(), dateNow, sqLiteDatabase);
                            String query = "select max(" + Quizz.QUIZZ_ID + ")" + " as id from " + Quizz.TABLE_NAME + ";";
                            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                            cursor.moveToFirst();
                            int id = cursor.getInt(cursor.getColumnIndex("id"));
                            for (int i = 0; i < studentArrayList.size(); i++) {
                                student_quizz.create_student_quizz(subjectId, studentArrayList.get(i).getId(), id, 0, sqLiteDatabase);
                            }
                            Toast.makeText(getActivity(),"QUIZZ CREATED SUCCESFULLY",Toast.LENGTH_LONG).show();
                        }
                        if(absence)
                        {
                            Absence.createAbsence(Name.getText().toString(), dateNow, sqLiteDatabase);
                            String query = "select max(" + Absence.ID+ ")" + " as id from " + Absence.TABLE_NAME + ";";
                            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
                            cursor.moveToFirst();
                            int id = cursor.getInt(cursor.getColumnIndex("id"));
                            for (int i = 0; i < studentArrayList.size(); i++) {
                                Student_absence.cretae_student_absence(studentArrayList.get(i).getId(),id,subjectId,false, sqLiteDatabase);
                            }
                            Toast.makeText(getActivity(),"ABSENCE CREATED SUCCESFULLY",Toast.LENGTH_LONG).show();
                        }

                    }

                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        chosseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendarView.setVisibility(View.VISIBLE);
            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                calendarView.setVisibility(View.GONE);

                Calendar cal = new GregorianCalendar(year,month,dayOfMonth);
                dateNow.setTime(cal.getTimeInMillis());
                date.setText(new SimpleDateFormat("dd-MM-yyyy").format(dateNow));

            }
        });
        builder.show();

    }
   private  void selectStudentFun(){
    LayoutInflater linf = LayoutInflater.from(getActivity());
    final  View inflator = linf.inflate(R.layout.select_name_layout, null);
    String  query = "select * from student ;";
   final  ArrayList<Student>selectList=new ArrayList<>();
    ArrayList<String>stringlist=new ArrayList<>();
    Cursor cursor = sqLiteDatabase.rawQuery(query, null);
    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
        Student student=new Student();
        student.setName(cursor.getString(cursor.getColumnIndex(Student.STUDENT_NAME)));
        student.setId(cursor.getInt(cursor.getColumnIndex(Student.STUDENT_ID)));
        selectList.add(student);
        stringlist.add(student.getName()+"\n"+student.getId());
    }
    final  ListView listView=(ListView)inflator.findViewById(R.id.SelectNameList);
     listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
     listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, stringlist));
    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle("Add Student");
    builder.setView(inflator);

   builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
       @Override
       public void onClick(DialogInterface dialog, int which) {
           SparseBooleanArray checked = listView.getCheckedItemPositions();
           for (int i=0;i<selectList .size();i++) {
               if (checked.get(i)) {
                Register.createRegister(selectList.get(i).getId(),subjectId,sectionId,sqLiteDatabase);

               }

           }
           checked=null;
           loadData();
       }
   });
    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    });


    builder.show();
}
    private  void addStudentFunc(){

        LayoutInflater linf = LayoutInflater.from(getActivity());
        final View inflator = linf.inflate(R.layout.create_student, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Add Student");
        builder.setView(inflator);

        final EditText name = (EditText) inflator.findViewById(R.id.create_student_name);
        final Button save = (Button) inflator.findViewById(R.id.cretae_student_save);
        final Button changeImg = (Button) inflator.findViewById(R.id.create_student_change_img);
        final ImageView img = (ImageView) inflator.findViewById(R.id.create_student_img);
        final EditText id=(EditText)inflator.findViewById(R.id.cretae_student_ID);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().trim().isEmpty() && !id.getText().toString().trim().isEmpty())
                {
                    try {
                        int x = Integer.parseInt(id.getText().toString().trim());
                        Student.createStudent(x, name.getText().toString(), sqLiteDatabase);
                        Register.createRegister(x,subjectId,sectionId,sqLiteDatabase);
                        Toast.makeText(getActivity(), "student created succesfuly", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        id.setText("");
                    }
                    catch (Exception ex){ Toast.makeText(getActivity(),"ID must be integer",Toast.LENGTH_SHORT).show();}
                }
                else
                    Toast.makeText(getActivity(),"YOU MUST FILL ALL FIELDS",Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                if(!name.getText().toString().trim().isEmpty() && !id.getText().toString().trim().isEmpty())
                {
                    try {
                        int x = Integer.parseInt(id.getText().toString().trim());
                        Student.createStudent(x, name.getText().toString(), sqLiteDatabase);
                        Register.createRegister(x,subjectId,sectionId,sqLiteDatabase);
                        Toast.makeText(getActivity(), "student created succesfuly", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        id.setText("");
                    }
                    catch (Exception ex){ Toast.makeText(getActivity(),"ID must be integer",Toast.LENGTH_SHORT).show();}
                }
                else
                    Toast.makeText(getActivity(),"YOU MUST FILL ALL FIELDS",Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {


            }
        });

        builder.show();
    }
}

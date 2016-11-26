package com.example.ahmed.teachercalender.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmed.teachercalender.R;
import com.example.ahmed.teachercalender.database.Student;

import java.util.ArrayList;

/**
 * Created by Ahmed on 25/11/2016.
 */

public class StudentNamesAdapter extends BaseAdapter {

    private ArrayList<Student>studentArrayList;
    Context context;

    public StudentNamesAdapter(ArrayList<Student> studentArrayList, Context context) {
        this.studentArrayList = studentArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return studentArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view=inflater.inflate(R.layout.list_view_student_names,null);
        TextView name=(TextView)view.findViewById(R.id.student_name);
        TextView id=(TextView)view.findViewById(R.id.student_id);

       /* EditText degree=(EditText)view.findViewById(R.id.quizzDegree);
                CheckBox absence=(CheckBox)view.findViewById(R.id.absence);
       */
        name.setText(studentArrayList.get(position).getName());
        id.setText(Integer.toString(studentArrayList.get(position).getId()));
        return view;
    }
}

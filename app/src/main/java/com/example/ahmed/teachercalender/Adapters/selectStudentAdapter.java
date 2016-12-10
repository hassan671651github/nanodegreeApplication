package com.example.ahmed.teachercalender.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ahmed.teachercalender.R;
import com.example.ahmed.teachercalender.database.Student;

import java.util.ArrayList;

/**
 * Created by Ahmed on 07/12/2016.
 */
public class selectStudentAdapter extends BaseAdapter {
    Context context;ArrayList<Student>studentArrayList;
  public   selectStudentAdapter(Context context, ArrayList<Student>students){
        studentArrayList=students;
      this.context=context;
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
        View view=inflater.inflate(R.layout.select_name_list,null);
        TextView name=(TextView)view.findViewById(R.id.student_name_select_name_list);
        TextView id=(TextView)view.findViewById(R.id.student_id_select_name_list);

        name.setText(studentArrayList.get(position).getName());
        id.setText(studentArrayList.get(position).getId()+"");
        view.setBackgroundColor(Color.red(100));
        return view;
    }
}

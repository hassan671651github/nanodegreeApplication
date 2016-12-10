package com.example.ahmed.teachercalender.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahmed.teachercalender.CustomAbsence;
import com.example.ahmed.teachercalender.CustomQuizz;
import com.example.ahmed.teachercalender.Interfaces.connection;
import com.example.ahmed.teachercalender.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Ahmed on 03/12/2016.
 */
public class AbsenceAdapter extends BaseAdapter {

    private Context context;
    ArrayList<CustomAbsence> customAbsenceArrayList;
connection conect;
    public AbsenceAdapter(Context context, ArrayList<CustomAbsence> customAbsenceArrayList, connection conect) {
        this.context = context;
        this.customAbsenceArrayList = customAbsenceArrayList;
        this.conect=conect;

    }

    @Override
    public int getCount() {
        return customAbsenceArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return customAbsenceArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_view_student_quizzes, null);
        TextView name = (TextView) view.findViewById(R.id.listStuQuiz_Name);
        Button change = (Button) view.findViewById(R.id.changeDegree);
        TextView date = (TextView) view.findViewById(R.id.listStuQuiz_Date);
        final TextView value = (TextView) view.findViewById(R.id.quizzDegree);

        name.setText(customAbsenceArrayList.get(position).getName());
        date.setText((new SimpleDateFormat("dd-MM-yyyy").format(customAbsenceArrayList.get(position).getDate())).toString());
        value.setText(customAbsenceArrayList.get(position).getValue() + "");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        conect.connect(position,"AbsenceAdapter");



            }
        });

        return view;

    }

    }





